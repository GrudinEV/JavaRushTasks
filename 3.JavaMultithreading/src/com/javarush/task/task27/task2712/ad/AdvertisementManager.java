package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.LinkedList;
import java.util.List;

public class AdvertisementManager {
    private AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    // Итоговый список видео, удовлетворяющий условию формирования списка:
    // 1. Максимальная стоимость
    // 2. Минимальная длительность при равной максимальной стоимости для разных вариантов
    // 3. Максимальное количество роликов при равных максимальной стоимости и минимальной длительности
    private final List<Advertisement> listVideosForShow = new LinkedList<>();
    // Промежуточный список видео, все возможные варианты которого формируется рекурсией в методе getListVideos()
    // с учётом выполнения перечня условий для каждого варианта:
    // 1. Видео не повторяются
    // 2. Общее время списка видео не превышает длительности приготовления заказа - timeSeconds
    // 3. Видео имеют оставшееся количество показов больше 0
    private final List<Advertisement> listVideosForShowFind = new LinkedList<>();


    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {
        // Проверяем, что в хранилище присутствуют видео для показа
        if (storage.list().isEmpty()) {
            throw new NoVideoAvailableException();
        }
        // Получаем итоговый список видео для показа
        getListVideos();
        // Сортируем список видео для показа по условию задачи:
        // 1. Сначала самые дорогие
        // 2. Сначала ролики с самой дешёвой стоимости секунды при равной стоимости всего ролика.
        listVideosForShow.sort((x1, x2) ->
        {
            return (int) (x2.getAmountPerOneDisplaying() - x1.getAmountPerOneDisplaying()) != 0 ?
                    (int) (x2.getAmountPerOneDisplaying() - x1.getAmountPerOneDisplaying()) :
                    (int) (x1.getAmountPerSecondDisplaying() - x2.getAmountPerSecondDisplaying());
        });
        StatisticManager.getInstance().register(new VideoSelectedEventDataRow(listVideosForShow,
                listVideosForShow.stream().mapToLong(Advertisement::getAmountPerOneDisplaying).sum(),
                listVideosForShow.stream().mapToInt(Advertisement::getDuration).sum()));
        // Показываем видео-ролики из отсортированного итогового списка видео
        listVideosForShow.stream()
                .peek(Advertisement::revalidate)
                .map(Advertisement::toString)
                .forEach(ConsoleHelper::writeMessage);
    }

    private void getListVideos() {
        // Перебираем все видео из хранилища
        for (Advertisement advertisement : storage.list()) {
            //Определяем длительность и стоимость промежуточного списка видео
            int listVideosDurationFind = listVideosForShowFind.stream().mapToInt(Advertisement::getDuration).sum();
            long amountDisplayingFind = listVideosForShowFind.stream().mapToLong(Advertisement::getAmountPerOneDisplaying).sum();
            //Начинаем проверять:
            //Если: 1. видео нет в промежуточном списке, 2. В оставшемся времени заказа осталось время для показа данного видео,
            //3. Количество возможных показов данного видео больше 0, то
            //1. Добавляем видео в промежуточный список,
            //2. Рекурсивно запускаем добавления следующего видео из хранилища,
            //3. После неудачной попытки добавить следующее видео удаляем из промежуточного списка видео последнее добавленное видео.
            if (!listVideosForShowFind.contains(advertisement) && timeSeconds - listVideosDurationFind >= advertisement.getDuration()
                && advertisement.getHits() > 0) {
                listVideosForShowFind.add(advertisement);
                getListVideos();
                listVideosForShowFind.remove(advertisement);
            // Если видео не получилось добавить в промежуточный список поиска видео, то ...
            } else {
                // Если не получилось добавить последнее видео из хранилища, то считаем промежуточный список видео полностью сформированным
                // Начинаем проверять необходимость замены содержимого итогового списка содержимым промежуточного списка
                if (advertisement.equals(storage.list().get(storage.list().size() - 1))) {
                    //Определяем длительность и стоимость итогового списка видео
                    int listVideosDuration = listVideosForShow.stream().mapToInt(Advertisement::getDuration).sum();
                    long amountDisplaying = listVideosForShow.stream().mapToLong(Advertisement::getAmountPerOneDisplaying).sum();
                    // Если стоимость всех видео итогового списка меньше стоимости всех видео промежуточного списка
                    // То заменяем содеримое итогового списка содержимым промежуточного списка
                    if (amountDisplaying < amountDisplayingFind) {
                        changeListVideosForShow();
                    } else {
                        //Иначе, если стоимость всех видео итогового списка равна стоимости всех видео промежуточного списка, то...
                        if (amountDisplaying == amountDisplayingFind) {
                            // Проверяем, если длительность всех видео итогового списка меньше длительности всех видео промежуточного списка
                            // То заменяем содеримое итогового списка содержимым промежуточного списка
                            if (listVideosDuration < listVideosDurationFind) {
                                changeListVideosForShow();
                            } else {
                                //Иначе, если длительность всех видео итогового списка равна длительности всех видео промежуточного списка,
                                // то...
                                if (listVideosDuration == listVideosDurationFind) {
                                    // Проверяем, если количество всех видео итогового списка больше количества всех видео
                                    // промежуточного списка
                                    // То заменяем содеримое итогового списка содержимым промежуточного списка
                                    if (listVideosForShow.size() > listVideosForShowFind.size()) {
                                        changeListVideosForShow();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // Метод для замены содержимого итогового списка содержимым промежуточного списка
    private void changeListVideosForShow() {
        listVideosForShow.clear();
        listVideosForShow.addAll(listVideosForShowFind);
    }
}
