package com.dwt.training.imports.imp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CsvFileImporterTest {


    @Mock
    Function<String , List<String>> fileReader;

    @InjectMocks
    CsvFileImporter csvFileImporter;

    @Test
    void shouldSkipEmptyLines(){
        when(fileReader.apply(anyString()))
                .thenReturn(Arrays.asList("" , System.lineSeparator()));
        assertThat(csvFileImporter.importFromFile("test"))
                .hasSize(0);
    }
}