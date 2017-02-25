package maxinertia.lifelog.note;

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
public class NoteListAdapter extends ArrayAdapter<Note> {

    /**
     * Constructor for the NoteListAdapter class.
     * @param context The context of MainActivity.class
     * @param resource The layout for each row of the list
     * @param items The items that will populate the list
     */
    public NoteListAdapter(Context context, int resource, LinkedList<Note> items) {
        super(context, resource, items);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        Note item = getItem(position);
        View view;

        if(item!=null) {
            if(convertView == null) {
                view = inflater.inflate(R.layout.note_list_item, parent, false);
                ((TextView) view.findViewById(R.id.main_listItemTitle)).setText(
                        item.getTitle()
                );
                ((TextView) view.findViewById(R.id.main_listItemDescription)).setText(
                        item.getContent()
                );
                ((TextView) view.findViewById(R.id.main_listItemTime)).setText(
                        item.getReadableDate()
                );
                return view;
            } else {
                ((TextView) convertView.findViewById(R.id.main_listItemTitle)).setText(
                        item.getTitle()
                );
                ((TextView) convertView.findViewById(R.id.main_listItemDescription)).setText(
                        item.getContent()
                );
                ((TextView) convertView.findViewById(R.id.main_listItemTime)).setText(
                        item.getReadableDate()
                );
                return convertView;
            }
        } else {
            view = inflater.inflate(R.layout.note_list_item, parent, false);
        }

        return view;
    }

}
