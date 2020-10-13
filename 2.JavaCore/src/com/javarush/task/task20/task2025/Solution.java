package com.javarush.task.task20.task2025;

import java.util.*;

/*
Алгоритмы-числа
*/
public class Solution {
    //проверка числа на принадлежность числам Армстронга
    public static boolean equalsNumbers(byte[] i, long N) {
        byte[] number = new byte[19];
        for (byte j = (byte) (number.length - 1); j >= 0; j--) {
            if (N > 0) {
                number[j] = (byte) (N % 10);
                N = N / 10;
            } else {
                number[j] = 0;
            }
        }
        Arrays.sort(number);
        return Arrays.equals(number, i);
    }
    //создание матрицы чисел в степени
    public static long[][] arrayCreating(byte length) {
        long[][] arraySquare = new long[length][8];
        for (byte i = 0; i < length; i++) {
            for (byte j = 0; j < 8; j++) {
                arraySquare[i][j] = (long) Math.pow(j + 2, i + 1);
            }
        }
        return arraySquare;
    }

    public static long[] getNumbers(long N) {
        String str = N + "";
        byte lenN = (byte) str.length();

        //создаём матрицу чисел от 0 до 9 в степенях до длины числа N
        long[][] arraySquare = arrayCreating((byte) (N +"").length());

        Set<Long> set = new HashSet<>();
        //проверяем число N
        byte[] i = new byte[19];
        byte minDegree = 1;
        for (i[0] = 0; i[0] < 10; i[0]++) {
            if (i[0] > 0) minDegree = 19;
            for (i[1] = i[0]; i[1] < 10; i[1]++) {
                if (i[1] > 0 && minDegree < 18) minDegree = 18;
                for (i[2] = i[1]; i[2] < 10; i[2]++) {
                    if (i[2] > 0 && minDegree < 17) minDegree = 17;
                    for (i[3] = i[2]; i[3] < 10; i[3]++) {
                        if (i[3] > 0 && minDegree < 16) minDegree = 16;
                        for (i[4] = i[3]; i[4] < 10; i[4]++) {
                            if (i[4] > 0 && minDegree < 15) minDegree = 15;
                            for (i[5] = i[4]; i[5] < 10; i[5]++) {
                                if (i[5] > 0 && minDegree < 14) minDegree = 14;
                                for (i[6] = i[5]; i[6] < 10; i[6]++) {
                                    if (i[6] > 0 && minDegree < 13) minDegree = 13;
                                    for (i[7] = i[6]; i[7] < 10; i[7]++) {
                                        if (i[7] > 0 && minDegree < 12) minDegree = 12;
                                        for (i[8] = i[7]; i[8] < 10; i[8]++) {
                                            if (i[8] > 0 && minDegree < 11) minDegree = 11;
                                            for (i[9] = i[8]; i[9] < 10; i[9]++) {
                                                if (i[9] > 0 && minDegree < 10) minDegree = 10;
                                                for (i[10] = i[9]; i[10] < 10; i[10]++) {
                                                    if (i[10] > 0 && minDegree < 9) minDegree = 9;
                                                    for (i[11] = i[10]; i[11] < 10; i[11]++) {
                                                        if (i[11] > 0 && minDegree < 8) minDegree = 8;
                                                        for (i[12] = i[11]; i[12] < 10; i[12]++) {
                                                            if (i[12] > 0 && minDegree < 7) minDegree = 7;
                                                            for (i[13] = i[12]; i[13] < 10; i[13]++) {
                                                                if (i[13] > 0 && minDegree < 6) minDegree = 6;
                                                                for (i[14] = i[13]; i[14] < 10; i[14]++) {
                                                                    if (i[14] > 0 && minDegree < 5) minDegree = 5;
                                                                    for (i[15] = i[14]; i[15] < 10; i[15]++) {
                                                                        if (i[15] > 0 && minDegree < 4) minDegree = 4;
                                                                        for (i[16] = i[15]; i[16] < 10; i[16]++) {
                                                                            if (i[16] > 0 && minDegree < 3) minDegree = 3;
                                                                            for (i[17] = i[16]; i[17] < 10; i[17]++) {
                                                                                if (i[16] > 0 && minDegree < 2) minDegree = 2;
                                                                                for (i[18] = i[17] == 0 ? 1 : i[17]; i[18] < 10; i[18]++) {

                                                                                    if (minDegree <= lenN) {
                                                                                        byte minIndex = (byte) (19 - minDegree);
                                                                                        for (byte degree = minDegree; degree <= lenN; degree++) {
                                                                                            long l = 0;
                                                                                            for (byte j = minIndex; j < 19; j++) {
//                                                                                                if (i[j] != 0) {
                                                                                                    if (i[j] == 1) {
                                                                                                        l += 1;
                                                                                                    } else {
                                                                                                        l += arraySquare[degree - 1][i[j] - 2];
                                                                                                    }
//                                                                                                }
                                                                                            }
                                                                                            if (l < N) {
                                                                                                if (!set.contains(l))
                                                                                                    if ((l + "").length() == degree) {
                                                                                                        if (equalsNumbers(i, l)) set.add(l);
                                                                                                    }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        Object[] res = set.toArray();

        long[] result = new long[set.size() - 1];
        for (int j = 0; j < result.length; j++) {
            if ((long) res[j] != 0) {
                result[j] = (long) res[j];
            }
        }
        Arrays.sort(result);
        return result;
    }

    public static void main(String[] args) {
        long freeMemoryStart = Runtime.getRuntime().freeMemory();
        Date dateStart = new Date();
        long[] result = getNumbers(Long.MAX_VALUE);
        for (int i = 0; i < result.length; i++)
            System.out.println(result[i]);
        Date dateEnd = new Date();
        System.out.println("Time has passed " + (dateEnd.getTime() - dateStart.getTime()) + " ms.");
        long freeMemoryEnd = Runtime.getRuntime().freeMemory();
        System.out.println((freeMemoryStart - freeMemoryEnd) / 1048576);

    }
}
