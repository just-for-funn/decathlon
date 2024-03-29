package com.dwt.training.imports.imp;

import com.dwt.training.event.Event;
import com.dwt.training.event.EventType;
import com.dwt.training.imports.EventScore;
import com.dwt.training.imports.UserScore;
import com.dwt.training.imports.imp.CsvLineConverter;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.dwt.training.event.EventType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CsvLineConverterTest {
    static String A_LINE = "John Smith;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5.25.72";
    static String A_LINE_WITHOUT_MINUTES = "John Smith;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;25.72";



    @Test
    void shouldThrowErrorWhenLineHasMissingValues(){
        String invalidLine = "John Smith;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81";
        assertThrows(IllegalArgumentException.class , ()-> CsvLineConverter.convert(invalidLine));
    }


    @Test
    void shouldConvertEventsInCorrectOrder(){
        UserScore score = CsvLineConverter.convert(A_LINE_WITHOUT_MINUTES);
        List<EventType> events = score.getScores()
                .stream()
                .map(EventScore::getEvent)
                .map(Event::getType)
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

    @Test
    public void shouldReadScoresWhenThereAreNoMinutes(){
        UserScore score = CsvLineConverter.convert(A_LINE_WITHOUT_MINUTES);
        List<Double> scoreValues = score.getScores().stream()
                .map(EventScore::getScore)
                .collect(Collectors.toList());

        assertThat(scoreValues )
                .containsExactly(12.61,5.00,9.22,1.50,60.39,16.43,21.60,2.60,35.81,25.72);
    }

    @Test
    public void shouldReadScoresWithMinutes(){
        UserScore score = CsvLineConverter.convert(A_LINE);
        double scoreWithMinutes = score.getScores().get(9).getScore();

        assertThat(scoreWithMinutes)
                .isEqualTo(325.72);
    }

}