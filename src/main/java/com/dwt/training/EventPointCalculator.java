package com.dwt.training;

public class EventPointCalculator {

    public static int calculate(Event event, double score)
    {

        if(requireScoreInCm(event))
        {
            score = score * 100;
        }

         Double diff;
         if(event.isTracEvent())
             diff = event.getPoinstContants().B -score;
         else
             diff = score - event.getPoinstContants().B;

         return (int)(Math.pow( diff , event.getPoinstContants().C) * event.getPoinstContants().A);
    }

    private static boolean requireScoreInCm(Event event) {
        return event == Event.HIGH_JUMP || event == Event.LONG_JUMP || event == Event.POLE_VAULT;
    }


}
