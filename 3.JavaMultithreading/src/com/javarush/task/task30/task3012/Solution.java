package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private static final int[] arrayNumbers = {1, 3, 9, 27, 81, 243, 729, 2187};
    private static int[] numberIn3Radix = new int[8];
    private static List<Integer> listNumbers = new ArrayList<>();
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(1);
        solution.createExpression(2);
        solution.createExpression(3);
        solution.createExpression(9);
        solution.createExpression(32);
        solution.createExpression(74);
        solution.createExpression(258);
        solution.createExpression(951);
        solution.createExpression(1234);
        solution.createExpression(3000);
    }

    public void createExpression(int number) {
        //напишите тут ваш код
        label: for (int i0 = -1; i0 < 2; i0++) {
            numberIn3Radix[0] = i0;
            for (int i1 = -1; i1 < 2; i1++) {
                numberIn3Radix[1] = i1;
                for (int i2 = -1; i2 < 2; i2++) {
                    numberIn3Radix[2] = i2;
                    for (int i3 = -1; i3 < 2; i3++) {
                        numberIn3Radix[3] = i3;
                        for (int i4 = -1; i4 < 2; i4++) {
                            numberIn3Radix[4] = i4;
                            for (int i5 = -1; i5 < 2; i5++) {
                                numberIn3Radix[5] = i5;
                                for (int i6 = -1; i6 < 2; i6++) {
                                    numberIn3Radix[6] = i6;
                                    for (int i7 = -1; i7 < 2; i7++) {
                                        numberIn3Radix[7] = i7;
                                        int sum = i0 * arrayNumbers[0] + i1 * arrayNumbers[1] + i2 * arrayNumbers[2]
                                                    + i3 * arrayNumbers[3] + i4 * arrayNumbers[4] + i5 * arrayNumbers[5]
                                                    + i6 * arrayNumbers[6] + i7 * arrayNumbers[7];
                                        if (sum == number) {
                                            break label;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.print(number + " =");
        for (int i = 0; i <= 7; i++) {
            switch (numberIn3Radix[i]) {
                case -1:
                    System.out.print(" - " + -numberIn3Radix[i] * arrayNumbers[i]);
                    break;
                case 1:
                    System.out.print(" + " + numberIn3Radix[i] * arrayNumbers[i]);
            }
        }
        System.out.println();
//        RecursiveAction action = new RecursiveAction() {
//            @Override
//            protected void compute() {
//                int lastElementFromArrayNumbers;
//                if (listNumbers.size() == 0) {
//                    lastElementFromArrayNumbers = 0;
//                } else {
//                    int lastNumberInList = Math.abs(listNumbers.get(listNumbers.size() - 1));
//                    int indexOfLastNumberInList = Arrays.asList(arrayNumbers).indexOf(lastNumberInList);
//                    lastElementFromArrayNumbers = 1 + indexOfLastNumberInList;
//                    System.out.println(indexOfLastNumberInList);
//                }
//                for (int i = lastElementFromArrayNumbers; i < arrayNumbers.length; i++) {
//                    int numberForCheck = arrayNumbers[i];
//                    listNumbers.add(- numberForCheck);
//                    if (listNumbers.stream().mapToInt(x -> x).sum() == number) {
//                        System.out.println("Выражение найдено");
//                        break;
//                    } else {
//                        createExpression(number);
//                        if (listNumbers.stream().mapToInt(x -> x).sum() == number) {
//                            System.out.println("Выражение найдено");
//                            break;
//                        }
//                    }
//                    listNumbers.remove(- numberForCheck);
//                    listNumbers.add(numberForCheck);
//                    if (listNumbers.stream().mapToInt(x -> x).sum() == number) {
//                        System.out.println("Выражение найдено");
//                        break;
//                    } else {
//                        createExpression(number);
//                        if (listNumbers.stream().mapToInt(x -> x).sum() == number) {
//                            System.out.println("Выражение найдено");
//                            break;
//                        }
//                    }
//                    listNumbers.remove(numberForCheck);
//                }
//            }
//        };
//        action.fork();
//        action.join();
    }
}