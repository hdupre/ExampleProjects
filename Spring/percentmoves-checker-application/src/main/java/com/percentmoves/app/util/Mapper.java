package com.percentmoves.app.util;

import java.util.Comparator;
import java.util.Map;


public class Mapper implements Comparator {

    private Map _data;

    public Mapper(Map data) {
        super();
        _data = data;
    }

    public int compare(Object o1, Object o2) {
        Double e1 = Math.abs((Double) _data.get(o1));
        Double e2 = Math.abs((Double) _data.get(o2));
        int compare = e2.compareTo(e1);
        if (compare == 0) {
            Integer a = o1.hashCode();
            Integer b = o2.hashCode();
            return b.compareTo(a);
        }
        return compare;
    }
}
