package com.dwt.training.imports;

import com.dwt.training.event.Event;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EventScore {
    private Event event;
    private double score;
}
