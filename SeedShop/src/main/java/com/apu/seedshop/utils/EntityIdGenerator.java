package com.apu.seedshop.utils;

import java.util.UUID;

public class EntityIdGenerator {
    public static Long random(){
        Long l = UUID.randomUUID().getLeastSignificantBits();
        if(l<0){
            l=-l;
        }
        return l;
    }
}
