package com.example.study.tdd.list08;

import lombok.Setter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DailyBatchLoader {

    private String basePath = ".";

    @Setter
    private Times times = new Times();

    public int load() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Path batchPath = Paths.get(basePath, date.format(formatter), "batch.txt");
//        return null;
        return 0;
    }
}
