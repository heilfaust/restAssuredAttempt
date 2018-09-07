package com.SIS.test.common;

import java.util.concurrent.ThreadLocalRandom;

public class GeneratePortfolioName {

    public int genRand(){
        int min = 1;
        int max = 9999;
        int index;
        return index = ThreadLocalRandom.current().nextInt(min, max + 1);

    }

    public String genName(){
        return "hx_arolon_portf_"+genRand();
    }
}
