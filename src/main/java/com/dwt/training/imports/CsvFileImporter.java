package com.dwt.training.imports;

import lombok.SneakyThrows;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CsvFileImporter {

    String fileName;

    public CsvFileImporter(String fileName) {
        this.fileName = fileName;
    }

    @SneakyThrows
    public List<UserScore> importIt(){
        return Files.readAllLines(Paths.get(new URI(fileName))).stream()
                .map(line -> CsvLineConverter.convert(line))
                .collect(Collectors.toList());
    }
}
