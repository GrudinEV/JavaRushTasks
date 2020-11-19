package com.javarush.task.task38.task3804;

public class FactoryException {
    public static Throwable getException(Object object) {
        if (object == null) {
            return new IllegalArgumentException();
        }
        String nameEnum = object.getClass().getSimpleName();
        String message = object.toString().charAt(0) + object.toString().substring(1).replace("_", " ").toLowerCase();
        switch (nameEnum) {
            case "ApplicationExceptionMessage":
                return new Exception(message);
            case "DatabaseExceptionMessage":
                return new RuntimeException(message);
            case "UserExceptionMessage":
                return new Error(message);
            default:
                return new IllegalArgumentException();
        }
    }
}
