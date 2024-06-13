package com.atm.simulation.util;

import com.atm.simulation.entity.Account;
import com.atm.simulation.view.TransactionView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Objects;

public class ValidationUtil {

    public boolean checkLength(String account, String pin){
        if(account.length() != 6){
            System.out.println("Account Number should have 6 digits length");
        }else if(pin.length() != 6){
            System.out.println("PIN should have 6 digits length");
        }else{
            return true;
        }
        return false;
    }

    public void listener(Integer accNo){
        JFrame frame = new JFrame("Key Listener Example");
        JPanel panel = new JPanel();
        JTextField textField = new JTextField(20);
        JLabel label1 = new JLabel("close window after press esc to close or after input account numb destination");
        JLabel label2 = new JLabel(" or ");
        JLabel label3 = new JLabel("after input account numb destination");
        JLabel label4 = new JLabel("and press enter in terminal");
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Check if ESC key is pressed (key code 27)
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.out.println("esc");
                    moveChar("esc");
                }else {
                    char typedChar = e.getKeyChar(); // Get the typed character
                    String typedString = String.valueOf(typedChar);
                    moveChar(typedString);
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                // Unused
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Unused
            }
        });

        panel.add(textField);
        panel.add(label1, BorderLayout.CENTER);
        panel.add(label2, BorderLayout.CENTER);
        panel.add(label3, BorderLayout.CENTER);
        panel.add(label4, BorderLayout.CENTER);
        frame.add(panel);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);

    }

    private String value ="";
    public String getValue(){
        return value = value;
    }
    public void setValue(String value){
        this.value = value;
    }
    public void moveChar(String chars){
        value += chars;
    }

    public boolean isNumber(String account, String pin) {
       if(!isNumeric(account)){
           System.out.println("Account Number should only contains numbers");
       }else if(!isNumeric(pin)){
           System.out.println("PIN should only contains numbers");
       }else{
           return true;
       }
       return false;
    }
    public boolean isNumeric(String str) {
        return str.matches("-?\\d+?");  // Matches integers and decimals
    }

    public boolean validateSameAccountNumb(List<Account> accounts, Integer accNo){
        for (Account account:accounts){
            return account.getAccountNumber().equals(accNo);
        }
        return false;
    }

    public boolean validateSameAccount(List<Account> accounts, Account acc){
        for (Account account:accounts){
            return Objects.equals(account, acc);
        }
        return false;
    }

}
