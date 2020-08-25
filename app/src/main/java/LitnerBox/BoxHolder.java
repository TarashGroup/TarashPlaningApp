package LitnerBox;

import android.icu.util.LocaleData;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class BoxHolder {
    private final Integer B1 = 1;
    private final Integer B2 = 2;
    private final Integer B3 = 4;
    private final Integer B4 = 8;
    private final Integer B5 = 16;
    private ArrayList<DailyNotes> Box1 = new ArrayList<>();
    private ArrayList<DailyNotes> Box2 = new ArrayList<>();
    private ArrayList<DailyNotes> Box3 = new ArrayList<>();
    private ArrayList<DailyNotes> Box4 = new ArrayList<>();
    private ArrayList<DailyNotes> Box5 = new ArrayList<>();
    private GregorianCalendar date;


    private void tick () {
    }

    private void load_from_database () {
        // TODO after database
    }
}
