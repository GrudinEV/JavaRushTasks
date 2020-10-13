package com.javarush.task.task17.task1721;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();
        BufferedReader rf1 = new BufferedReader(new FileReader(fileName1));
        while (rf1.ready()) allLines.add(rf1.readLine());
        rf1.close();
        BufferedReader rf2 = new BufferedReader(new FileReader(fileName2));
        while (rf2.ready()) forRemoveLines.add(rf2.readLine());
        rf2.close();
        System.out.println(allLines);
        System.out.println(forRemoveLines);
        try {
            new Solution().joinData();
        } catch (CorruptedDataException e) {
        }
        System.out.println(allLines);
    }

    public void joinData() throws CorruptedDataException {
        if (allLines.containsAll(forRemoveLines)) {
            allLines.removeAll(forRemoveLines);
        } else {
            allLines.clear();
            throw new CorruptedDataException();
        }

    }
}
