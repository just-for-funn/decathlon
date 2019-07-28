package com.dwt.training.event;

import java.util.function.Function;

public class EventFactory {
    public static Event metres100() {
        return new Event(25.4347,18,1.81 , true , EventType.METRES_100);
    }

    public static Event longJump() {
        return new Event(0.14354,220,1.4 , false , EventType.LONG_JUMP , meterToCM());
    }

    public static Event shotPut() {
        return new Event(51.39,1.5,1.05 , false , EventType.SHOT_PUT);
    }

    public static Event highJump() {
        return new Event(0.8465,75,1.42 ,false , EventType.HIGH_JUMP , meterToCM());
    }

    public static Event metres400() {
        return new Event(1.53775,82,1.81 , true , EventType.METRES_400);
    }

    public static Event metresHurdles100() {
        return new Event(5.74352,28.5,1.92 , true , EventType.METRES_HURDLES_110);
    }

    public static Event discusThrow() {
        return new Event(12.91,4,1.1 , false , EventType.DISCUS_THROW);
    }

    public static Event poleVault() {
        return new Event(0.2797,100,1.35 , false , EventType.POLE_VAULT , meterToCM());
    }

    public static Event javelinThrow() {
        return new Event(10.14,7,1.08 , false , EventType.JAVELIN_THROW);
    }

    public static Event metres1500() {
        return new Event(0.03768,480,1.85 , true , EventType.METRES_1500);
    }

    private static Function<Double, Double> meterToCM() {
        return meter -> meter * 100;
    }
}
