package com.huangty.rocketmq.General;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        System.out.println("welcome "+getName());
    }

    public static String getName(){
        System.out.println("Please input your name:");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        return s;
    }
}
