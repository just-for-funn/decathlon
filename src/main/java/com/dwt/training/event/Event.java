package com.dwt.training.event;

import lombok.Builder;
import lombok.Data;

import java.util.function.Function;

@Data
public class  Event
{
    private final EventType type;
    private PointConstants constants;
    private boolean isTracEvent;
    Function<Double,Double> valueNormalizer;//meter to cm stuff

    Event(double A , double B, double C , boolean isTracEvent , EventType eventType) {
        this.constants = new PointConstants(A , B , C);
        this.isTracEvent = isTracEvent;
        this.type = eventType;
        this.valueNormalizer = Function.identity();
    }

    Event(double A , double B, double C , boolean isTracEvent , EventType eventType , Function<Double,Double> valueNormalizer) {
        this.constants = new PointConstants(A , B , C);
        this.isTracEvent = isTracEvent;
        this.type = eventType;
        this.valueNormalizer = valueNormalizer;
    }

    public int calculate(Event event, double score)
    {

        double normalizedScore = valueNormalizer.apply(score);

        double  diff = 0;
        if(this.isTracEvent())
            diff = this.getConstants().B -normalizedScore;
        else
            diff = normalizedScore - event.getConstants().B;

        return (int)(Math.pow( diff , event.getConstants().C) * event.getConstants().A);
    }




    @Data
    @Builder
    static class PointConstants{
        double A;
        double B;
        double C;
    }
}
