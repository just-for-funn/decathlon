package com.dwt.training;


import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.range;

public class PositionCalculator {

    public List<List<Integer>> getPositions(List<Integer> args) {
        List<Integer> sorted = args.stream().sorted().collect(Collectors.toList());

       CustomMultiValueMap map = new CustomMultiValueMap();

        range(0,sorted.size()).forEach( index -> {
            map.put(sorted.get(index) , index+1 );
        });

        return args.stream()
                .map(o-> map.get(o ))
                .collect(Collectors.toList());
    }


    static class CustomMultiValueMap{
        Map<Integer , List<Integer>> myMap = new HashMap<>();

        List<Integer> get(Integer key)
        {
            return myMap.getOrDefault(key , Collections.emptyList() );
        }

        void put(Integer key , Integer val)
        {
            myMap.computeIfAbsent(key , k -> new ArrayList<>()).add(val);
        }
    }
}
