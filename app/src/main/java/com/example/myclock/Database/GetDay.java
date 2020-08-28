package com.example.myclock.Database;


public class GetDay {
    static public Long getDay () {
        long time = System.currentTimeMillis();
        return time / 60 / 60 / 24/1000;
    }
    static public Long getDay (long timeInMillis) {
        return timeInMillis / 60 / 60 / 24/1000;
    }
    static public Long toTime (Long dayAsInteger) {
        return dayAsInteger * 60 * 60 * 24;
    }
}
