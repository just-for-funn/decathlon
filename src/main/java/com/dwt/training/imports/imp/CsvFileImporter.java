package com.dwt.training.imports.imp;

import com.dwt.training.imports.FileImporter;
import com.dwt.training.imports.UserScore;
import lombok.SneakyThrows;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CsvFileImporter implements FileImporter {

    @SneakyThrows
    @Override
    public List<UserScore> importFromFile(String fileName) {
        return Files.readAllLines(new File(fileName).toPath()).stream()
                .map(line -> CsvLineConverter.convert(line))
                .collect(Collectors.toList());
    }
}
