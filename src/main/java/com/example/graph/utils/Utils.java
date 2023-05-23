package com.example.graph.utils;

import java.util.Collection;

public class Utils {
    public static Boolean isNull(Object o) {
        return null == o;
    }

    public static Boolean isNotNull(Object o) {
        return !isNull(o);
    }

    public static Boolean isEmpty(Collection c) {
        return isNull(c) || c.isEmpty();
    }

    public static Boolean isNotEmpty(Collection c) {
        return !isEmpty(c);
    }
}
