package com.dwt.training;

import com.dwt.training.event.Event;
import com.dwt.training.event.EventFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.dwt.training.event.EventFactory.*;
import static org.assertj.core.api.Assertions.assertThat;

class EventPointCalculatorTest {

    @ParameterizedTest
    @MethodSource("scoreAndPoinst")
    public void shouldCalculatePoints(Event event , Double score ){
        int points = event.calculate(event , score);
        assertThat(points)
                .isEqualTo(1000);
    }

    static Stream<Arguments> scoreAndPoinst() {
        return Stream.of(
                Arguments.of(metres100(), 10.395 ),
                Arguments.of(shotPut() , 18.4 ),
                Arguments.of(metres400() , 46.17 ),
                Arguments.of(metresHurdles100() , 13.8 ),
                Arguments.of(discusThrow() , 56.17 ),
                Arguments.of(javelinThrow() , 77.19 ),
                Arguments.of(metres1500() , 233.79 )
        );
    }

    /**
     * Remaining values not match with wikipedi
     */

    @Test
    public void shouldCalculateForLongJump(){
        int points = longJump().calculate(longJump(), 7.76);
        assertThat(points)
                .isEqualTo(1000);
    }

    @Test
    public void shouldCalculateForHighJump(){
        int points = highJump().calculate(highJump() , 2.20);
        assertThat(points)
                .isEqualTo(992);
    }

    @Test
    public void shouldCalculateForPoleValult(){
        int points = poleVault().calculate(poleVault(), 5.28);
        assertThat(points)
                .isEqualTo(998);
    }
}