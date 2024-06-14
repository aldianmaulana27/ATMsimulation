package com.atm.simulation.util;

import java.util.Scanner;

public class InputUtil {

    private Scanner scanner = new Scanner(System.in);

    public String inputString(String info){
        System.out.println(info);
        String data = scanner.nextLine();
        return data;
    }
}
