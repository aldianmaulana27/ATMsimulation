package com.atm.simulation.service.impl;

import com.atm.simulation.entity.Account;
import com.atm.simulation.repository.FileRepository;
import com.atm.simulation.service.FileReaderService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class FileReaderServiceImpl implements FileReaderService {

    private FileRepository fileRepository;

    public FileReaderServiceImpl(FileRepository fileRepository){
        this.fileRepository = fileRepository;
    }

    @Override
    public List<Account> uploadDoc(String value) {
        Path path = Path.of(value);
        try (InputStream stream = Files.newInputStream(path)) {
            InputStreamReader ips = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(ips);

            List<Account> accounts = br.lines().skip(1).map(line -> {
                List<String> data = List.of(line.split(";"));
                return new Account(Integer.parseInt(data.get(3)), data.get(1), data.get(0), Integer.parseInt(data.get(2)));
            }).collect(Collectors.toList());
            fileRepository.addDataFile(accounts);
            return accounts;
        } catch (IOException e) {
            throw new RuntimeException("file not found");
        }
    }
}
