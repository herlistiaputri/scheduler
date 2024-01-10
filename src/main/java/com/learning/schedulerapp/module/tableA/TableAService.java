package com.learning.schedulerapp.module.tableA;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Scanner;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TableAService {

    private final TableARepository tableARepository;
    private String pathA = "D:\\Project\\Private\\Learn\\document\\table_a.csv";

    public void readReference() throws IOException {
        try{
            File file = new File(pathA);
            log.info("Read reference from file " + file.getName());
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                readLine(scanner);
            }
            scanner.close();
        } catch (Exception e) {
            log.error("ERROR : {} ", e.getMessage());
        }
    }

    public void readLine(Scanner scanner) {
        String line = scanner.nextLine();
        String[] values = line.split(",");

        Optional<TableA> tableAOptional = tableARepository.findById(Integer.parseInt(values[0]));
        TableA tableA = new TableA();
        if(tableAOptional.isPresent()) {
            tableA = tableAOptional.get();
        }

        tableA.setId(Integer.parseInt(values[0]));
        tableA.setFirstName(values[1]);
        tableA.setLastName(values[2]);
        tableA.setEmail(values[3]);
        tableA.setGender(values[4]);
        tableA.setLastModified(LocalDateTime.now());

        tableARepository.save(tableA);
    }

}
