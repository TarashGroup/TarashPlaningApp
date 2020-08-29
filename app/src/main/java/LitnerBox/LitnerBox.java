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
    private static ArrayList<ArrayList<Note>> boxes;
    private static ArrayList<Note> doneBoxes;
    private static ArrayList<Note> failedBoxes;
    private static boolean hasBeenLoaded = false;


    public static void loadAndSetBoxes () {
        boxes = PropertyHolder.getLitnerBoxValues();
        doneBoxes = PropertyHolder.getDoneBoxes();
        failedBoxes = PropertyHolder.getFailedBoxes();
        hasBeenLoaded = true;

        if (PropertyHolder.getLitnerShouldBeUpdated()) {
            PropertyHolder.setLitnerShouldBeUpdated(false);
            tick(PropertyHolder.getPastDays().intValue());
        }
    }

    private static void tick (int times) {
        if (!hasBeenLoaded)
            loadAndSetBoxes();

        for (int i = 0; i < times; i++) {
            PropertyHolder.shiftBoxes();
        }

        loadAndSetBoxes();
    }
    


    public static Integer getNoteStatus (Note note) {
        if(!hasBeenLoaded)
            loadAndSetBoxes();


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

    public static void moveNote (Note note, int todo) {
        if(!hasBeenLoaded)
            loadAndSetBoxes();

        switch (todo) {
            case MOVE_FROM_CURRENT_BOX_TO_NEXT:
                for (int i = 0; i < 31; i++) {
                    if (boxes.get(i).contains(note)) {
                        removeFromBox(i, note);
                        addToBox(i + 1, note);
                        break;
                    }
                }
                if (boxes.get(31).contains(note)) {
                    removeFromBox(31, note);
                    addToDoneBox(note);
                }
                break;

            case MOVE_FROM_CURRENT_BOX_TO_FAILED_BOXES:
                for (int i = 0; i <= 31; i++) {
                    if (boxes.get(i).contains(note)) {
                        removeFromBox(i, note);
                        addToFailedBox(note);
                        break;
                    }
                }
                break;

            case MOVE_FROM_DONE_TO_FIRST:
                removeFromDoneBox(note);
                addToBox(0, note);
                break;

            case MOVE_FROM_FAILED_BOXES_TO_FIRST:
                removeFromFailedBox(note);
                addToBox(0, note);
                break;

            case MOVE_FROM_CURRENT_BOX_TO_FIRST:
                for (int i = 0; i <= 31; i++) {
                    if (boxes.get(i).contains(note)) {
                        removeFromBox(i, note);
                        addToBox(0, note);
                        break;
                    }
                }
                break;
        }

        loadAndSetBoxes();
    }

    public static ArrayList<Note> getFailedBoxes() {
        if(!hasBeenLoaded)
            loadAndSetBoxes();

        return failedBoxes;
    }

    public static ArrayList<Note> getDoneBoxes() {
        if(!hasBeenLoaded)
            loadAndSetBoxes();

        return doneBoxes;
    }

    public static ArrayList<Note> getSubBoxWithNumber (int index) {
        if(!hasBeenLoaded)
            loadAndSetBoxes();

        return boxes.get(index);
    }


    private static void removeFromBox (int index, Note note) {
        PropertyHolder.removeFromBox(index, note);
    }

    private static void addToBox (int index, Note note) {
        PropertyHolder.addToBox(index, note);
    }

    private static void removeFromDoneBox (Note note) {
        PropertyHolder.removeFromDoneBox(note);
    }

    private static void addToDoneBox (Note note) {
        PropertyHolder.addToDoneBox(note);
    }

    private static void removeFromFailedBox (Note note) {
        PropertyHolder.removeFromFailedBox(note);
    }

    private static void addToFailedBox (Note note) {
        PropertyHolder.addToFailedBox(note);
    }
}
