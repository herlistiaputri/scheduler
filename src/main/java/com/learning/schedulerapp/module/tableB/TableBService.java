package com.learning.schedulerapp.module.tableB;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Scanner;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TableBService {

    private final TableBRepository tableBRepository;
    private String pathB = "D:\\Project\\Private\\Learn\\document\\table_b.csv";

    public void readReference() throws IOException {
        try{
            File file = new File(pathB);
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

        Optional<TableB> tableBOptional = tableBRepository.findById(Integer.parseInt(values[0]));
        TableB tableB = new TableB();
        if(tableBOptional.isPresent()) {
            tableB = tableBOptional.get();
        }

        tableB.setId(Integer.parseInt(values[0]));
        tableB.setName(values[1]);
        tableB.setGender(values[2]);
        tableB.setOccupation(values[3]);
        tableB.setLastModified(LocalDateTime.now());

        tableBRepository.save(tableB);
    }
}
