package com.javarush.task.task21.task2101;

import org.w3c.dom.ls.LSOutput;

/*
Определяем адрес сети
*/
public class Solution {
    public static void main(String[] args) {
        byte[] ip = new byte[]{(byte) 192, (byte) 168, 1, 2};
        byte[] mask = new byte[]{(byte) 255, (byte) 255, (byte) 254, 0};
        byte[] netAddress = getNetAddress(ip, mask);
        print(ip);          //11000000 10101000 00000001 00000010
        print(mask);        //11111111 11111111 11111110 00000000
        print(netAddress);  //11000000 10101000 00000000 00000000
    }

    public static byte[] getNetAddress(byte[] ip, byte[] mask) {
        byte[] netAddress = new byte[4];
        for (byte i = 0; i < ip.length; i++) {
            netAddress[i] = (byte) ((ip[i]) & (mask[i]));
        }
        return netAddress;
    }

    public static void print(byte[] bytes) {
        for (byte b : bytes) {
//            String str = "";
//            for (int i = 0; i < 8 - (b < 0 ? Integer.toBinaryString(b + 256) : Integer.toBinaryString(b)).length(); i++) {
//                str += "0";
//            }
            System.out.print(String.format("%8s", Integer.toBinaryString(b & 255), " ").replace(" ", "0")); /*b < 0 ? str + Integer.toBinaryString(b + 256) + " " : str + Integer.toBinaryString(b) + " "*/
            System.out.print(" ");
        }
        System.out.println("");
    }
}
