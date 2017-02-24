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

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 *
 */
public class Storage {


    static final String FILE_NAME = "nt";
    static final String FILE_EXT = ".dt";

    public static void saveNote(Context context, Note note) {
        Data.notes.addFirst(note);

        Gson gson = new Gson();
        String note_json = gson.toJson(note);

        String id = ""+ note.getReadableDate();
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(id,note_json);
        editor.apply();
    }

    public static void updateNote(Context context, Note note) {
        Gson gson = new Gson();
        String note_json = gson.toJson(note);

        String id = ""+ note.getReadableDate();
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(id,note_json);
        editor.apply();
    }

    public static int loadNotes(Context context) {
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);

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
