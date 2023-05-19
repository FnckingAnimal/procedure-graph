package com.example.graph.utils;

import java.util.Collection;

public class Utils {
    public static Boolean isNull(Object o){
        return null == o;
    }
    public static Boolean isEmpty(Collection<Object> c){
        return isNull(c) || c.isEmpty();
    }
}
