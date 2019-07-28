package com.dwt.training.imports;

import lombok.Builder;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Builder
@Data
public class UserScore {
    private String user;
    private List<EventScore> scores = Collections.emptyList();

    public int getTotalPoints() {
        return scores.stream()
                .map(ev-> ev.getEvent().calculatePoints(ev.getScore()))
                .reduce(0 , (a,b)-> a+b);
    }
}
