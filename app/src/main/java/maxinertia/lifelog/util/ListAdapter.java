package maxinertia.lifelog.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.LinkedList;

import maxinertia.lifelog.R;
import maxinertia.lifelog.data.Note;

/**
 *
 */
public class ListAdapter extends ArrayAdapter<Note> {

    /**
     * Constructor for the ListAdapter class.
     * @param context The context of MainActivity.class
     * @param resource The layout for each row of the list
     * @param notes The notes that will populate the list
     */
    public ListAdapter(Context context, int resource, LinkedList<Note> notes) {
        super(context, resource, notes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        Note note = getItem(position);
        View noteView;

        if(note != null) {
            noteView = inflater.inflate(R.layout.note_list_item, parent, false);

            ((TextView) noteView.findViewById(R.id.main_listItemTitle)).setText(
                    note.getTitle()
            );
            ((TextView) noteView.findViewById(R.id.main_listItemDescription)).setText(
                    note.getContent()
            );
            ((TextView) noteView.findViewById(R.id.main_listItemTime)).setText(
                    note.getReadableDate()
            );
        } else {
            noteView = inflater.inflate(R.layout.note_list_item, parent, false);
        }

        return noteView;
    }

}
