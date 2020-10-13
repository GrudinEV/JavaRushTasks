package com.javarush.task.task34.task3404;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Рекурсия для мат. выражения
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        solution.recurse("tan(45)", 0);  System.out.println("1 1 - expected output");
//        solution.recurse("tan(-45)", 0);  System.out.println("-1 2 - expected output");
//        solution.recurse("0.305", 0);  System.out.println("0.3 0 - expected output");
//        solution.recurse("0.3051", 0);  System.out.println("0.31 - expected output");
//        solution.recurse("(0.3051)", 0);  System.out.println("0.31 - expected output");
//        solution.recurse("1+(1+(1+1)*(1+1))*(1+1)+1", 0);  System.out.println("12 8 - expected output");
//        solution.recurse("tan(44+sin(89-cos(180)^2))", 0);  System.out.println("1 6 - expected output");
//        solution.recurse("-2+(-2+(-2)-2*(2+2))", 0);  System.out.println("-14 8 - expected output");
//        solution.recurse("sin(80+(2+(1+1))*(1+1)+2)", 0);  System.out.println("1 7 - expected output");
//        solution.recurse("1+4/2/2+2^2+2*2-2^(2-1+1)", 0);  System.out.println("6 11 - expected output");
//        solution.recurse("10-2^(2-1+1)", 0);  System.out.println("6 4 - expected output");
//        solution.recurse("2^10+2^(5+5)", 0);  System.out.println("2048 4 - expected output");
//        solution.recurse("1.01+(2.02-1+1/0.5*1.02)/0.1+0.25+41.1", 0);  System.out.println("72.96 8 - expected output");
//        solution.recurse("0.000025+0.000012", 0);  System.out.println("0 1 - expected output");
//        solution.recurse("-2-(-2-1-(-2)-(-2)-(-2-2-(-2)-2)-2-2)", 0);  System.out.println("-3 16 - expected output");
//        solution.recurse("cos(3 + 19*3)", 0);  System.out.println("0.5 3 - expected output");
//        solution.recurse("2*(589+((2454*0.1548/0.01*(-2+9^2))+((25*123.12+45877*25)+25))-547)", 0);  System.out.println("8302231.36 14 - expected output");
//        solution.recurse("(-1 + (-2))", 0);  System.out.println("-3 3 - expected output");
//        solution.recurse("-sin(2*(-5+1.5*4)+28)", 0);  System.out.println("-0.5 7 - expected output");
//        solution.recurse("sin(100)-sin(100)", 0);  System.out.println("0 3 - expected output");
//        solution.recurse("-(-22+22*2)", 0);  System.out.println("-22 4 - expected output");
//        solution.recurse("-2^(-2)", 0);  System.out.println("-0.25 3 - expected output");
//        solution.recurse("-(-2^(-2))+2+(-(-2^(-2)))", 0);  System.out.println("2.5 10 - expected output");
//        solution.recurse("(-2)*(-2)", 0);  System.out.println("4 3 - expected output");
//        solution.recurse("(-2)/(-2)", 0);  System.out.println("1 3 - expected output");
//        solution.recurse("sin(-30)", 0);  System.out.println("-0.5 2 - expected output");
//        solution.recurse("cos(-30)", 0);  System.out.println("0.87 2 - expected output");
//        solution.recurse("tan(-30)", 0);  System.out.println("-0.58 2 - expected output");
//        solution.recurse("2+8*(9/4-1.5)^(1+1)", 0);  System.out.println("6.5 6 - expected output");
//        solution.recurse("0.005", 0);  System.out.println("0.01 0 - expected output");
//        solution.recurse("0.0049", 0);  System.out.println("0 0 - expected output");
//        solution.recurse("0+0.304", 0);  System.out.println("0.3 1 - expected output");
//        solution.recurse("sin(45) - cos(45)", 0);  System.out.println("0 3 - expected output");
//        solution.recurse("0/(-3)", 0);  System.out.println("0 2 - expected output");
    }

    public void recurse(final String expression, int countOperation) {
        /*Считаем количество действий в выражении*/
        String str = expression;
        if (countOperation == 0) {
            Matcher matcher = Pattern.compile("(sin)|(cos)|(tan)|[\\^\\*\\+\\/\\-]").matcher(expression);
            while (matcher.find()) {
                countOperation++;
            }
            /*Удаляем все пробелы*/
            str = expression.replace(" ", "");
        }

        /*Округляем до 2 знаков после запятой*/
        if (Pattern.matches("-?\\d+(\\.\\d+)?", str)) {
            BigDecimal bd = new BigDecimal(str).setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();
            System.out.println(bd + " " + countOperation);
            return;
        }

        /*Находим sin, cos, tan, внутри которых находятся только числа, при нахождении таковых скобки удаляем и рекурсивно запускаем метод с исправленной строкой*/
        Matcher m = Pattern.compile("(sin|cos|tan)\\(-?\\d+(\\.\\d+)?\\)").matcher(str);
        if (m.find()) {
            String substring = str.substring(m.start(), m.end());
            double result = 0;
            if (substring.startsWith("sin")) {
                result = Math.sin(Math.toRadians(Double.parseDouble(substring.substring(4, substring.length() - 1))));
            }
            if (substring.startsWith("cos")) {
                result = Math.cos(Math.toRadians(Double.parseDouble(substring.substring(4, substring.length() - 1))));
            }
            if (substring.startsWith("tan")) {
                result = Math.tan(Math.toRadians(Double.parseDouble(substring.substring(4, substring.length() - 1))));
            }
            str = str.replace(substring, "" + result);
            recurse(str, countOperation);
            return;
        }

        /*Убираем +- и --*/
        if (str.contains("+-")) {
            str = str.replace("+-", "-");
            recurse(str, countOperation);
            return;
        }
        if (str.contains("-+")) {
            str = str.replace("-+", "-");
            recurse(str, countOperation);
            return;
        }
        if (str.contains("--")) {
            str = str.replace("--", "+");
            recurse(str, countOperation);
            return;
        }
        if (str.contains("++")) {
            str = str.replace("++", "+");
            recurse(str, countOperation);
            return;
        }
        if (str.startsWith("+")) {
            str = str.substring(1);
            recurse(str, countOperation);
            return;
        }
        if (str.contains("(+")) {
            str = str.replace("(+", "(");
            recurse(str, countOperation);
            return;
        }

        /*Находим скобки, внутри которых находятся только числа, при нахождении таковых скобки удаляем и рекурсивно запускаем метод с исправленной строкой*/
        m = Pattern.compile("(\\(-?\\d+(\\.\\d+)?\\))").matcher(str);
        if (m.find()) {
            String substring = str.substring(m.start(), m.end());
            if (str.equals(substring)) {
                recurse(str.substring(1, str.length() - 1), countOperation);
                return;
            }
            String[] array = str.split(substring);
            substring = substring.substring(1, substring.length() - 1);
            String strIzm = str.substring(0, m.start()) + substring + str.substring(m.end());
            recurse(strIzm, countOperation);
            return;
        }

        /*Находим возведение в степень, возводим, меняем строку выражения и рекурсивно запускаем метод с исправленной строкой*/
        m = Pattern.compile("(^\\d+(\\.\\d+)?\\^-?\\d+(\\.\\d+)?)").matcher(str);
        if (m.find()) {
            String substring = str.substring(m.start(), m.end());
            String[] array = substring.split("\\^");
            double result = Math.pow(Double.parseDouble(array[0]), Double.parseDouble(array[1]));
            String res = String.valueOf(result);
            str = res + str.substring(m.end());
            recurse(str, countOperation);
            return;
        }
        m = Pattern.compile("([^\\d]\\d+(\\.\\d+)?\\^-?\\d+(\\.\\d+)?)").matcher(str);
        if (m.find()) {
            String substring = str.substring(m.start() + 1, m.end());
            String[] array = substring.split("\\^");
            double result = Math.pow(Double.parseDouble(array[0]), Double.parseDouble(array[1]));
            String res = String.valueOf(result);
            str = str.substring(0, m.start() + 1) + res + str.substring(m.end());
            recurse(str, countOperation);
            return;
        }

        /*Находим умножение или деление, вычисляем, меняем строку выражения и рекурсивно запускаем метод с исправленной строкой*/
        m = Pattern.compile("(^-?\\d+(\\.\\d+)?(\\/|\\*)-?\\d+(\\.\\d+)?)").matcher(str);
        while (m.find()) {
            if (!str.substring(m.end()).startsWith("^")) {
                String substring = str.substring(m.start(), m.end());
                String res = divideOrMultiply(substring);
                str = res + str.substring(m.end());
                recurse(str, countOperation);
                return;
            }
        }
        m = Pattern.compile("([^\\d]-?\\d+(\\.\\d+)?(\\/|\\*)-?\\d+(\\.\\d+)?)").matcher(str);
        while (m.find()) {
            if (!str.substring(m.end()).startsWith("^")) {
                String substring = str.substring(m.start() + 1, m.end());
                String res = divideOrMultiply(substring);
                str = str.substring(0, m.start() + 1) + res + str.substring(m.end());
                recurse(str, countOperation);
                return;
            }
        }

        /*Находим сложение или вычитание, вычисляем, меняем строку выражения и рекурсивно запускаем метод с исправленной строкой*/
        m = Pattern.compile("(^-?\\d+(\\.\\d+)?(\\+|\\-)-?\\d+(\\.\\d+)?)").matcher(str);
        while (m.find()) {
            if (!str.substring(m.end()).startsWith("^") && !str.substring(m.end()).startsWith("/") && !str.substring(m.end()).startsWith("*")) {
                String substring = str.substring(m.start(), m.end());
                String res = addOrSubstract(substring);
                str = res + str.substring(m.end());
                recurse(str, countOperation);
                return;
            }
        }
        m = Pattern.compile("([^\\d]-?\\d+(\\.\\d+)?(\\+|\\-)-?\\d+(\\.\\d+)?)").matcher(str);
        while (m.find()) {
            if (!str.substring(m.end()).startsWith("^") && !str.substring(m.end()).startsWith("/") && !str.substring(m.end()).startsWith("*")) {
                String substring = str.substring(m.start() + 1, m.end());
                String res = addOrSubstract(substring);
                str = str.substring(0, m.start() + 1) + res + str.substring(m.end());
                recurse(str, countOperation);
                return;
            }
        }
    }

    private String divideOrMultiply(String substring) {
        String[] array = substring.split("\\/|\\*");
        BigDecimal result;
        if (substring.contains("/")) {
            result = new BigDecimal(array[0]).divide(new BigDecimal(array[1]));
        } else {
            result = new BigDecimal(array[0]).multiply(new BigDecimal(array[1]));
        }
        return String.valueOf(result);
    }

    private String addOrSubstract(String substring) {
        Matcher mForFirstNumber = Pattern.compile("(^-?\\d+(\\.\\d+)?(\\+|\\-))").matcher(substring);
        mForFirstNumber.find();
        String firstNumber = substring.substring(0, mForFirstNumber.end() - 1);
        String znak = substring.substring(mForFirstNumber.end() - 1, mForFirstNumber.end());
        String secondNumber = substring.substring(mForFirstNumber.end());
        BigDecimal result;
        if (znak.contains("+")) {
            result = new BigDecimal(firstNumber).add(new BigDecimal(secondNumber));
        } else {
            result = new BigDecimal(firstNumber).subtract(new BigDecimal(secondNumber));
            if (result.compareTo(BigDecimal.ZERO) == 0 || result.toString().contains("E")) {
                result = new BigDecimal("0");
            }
        }
        return String.valueOf(result);
    }

    public Solution() {
        //don't delete
    }
}
