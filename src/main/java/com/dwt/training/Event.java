package com.dwt.training;

import lombok.Builder;
import lombok.Data;

public enum Event {
    METRES_100(25.4347,18,1.81 , true),
    LONG_JUMP(0.14354,220,1.4 , false),
    SHOT_PUT(51.39,1.5,1.05 , false),
    HIGH_JUMP(0.8465,75,1.42 ,false ),
    METRES_400(1.53775,82,1.81 , true),
    METRES_HURDLES_110(5.74352,28.5,1.92 , true),
    DISCUS_THROW(12.91,4,1.1 , false),
    POLE_VAULT(0.2797,100,1.35 , false),
    JAVELIN_THROW(10.14,7,1.08 , false),
    METRES_1500(0.03768,480,1.85 , true);


    private PointConstants constants;
    private boolean isTracEvent;
    Event(double A , double B, double C , boolean isTracEvent) {
        this.constants = new PointConstants(A , B , C);
        this.isTracEvent = isTracEvent;
    }

    PointConstants getPoinstContants(){
        return this.constants;
    }

    boolean isTracEvent(){
        return this.isTracEvent;
    }


    @Data
    @Builder
    static class PointConstants{
        Double A;
        Double B;
        Double C;
    }
}
