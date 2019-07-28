package com.dwt.training;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.IntStream.range;
import static org.assertj.core.api.Assertions.assertThat;

class PositionCalculatorTest {

    PositionCalculator positionCalculator = new PositionCalculator();

    @Test
    public void shouldReturnNormalOrderForNonEqualInput(){
        List<List<Integer>> positions =  positionCalculator.getPositions(asList(300,100,200));
        assertPositions(positions,  asList(3), asList(1) , asList(2));
    }

    @Test
    void shouldReturnSameValuesForMultipleOccurencesofInput(){
        List<List<Integer>> positions =  positionCalculator.getPositions(asList(100,100,100));
        assertPositions(positions,  asList(1,2,3), asList(1,2,3) , asList(1,2,3));
    }

    @Test
    void shouldWorkForMixedInputs()
    {
        List<List<Integer>> positions =  positionCalculator.getPositions(asList(100,100,50 , 200));
        assertPositions(positions,  asList(2,3), asList(2,3) , asList(1) , asList(4));
    }


    private void assertPositions(List<List<Integer>> data, List<Integer> ... assertions) {
        assertThat(data.size()).isEqualTo( assertions.length);

        range(0 , data.size()).forEach(index->{
            assertThat(data.get(index))
                    .containsAll(assertions[index]);
        });
    }
}