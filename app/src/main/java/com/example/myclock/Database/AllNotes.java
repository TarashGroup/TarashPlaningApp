package com.example.myclock.Database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class AllNotes {
    private static HashMap<Integer, Note> NotesHashMap;
    private static boolean hasBeenLoaded = false;

    public static int AddToList(Note l) {
        if (!hasBeenLoaded)
            load();

        int ID = MaxID.noteMaxID();
        l.setSelf_ID(ID);
        NotesHashMap.put(ID, l);

        return ID;
    }

    public static Note getByID (int ID) {
        if (!hasBeenLoaded)
            load();

        return NotesHashMap.get(ID);
    }

    public static void updateByID (Integer ID, Note newNote) {
        if (!hasBeenLoaded)
            load();

        if (ID == -1)
            return;

        NotesHashMap.put(ID, newNote);
    }


    public static int removeByNote(Note l) {
        if (!hasBeenLoaded)
            load();

        int ID = l.getSelf_ID();
        removeByID(ID);
        return ID;
    }


    public static ArrayList<Note> getNotesByListOfIDs (ArrayList<Integer> IDs) {
        if (!hasBeenLoaded)
            load();

        ArrayList<Note> temp = new ArrayList<>();
        for (Integer ID : IDs) {
            Note found = getByID(ID);
            if (found != null) {
                temp.add(found);
            }
        }
        return temp;
    }

    public static void removeByID (int ID) {
        if (!hasBeenLoaded)
            load();

        NotesHashMap.remove(ID);
    }

    public static void load () {
        // HashMap.put(ID, l);-
        hasBeenLoaded = true;
    }
}
