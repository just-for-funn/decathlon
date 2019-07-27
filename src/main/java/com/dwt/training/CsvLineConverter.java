package com.dwt.training;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CsvLineConverter {

    public static final String DELIMETER = ";";
    String line;

    public CsvLineConverter(String line) {
        this.line = line;
    }

    UserScore convert(){
        String [] splitted = this.line.split(DELIMETER);
        List<EventScore> scores = readScores(splitted);

        return UserScore.builder()
                .name(splitted[0])
                .scoreList(scores)
                .build();
    }

    private List<EventScore> readScores(String[] splitted) {
        return IntStream.range(0,splitted.length)
                .skip(1)
                .mapToObj(index-> this.convertEventScore(index , splitted[index] ))
                .collect(Collectors.toList());
    }

    private  EventScore convertEventScore(int index ,String s)
    {
        return EventScore.builder()
                .event(Event.values()[index-1])
                .score(0.0)
                .build();
    }

    static UserScore convert(String line)
    {
        return new CsvLineConverter(line).convert();
    }


    @Builder
    @Data
    static class UserScore{
        private String name;
        private List<EventScore> scoreList = Collections.emptyList();
    }

    @Builder
    @Data
    static class EventScore{
        private Event event;
        private double score;
    }
}
