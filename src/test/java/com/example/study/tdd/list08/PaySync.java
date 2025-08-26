package com.example.study.tdd.list08;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

//@RequiredArgsConstructor
public class PaySync {

    @Setter
    private PayInfoDao payInfoDao = new PayInfoDao();
    private String filePath = "D:\\data\\pay\\cp0001.csv";

    public void sync(String filepath) throws IOException {
        Path path = Paths.get(filepath);

        List<PayInfo> payInfos = Files.lines(path)
                .map(line -> {
                    String[] data = line.split(",");
                    return new PayInfo(data[0], data[1], Integer.parseInt(data[2]));
                })
                .collect(Collectors.toList());

        payInfos.forEach(pi -> payInfoDao.insert(pi));
    }
}
