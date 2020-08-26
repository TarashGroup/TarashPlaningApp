package com.example.myclock;


public class GetDay {
    static Long getDay () {
        long time = System.currentTimeMillis();
        return time / 60 / 60 / 24;
    }
    static Long getDay (long time) {
        return time / 60 / 60 / 24;
    }
    static Long toTime (Long dayAsInteger) {
        return dayAsInteger * 60 * 60 * 24;
    }
}
