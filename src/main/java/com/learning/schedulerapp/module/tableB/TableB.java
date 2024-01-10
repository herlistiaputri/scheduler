package com.learning.schedulerapp.module.tableB;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "table_b")
public class TableB {

    @Id
    private Integer id;
    private String name;
    private String gender;
    private String occupation;
    private LocalDateTime lastModified;
}
