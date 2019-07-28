package com.dwt.training.imports;

import java.util.List;

public interface FileImporter {
    List<UserScore> importFromFile(String fileName);
}
