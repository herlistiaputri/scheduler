package com.learning.schedulerapp.module.tableA;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "table_a")
public class TableA {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private LocalDateTime lastModified;
}
