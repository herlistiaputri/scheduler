package com.learning.schedulerapp.module.tableA;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TableARepository extends JpaRepository<TableA, Integer> {


}
