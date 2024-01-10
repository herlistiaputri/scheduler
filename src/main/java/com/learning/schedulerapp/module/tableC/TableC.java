package com.learning.schedulerapp.module.tableC;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "table_c")
public class TableC {
    @Id
    private Integer id;
    private String name;
    private String employeeNumber;
    private String department;
    private LocalDateTime lastModified;
}
