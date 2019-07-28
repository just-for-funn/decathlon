package com.dwt.training.exports;

import java.util.List;

public interface FileExporter {
    void export(List<UserPosition> users , String fileName);
}
