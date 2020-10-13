package com.javarush.task.task19.task1917;

/* 
Свой FileWriter
*/

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileWriter;
import java.io.IOException;

public class FileConsoleWriter {
    private FileWriter fileWriter;

    public FileConsoleWriter(String filename) throws IOException {
            this.fileWriter = new FileWriter(filename);
    }

    public FileConsoleWriter(String filename, boolean append) throws IOException {
        this.fileWriter = new FileWriter(filename, append);
    }

    public FileConsoleWriter(File file) throws IOException {
        this.fileWriter = new FileWriter(file);
    }

    public FileConsoleWriter(File file, boolean append) throws IOException {
        this.fileWriter = new FileWriter(file, append);
    }

    public FileConsoleWriter(FileDescriptor fd) {
        this.fileWriter = new FileWriter(fd);
    }

    public void write(int c) throws IOException {
        System.out.println((char) c);
        this.fileWriter.write(c);
    }

    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = off; i < off + len; i++) {
            System.out.print(cbuf[i]);
        }
        System.out.print("\n");
        this.fileWriter.write(cbuf, off, len);
    }

    public void write(String str, int off, int len) throws IOException {
        System.out.println(str.substring(off, off + len));
        this.fileWriter.write(str, off, len);
    }

    public void close() throws IOException {
        this.fileWriter.close();
    }

    public void write(char[] cbuf) throws IOException {
        for (int i = 0; i < cbuf.length; i++) {
            System.out.print(cbuf[i]);
        }
        System.out.print("\n");
        this.fileWriter.write(cbuf);
    }

    public void write(String str) throws IOException {
        System.out.println(str);
        this.fileWriter.write(str);
    }

    public static void main(String[] args) {

    }

}
