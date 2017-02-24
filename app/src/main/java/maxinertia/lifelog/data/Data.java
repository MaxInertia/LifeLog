package maxinertia.lifelog.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 */
public class Data {
    static final LinkedList<Note> notes = new LinkedList<>();

    public static int noteCount() {
        return notes.size();
    }

    public static LinkedList<Note> getNotes() {
        return notes;
    }

    private void sortNotesByDate() {
        Collections.sort(notes);
    }
}
