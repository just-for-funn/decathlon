package com.dwt.training.imports.imp;

import com.dwt.training.imports.FileImporter;
import com.dwt.training.imports.UserScore;
import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CsvFileImporter implements FileImporter {

    Function<String ,  List<String>> fileReader = this::readFile;

    @Override
    public List<UserScore> importFromFile(String fileName) {
        return fileReader.apply(fileName)
                .stream()
                .filter(this::isNonEmpty)
                .map(line -> CsvLineConverter.convert(line))
                .collect(Collectors.toList());
    }

    private boolean isNonEmpty(String line) {
        if(line.length() == 0)
            return false;
        if(line .equals("\n") || line.equals("\r\n"))
            return false;
        return true;
    }

    @SneakyThrows
    private List<String> readFile(String fileName) {
        return Files.readAllLines(new File(fileName).toPath());
    }
}
