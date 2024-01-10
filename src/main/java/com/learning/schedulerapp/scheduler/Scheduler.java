package com.learning.schedulerapp.scheduler;

import com.learning.schedulerapp.module.tableA.TableAService;
import com.learning.schedulerapp.module.tableB.TableBService;
import com.learning.schedulerapp.module.tableC.TableCService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
@Slf4j
public class Scheduler {

    private final TableAService tableAService;
    private final TableBService tableBService;
    private final TableCService tableCService;

    private final ExecutorService customThreadPool = Executors.newFixedThreadPool(10);

    @Scheduled(cron = "0 * * * * *")
    public void myScheduledTask() throws ExecutionException, InterruptedException {
        log.info("Scheduler started on : " + LocalDateTime.now());

        /* Change or add this CompletableFuture<Void> for the reference manager type you've added */
        CompletableFuture<Void> tableA = CompletableFuture.runAsync(() -> {
            try {
                log.info("Method fmc reference manager called  by Thread : " + Thread.currentThread().getName() + "  at " + LocalDateTime.now());
                tableAService.readReference();
            } catch (IOException e) {
                log.error("Error read fmc reference caused by " + e.getMessage());
            }
        }, customThreadPool);

        CompletableFuture<Void> tableB = CompletableFuture.runAsync(() -> {
            try {
                log.info("Method fmc reference manager called  by Thread : " + Thread.currentThread().getName() + "  at " + LocalDateTime.now());
                tableBService.readReference();
            } catch (IOException e) {
                log.error("Error read fmc reference caused by " + e.getMessage());
            }
        }, customThreadPool);


        CompletableFuture<Void> tableC = CompletableFuture.runAsync(() -> {
            try {
                log.info("Method fmc reference manager called  by Thread : " + Thread.currentThread().getName() + "  at " + LocalDateTime.now());
                tableCService.readReference();
            } catch (IOException e) {
                log.error("Error read fmc reference caused by " + e.getMessage());
            }
        }, customThreadPool);
        CompletableFuture.allOf(
                tableA,
                tableB,
                tableC
        ).get();

        log.info("Scheduler ended on : " + LocalDateTime.now());
    }

}
