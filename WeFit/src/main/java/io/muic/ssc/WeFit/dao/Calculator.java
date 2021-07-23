package io.muic.ssc.WeFit.dao;

public class Calculator {



    public int needCalPerDay(String gender){
        if ("male".equalsIgnoreCase(gender)){
            return 2500;
        }
        else{
            return 2000;
        }
    }

    public int suggestedCal(int burnCal, int consume, int need){
        return need - (consume - burnCal);
    }
}
