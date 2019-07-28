package com.dwt.training;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class EventPointCalculatorTest {

    @ParameterizedTest
    @MethodSource("scoreAndPoinst")
    public void shouldCalculatePoints(Event event , Double score ){
        int points = EventPointCalculator.calculate(event , score);

        assertThat(points)
                .isEqualTo(1000);
    }

    static Stream<Arguments> scoreAndPoinst() {
        return Stream.of(
                Arguments.of(Event.METRES_100 , 10.395 ),
                Arguments.of(Event.SHOT_PUT , 18.4 ),
                Arguments.of(Event.METRES_400 , 46.17 ),
                Arguments.of(Event.METRES_HURDLES_110 , 13.8 ),
                Arguments.of(Event.DISCUS_THROW , 56.17 ),
                Arguments.of(Event.JAVELIN_THROW , 77.19 ),
                Arguments.of(Event.METRES_1500 , 233.79 )
        );
    }

    /**
     * Remaining values not match with wikipedi
     */

    @Test
    public void shouldCalculateForLongJump(){
        int points = EventPointCalculator.calculate(Event.LONG_JUMP , 7.76);
        assertThat(points)
                .isEqualTo(1000);
    }

    @Test
    public void shouldCalculateForHighJump(){
        int points = EventPointCalculator.calculate(Event.HIGH_JUMP , 2.20);
        assertThat(points)
                .isEqualTo(992);
    }

    @Test
    public void shouldCalculateForPoleValult(){
        int points = EventPointCalculator.calculate(Event.POLE_VAULT , 5.28);
        assertThat(points)
                .isEqualTo(998);
    }
}