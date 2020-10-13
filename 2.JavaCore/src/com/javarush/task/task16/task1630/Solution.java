package com.javarush.task.task16.task1630;

import java.io.*;

public class Solution {
    public static String firstFileName;
    public static String secondFileName;

    //add your code here - добавьте код тут
    static {
        BufferedReader readerFileName = new BufferedReader(new InputStreamReader(System.in));
        try {
            firstFileName = readerFileName.readLine();
            secondFileName = readerFileName.readLine();
            readerFileName.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException, FileNotFoundException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        //add your code here - добавьте код тут
        f.join();
        System.out.println(f.getFileContent());
    }

    public interface ReadFileInterface {

        void setFileName(String fullFileName);

        String getFileContent() throws FileNotFoundException;

        void join() throws InterruptedException;

        void start();
    }

    //add your code here - добавьте код тут
    public static class ReadFileThread extends Thread implements ReadFileInterface {
        String fileName;
        String fileContent = "";

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFileContent() {
            return this.fileContent;
        }

        public void run() {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(fileName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                while (reader.ready()) {
                    fileContent += reader.readLine() + " ";
                }
                reader.close();
            } catch (IOException e) {
            }

        }
    }
}
