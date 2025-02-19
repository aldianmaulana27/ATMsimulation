package com.atm.simulation.util;

import com.atm.simulation.entity.Account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputUtil {

    private Scanner scanner = new Scanner(System.in);

    public String inputString(String info){
        System.out.println(info);
        String data = scanner.nextLine();
        return data;
    }

    // move this
    public List<Account> uploadDoc(String value) {
        Path path = Path.of(value);
        try (InputStream stream = Files.newInputStream(path)) {
            InputStreamReader ips = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(ips);

            List<Account> accounts = br.lines().skip(1).map(line -> {
                List<String> data = List.of(line.split(";"));
                return new Account(Integer.parseInt(data.get(3)), data.get(1), data.get(0), Integer.parseInt(data.get(2)));
            }).collect(Collectors.toList());
            return accounts;
        } catch (IOException e) {
            throw new RuntimeException("file not found");
        }
    }
}
