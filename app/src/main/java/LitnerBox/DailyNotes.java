package LitnerBox;

import com.example.myclock.Database.Note;

import java.util.ArrayList;
import java.util.Date;

public class DailyNotes {
    private ArrayList<Note> notes = new ArrayList<>();
    private Date date;

    public DailyNotes(Date date) {
        this.date = date;
    }
}
