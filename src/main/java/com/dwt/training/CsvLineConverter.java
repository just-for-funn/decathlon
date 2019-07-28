package com.dwt.training;

import com.dwt.training.event.Event;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.dwt.training.event.EventFactory.*;

public class CsvLineConverter {

    public static final String DELIMETER = ";";
    String line;

    List<Event> eventsToRead = Arrays.asList(
            metres100(),
            longJump(),
            shotPut(),
            highJump(),
            metres400(),
            metresHurdles100(),
            discusThrow(),
            poleVault(),
            javelinThrow(),
            metres1500()
    );
    public CsvLineConverter(String line) {
        this.line = line;
    }

    UserScore convert(){
        String [] splitted = this.line.split(DELIMETER);
        if(splitted.length != 11)
            throw new IllegalArgumentException(String.format("Given csv line is not valid [ %s ]" , line));
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

    private  EventScore convertEventScore(int index ,String score)
    {
        return EventScore.builder()
                .event(eventsToRead.get(index-1))
                .score(this.convertScore(score))
                .build();
    }

    @SneakyThrows
    private double convertScore(String score) {
        String [] splitted =score.split("\\.");
        if(splitted.length == 3)
        {
            return  (Double.parseDouble(splitted[0]) * 60) + Double.parseDouble(splitted[1]) + (Double.parseDouble(splitted[2]) / 100);

        }else{
            return Double.parseDouble(score);
        }
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
