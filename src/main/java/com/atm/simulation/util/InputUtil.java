package com.atm.simulation.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class InputUtil {

    private Scanner scanner = new Scanner(System.in);

    public String inputString(String info) {
        System.out.println(info);
        String data = scanner.nextLine();
        return data;
    }

    public String uploadDoc(String value) {
        Path path = Path.of(value);
        try (InputStream stream = Files.newInputStream(path)) {
            StringBuilder builder = new StringBuilder();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = stream.read(buffer)) != -1) {
                builder.append(new String(buffer, 0, length));
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("file not found");
        }
    }
}