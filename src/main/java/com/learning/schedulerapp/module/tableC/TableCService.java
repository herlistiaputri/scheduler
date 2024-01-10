package com.learning.schedulerapp.module.tableC;

import com.learning.schedulerapp.module.tableB.TableB;
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
public class TableCService {

    private final TableCRepository tableCRepository;
    private String pathC = "D:\\Project\\Private\\Learn\\document\\table_c.csv";

    public void readReference() throws IOException {
        try{
            File file = new File(pathC);
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

        Optional<TableC> tableCOptional = tableCRepository.findById(Integer.parseInt(values[0]));
        TableC tableC = new TableC();
        if(tableCOptional.isPresent()) {
            tableC = tableCOptional.get();
        }

        tableC.setId(Integer.parseInt(values[0]));
        tableC.setName(values[1]);
        tableC.setEmployeeNumber(values[2]);
        tableC.setDepartment(values[3]);
        tableC.setLastModified(LocalDateTime.now());

        tableCRepository.save(tableC);
    }
}
