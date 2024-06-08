package com.atm.simulation.util;

public class ValidationUtil {

    public static boolean checkLength(String account, String pin){
        if(account.length() != 6){
            System.out.println("Account Number should have 6 digits length");
        }else if(pin.length() != 6){
            System.out.println("PIN should have 6 digits length");
        }else{
            return true;
        }
        return false;
    }

    public static boolean isNumber(String account, String pin) {
       if(!isNumeric(account)){
           System.out.println("Account Number should only contains numbers");
       }else if(!isNumeric(pin)){
           System.out.println("PIN should only contains numbers");
       }else{
           return true;
       }
       return false;
    }
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+?");  // Matches integers and decimals
    }
}
