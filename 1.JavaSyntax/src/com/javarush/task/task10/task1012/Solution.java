package com.javarush.task.task10.task1012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/* 
Количество букв
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Алфавит
        List<Character> alphabet = Arrays.asList(
                'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж',
                'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о',
                'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц',
                'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я');

        // Ввод строк в список строк
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            String s = reader.readLine();
            list.add(s.toLowerCase());
        }

        // напишите тут ваш код
        //преобразование списка строк в список массивов с элементами char
        ArrayList<char[]> listChar = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            listChar.add(list.get(i).toCharArray());
        }
        //создание карты: key = буква алфавита, value = количество таких букв, изначально value = 0
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < alphabet.size(); i++) {
            map.put(alphabet.get(i), 0);
        }
        //подсчитываем количество определённых букв, содержащихся в массивах списка массивов с элементами char
        //в карте алфавита для содержащегося ключа при обнаружении увеличиваем значение на 1
        int v = 0;
        for (int i = 0; i < listChar.size(); i++) {
            for (int j = 0; j < listChar.get(i).length; j++) {
                char c = listChar.get(i)[j];
                if (alphabet.contains(c)) {
                    v = map.get(c);
                    v++;
                    map.replace(c, v);}
            }
        }
        //выводим карту алфавита с подсчитанными в строках буквами
        for (Character c : alphabet)
            System.out.println(c + " " + map.get(c));

    }
}
