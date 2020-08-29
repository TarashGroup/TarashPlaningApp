package LitnerBox;

import com.example.myclock.Database.GetDay;
import com.example.myclock.Database.Note;
import com.example.myclock.Database.PropertyHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LitnerBox {
    public static final int DONE = 0;
    public static final int UNDONE = 1;
    public static final int WAITING_FOE_NEXT_DAY = 2;
    public static final int SHOULD_BE_READ_TODAY = 3;
    public static final int NOT_IN_BOXES = 4;

    public static final int MOVE_FROM_DONE_TO_FIRST = 5;
    public static final int MOVE_FROM_FAILED_BOXES_TO_FIRST = 6;
    public static final int MOVE_FROM_CURRENT_BOX_TO_NEXT = 7;
    public static final int MOVE_FROM_CURRENT_BOX_TO_FIRST = 8;
    public static final int MOVE_FROM_CURRENT_BOX_TO_FAILED_BOXES = 9;


    private final static List<Integer> readingDays = Arrays.asList(1,3,7,15,31);
    private ArrayList<ArrayList<Note>> boxes;
    private ArrayList<Note> doneBoxes;
    private ArrayList<Note> failedBoxes;

    public LitnerBox () {
        boxes = PropertyHolder.getLitnerBoxValues();
        doneBoxes = PropertyHolder.getDoneBoxes();
        failedBoxes = PropertyHolder.getFailedBoxes();
        
        if (PropertyHolder.getLitnerShouldBeUpdated()) {
            PropertyHolder.setLitnerShouldBeUpdated(false);
            tick(PropertyHolder.getPastDays().intValue());
        }
    }

    private void tick (int times) {
        for (int i = 0; i < times; i++) {
            shiftBoxes();
        }
    }
    
    private void shiftBoxes () {
        for (int i = 31; i >= 0; i--) {
            if (readingDays.contains(i)) {
                failedBoxes.addAll(boxes.get(i));
                boxes.get(i).clear();
            } else {
                boxes.set(i + 1, boxes.get(i));
            }
        }
    }

    public Integer getNoteStatus (Note note) {
        if (doneBoxes.contains(note))
            return DONE;

        if (failedBoxes.contains(note))
            return UNDONE;

        for (int i = 0; i <= 31; i++) {
            if (boxes.get(i).contains(note)) {
                if (readingDays.contains(i))
                    return SHOULD_BE_READ_TODAY;
                else
                    return WAITING_FOE_NEXT_DAY;
            }
        }

        return NOT_IN_BOXES;
    }

    public void moveNote (Note note, int todo) {
        switch (todo) {
            case MOVE_FROM_CURRENT_BOX_TO_NEXT:
                for (int i = 0; i < 31; i++) {
                    if (boxes.get(i).contains(note)) {
                        boxes.get(i).remove(note);
                        boxes.get(i + 1).add(note);
                        break;
                    }
                }
                if (boxes.get(31).contains(note)) {
                    boxes.get(31).remove(note);
                    doneBoxes.add(note);
                }
                break;

            case MOVE_FROM_CURRENT_BOX_TO_FAILED_BOXES:
                for (int i = 0; i <= 31; i++) {
                    if (boxes.get(i).contains(note)) {
                        boxes.get(i).remove(note);
                        failedBoxes.add(note);
                        break;
                    }
                }
                break;

            case MOVE_FROM_DONE_TO_FIRST:
                doneBoxes.remove(note);
                boxes.get(0).add(note);
                break;

            case MOVE_FROM_FAILED_BOXES_TO_FIRST:
                failedBoxes.remove(note);
                boxes.get(0).add(note);
                break;

            case MOVE_FROM_CURRENT_BOX_TO_FIRST:
                for (int i = 0; i <= 31; i++) {
                    if (boxes.get(i).contains(note)) {
                        boxes.get(i).remove(note);
                        boxes.get(0).add(note);
                        break;
                    }
                }
                break;
        }
    }

    public ArrayList<Note> getFailedBoxes() {
        return failedBoxes;
    }

    public ArrayList<Note> getDoneBoxes() {
        return doneBoxes;
    }

    public ArrayList<Note> getBoxWithNumber (int index) {
        return boxes.get(index);
    }
}
