package maxinertia.lifelog.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import maxinertia.lifelog.R;

import static android.content.Context.MODE_PRIVATE;
import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 *
 */
public class Storage {

    private final static String peopleListConstant = "People";
    private final static String notesListConstant = "Notes";

    public static void saveNote(Context context, Note note) {
        Data.notes.addFirst(note);
        updateNote(context, note);
    }

    public static void updateNote(Context context, Note note) {
        Gson gson = new Gson();
        String json = gson.toJson(note);

        String id = note.getID();
        SharedPreferences sharedPreferences = context.getSharedPreferences(notesListConstant, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(id,json);
        editor.apply();
    }

    public static int loadNotes(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(notesListConstant, MODE_PRIVATE);
        Collection json_note_collection = sharedPreferences.getAll().values();
        Gson gson = new Gson();

        int loadedNoteCount = 0;
        for(Object object: json_note_collection) {
            String json_note = (String) object;
            Note note = gson.fromJson(json_note, Note.class);
            Data.notes.add(note);
            loadedNoteCount++;
        }
        Collections.sort(Data.notes);
        return loadedNoteCount;
    }

    public static void savePerson(Context context, Person person) {
        Data.people.addFirst(person);
        updatePerson(context, person);
    }

    public static void updatePerson(Context context, Person person) {
        Gson gson = new Gson();
        String json = gson.toJson(person);

        String id = person.getID();
        SharedPreferences sharedPreferences = context.getSharedPreferences(peopleListConstant, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(id,json);
        editor.apply();
    }

    public static int loadPeople(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(peopleListConstant, MODE_PRIVATE);
        Collection json_person_collection = sharedPreferences.getAll().values();
        Gson gson = new Gson();

        int loadedNoteCount = 0;
        for(Object object: json_person_collection) {
            String json_note = (String) object;
            Person person = gson.fromJson(json_note, Person.class);
            Data.people.add(person);
            loadedNoteCount++;
        }
        return loadedNoteCount;
    }

    public static void loadData(Context context) {
        if(Data.notes.size()==0) loadNotes(context);
        if(Data.people.size()==0) loadPeople(context);
    }

    /* Checks if external storage is available for read and write */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

}
