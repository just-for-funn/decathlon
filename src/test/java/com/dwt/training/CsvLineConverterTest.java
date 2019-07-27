package com.dwt.training;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.dwt.training.Event.*;
import static org.assertj.core.api.Assertions.assertThat;

class CsvLineConverterTest {
    static String A_LINE = "John Smith;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5.25.72";



    @Test
    void shouldConvertEventsInCorrectOrder(){
        CsvLineConverter.UserScore score = CsvLineConverter.convert(A_LINE);
        List<Event> events = score.getScoreList()
                .stream()
                .map(CsvLineConverter.EventScore::getEvent)
                .collect(Collectors.toList());
        assertThat(events).containsExactly(
                METRES_100,
                LONG_JUMP,
                SHOT_PUT,
                HIGH_JUMP,
                METRES_400,
                METRES_HURDLES_110,
                DISCUS_THROW,
                POLE_VAULT,
                JAVELIN_THROW,
                METRES_1500);
    }

}