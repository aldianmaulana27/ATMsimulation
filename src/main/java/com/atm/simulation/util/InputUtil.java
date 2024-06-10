package com.atm.simulation.util;

import java.util.Scanner;

public class InputUtil {

    private static Scanner scanner = new Scanner(System.in);

    public static String inputString(String info){
        System.out.println(info);
        String data = scanner.nextLine();
        return data;
    }
    public static Integer integerConvert(String input){
        return Integer.parseInt(input);
    }

}
