package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    private Path logDir;
    private ArrayList<Recording> listAllRecording = new ArrayList<>();

    public LogParser(Path path) {
        this.logDir = path;
        initListAllRecording();
    }

    private void initListAllRecording() {
        List<Path> listFiles = getListPaths();
        for (Path path : listFiles) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(String.valueOf(path)))) {
                String record;
                while ((record = bufferedReader.readLine()) != null) {
                    String[] recordArray = record.split("\\t");
                    String ip = recordArray[0];
                    String userName = recordArray[1];

                    String dataString = recordArray[2];
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                    Date date = sdf.parse(dataString);

                    Integer numberTask = null;
                    Event event = null;
                    if (recordArray[3].contains(" ")) {
                        String[] eventArray = recordArray[3].split(" ");
                        event = Event.valueOf(eventArray[0]);
                        numberTask = Integer.parseInt(eventArray[1]);
                    } else {
                        event = Event.valueOf(recordArray[3]);
                    }

                    Status status = Status.valueOf(recordArray[4]);

                    Recording recording = new Recording(ip, userName, date, event, numberTask, status);
                    listAllRecording.add(recording);
                }
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Path> getListPaths() {
        try {
            return Files.list(logDir).filter(x -> x.toString().endsWith(".log")).collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Path ERROR!");
            return null;
        }
    }

    private Stream<Recording> getStreamRecordingByRangeData(Date after, Date before) {
        return listAllRecording.stream()
                .filter(x -> !(after != null && (x.date.before(after) || x.date.equals(after))))
                .filter(x -> !(before != null && (x.date.after(before) || x.date.equals(before))));
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        return getStreamRecordingByRangeData(after, before).
                map(x -> x.ip).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return getStreamRecordingByRangeData(after, before).
                filter(x -> x.userName.equals(user)).
                map(x -> x.ip).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return getStreamRecordingByRangeData(after, before).
                filter(x -> x.event.equals(event)).
                map(x -> x.ip).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return getStreamRecordingByRangeData(after, before).
                filter(x -> x.status.equals(status)).
                map(x -> x.ip).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getAllUsers() {
        return getStreamRecordingByRangeData(null, null).
                map(x -> x.userName).collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return getStreamRecordingByRangeData(after, before).
                map(x -> x.userName).collect(Collectors.toSet()).size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        return getStreamRecordingByRangeData(after, before).
                filter(x -> x.userName.equals(user)).map(x -> x.event).collect(Collectors.toSet()).size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return getStreamRecordingByRangeData(after, before).
                filter(x -> x.ip.equals(ip)).map(x -> x.userName).
                collect(Collectors.toSet());
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return getStreamRecordingByRangeData(after, before).
                filter(x -> x.event.equals(Event.LOGIN)).
                map(x -> x.userName).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return getStreamRecordingByRangeData(after, before).
                filter(x -> x.event.equals(Event.DOWNLOAD_PLUGIN)).
                map(x -> x.userName).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return getStreamRecordingByRangeData(after, before).
                filter(x -> x.event.equals(Event.WRITE_MESSAGE)).
                map(x -> x.userName).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return getStreamRecordingByRangeData(after, before).
                filter(x -> x.event.equals(Event.SOLVE_TASK)).
                map(x -> x.userName).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return getStreamRecordingByRangeData(after, before).
                filter(x -> x.event.equals(Event.SOLVE_TASK)).
                filter(x -> x.numberTask == task).
                map(x -> x.userName).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return getStreamRecordingByRangeData(after, before).
                filter(x -> x.event.equals(Event.DONE_TASK)).
                map(x -> x.userName).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return getStreamRecordingByRangeData(after, before).
                filter(x -> x.event.equals(Event.DONE_TASK)).
                filter(x -> x.numberTask == task).
                map(x -> x.userName).collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        return getStreamRecordingByRangeData(after, before).
                filter(x -> x.userName.equals(user)).
                filter(x -> x.event.equals(event)).
                map(x -> x.date).collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        return getStreamRecordingByRangeData(after, before)
                .filter(x -> x.status.equals(Status.FAILED))
                .map(x -> x.date).collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        return getStreamRecordingByRangeData(after, before)
                .filter(x -> x.status.equals(Status.ERROR))
                .map(x -> x.date).collect(Collectors.toSet());
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        List<Date> list = getStreamRecordingByRangeData(after, before)
                .filter(x -> x.userName.equals(user))
                .filter(x -> x.event.equals(Event.LOGIN))
                .filter(x -> x.status.equals(Status.OK))
                .map(x -> x.date).sorted().collect(Collectors.toList());
        return list.size() == 0 ? null : list.get(0);
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        List<Date> list = getStreamRecordingByRangeData(after, before)
                .filter(x -> x.userName.equals(user))
                .filter(x -> x.event.equals(Event.SOLVE_TASK))
                .filter(x -> x.numberTask == task)
                .map(x -> x.date).sorted().collect(Collectors.toList());
        return list.size() == 0 ? null : list.get(0);
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        List<Date> list = getStreamRecordingByRangeData(after, before)
                .filter(x -> x.userName.equals(user))
                .filter(x -> x.event.equals(Event.DONE_TASK))
                .filter(x -> x.numberTask == task)
                .map(x -> x.date).sorted().collect(Collectors.toList());
        return list.size() == 0 ? null : list.get(0);
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        return getStreamRecordingByRangeData(after, before)
                .filter(x -> x.userName.equals(user))
                .filter(x -> x.event.equals(Event.WRITE_MESSAGE))
                .map(x -> x.date).collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        return getStreamRecordingByRangeData(after, before)
                .filter(x -> x.userName.equals(user))
                .filter(x -> x.event.equals(Event.DOWNLOAD_PLUGIN))
                .map(x -> x.date).collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        return getStreamRecordingByRangeData(after, before)
                .map(x -> x.event).collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        return getStreamRecordingByRangeData(after, before)
                .filter(x -> x.ip.equals(ip))
                .map(x -> x.event).collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        return getStreamRecordingByRangeData(after, before)
                .filter(x -> x.userName.equals(user))
                .map(x -> x.event).collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        return getStreamRecordingByRangeData(after, before)
                .filter(x -> x.status.equals(Status.FAILED))
                .map(x -> x.event).collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        return getStreamRecordingByRangeData(after, before)
                .filter(x -> x.status.equals(Status.ERROR))
                .map(x -> x.event).collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        return (int) getStreamRecordingByRangeData(after, before)
                .filter(x -> x.event.equals(Event.SOLVE_TASK))
                .filter(x -> x.numberTask == task)
                .count();
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        return (int) getStreamRecordingByRangeData(after, before)
                .filter(x -> x.event.equals(Event.DONE_TASK))
                .filter(x -> x.numberTask == task)
                .count();
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> map = new HashMap<>();
        getStreamRecordingByRangeData(after, before)
                .filter(x -> x.event.equals(Event.SOLVE_TASK))
                .forEach(x -> {
                    if (map.containsKey(x.numberTask)) {
                        map.put(x.numberTask, map.get(x.numberTask) + 1);
                    } else {
                        map.put(x.numberTask, 1);
                    }
                });
        return map;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> map = new HashMap<>();
        getStreamRecordingByRangeData(after, before)
                .filter(x -> x.event.equals(Event.DONE_TASK))
                .forEach(x -> {
                    if (map.containsKey(x.numberTask)) {
                        map.put(x.numberTask, map.get(x.numberTask) + 1);
                    } else {
                        map.put(x.numberTask, 1);
                    }
                });
        return map;
    }

    @Override
    public Set<Object> execute(String query) {
        String[] arraySplitBySpace = query.split("\\s");
        String[] arraySplitByQuotes = query.split("\"");
        String field1 = arraySplitBySpace[1];
        String field2 = null;
        String field2Value = null;
        if (arraySplitBySpace.length > 2) {
            field2 = query.split("\\s")[3];
            field2Value = arraySplitByQuotes[1];
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date dateAfter = null, dateBefore = null;
        if (arraySplitByQuotes.length > 3) {
            try {
                dateAfter = sdf.parse(arraySplitByQuotes[3]);
                dateBefore = sdf.parse(arraySplitByQuotes[5]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        String finalField = field2;
        String finalField2Value = field2Value;
        return getStreamRecordingByRangeData(dateAfter, dateBefore)
                .filter(x -> {
                    if (finalField != null) {
                        switch (finalField) {
                            case "ip":
                                return x.ip.equals(finalField2Value);
                            case "user":
                                return x.userName.equals(finalField2Value);
                            case "date":
                                try {
                                    Date date = sdf.parse(finalField2Value);
                                    return x.date.equals(date);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                return false;
                            case "event":
                                return x.event.equals(Event.valueOf(finalField2Value));
                            case "status":
                                return x.status.equals(Status.valueOf(finalField2Value));
                        }
                    }
                    return true;
                })
                .map(x -> {
                    switch (field1) {
                        case "ip":
                            return x.ip;
                        case "user":
                            return x.userName;
                        case "date":
                            return x.date;
                        case "event":
                            return x.event;
                        case "status":
                            return x.status;
                    }
                    return null;
                })
                .collect(Collectors.toSet());
    }

    private static class Recording {
        private final String ip;
        private final String userName;
        private final Date date;
        private final Event event;
        private final Integer numberTask;
        private final Status status;

        public Recording(String ip, String userName, Date date, Event eventUser, Integer numberTask, Status status) {
            this.ip = ip;
            this.userName = userName;
            this.date = date;
            this.event = eventUser;
            this.numberTask = numberTask;
            this.status = status;
        }
    }
}