package com.dwt.training.imports;

import lombok.Builder;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Builder
@Data
class UserScore {
    private String user;
    private List<EventScore> scores = Collections.emptyList();
}
