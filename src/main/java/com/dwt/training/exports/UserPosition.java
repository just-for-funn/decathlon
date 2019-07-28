package com.dwt.training.exports;

import com.dwt.training.event.EventType;
import lombok.Builder;
import lombok.Data;

import java.util.Collections;
import java.util.List;


@Builder
@Data
public class UserPosition {

    private String user;
    private List<Integer> position = Collections.emptyList();
    private List<EventAndScore> scores = Collections.emptyList();
    private int points;
    @Builder
    @Data
    public static class EventAndScore{
        EventType event;
        Double score;
    }
}
