package maxinertia.lifelog.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 */
public class Data {
    static final LinkedList<Note> notes = new LinkedList<>();
    static final LinkedList<Person> people = new LinkedList<>();

    public static LinkedList<Note> getNotes() {
        return notes;
    }

    public static LinkedList<Person> getPeople() {
        return people;
    }
}
