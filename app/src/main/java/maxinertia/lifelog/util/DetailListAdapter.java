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
import maxinertia.lifelog.data.SubNote;

/**
 *
 */
public class DetailListAdapter extends ArrayAdapter<SubNote> {
    /**
     * Constructor for the DetailListAdapter class.
     *
     * @param context  The context of DetailedNoteClass
     * @param resource The layout for each row of the list
     * @param subnotes    The subnotes that will populate the list
     */
    public DetailListAdapter(Context context, int resource, LinkedList<SubNote> subnotes) {
        super(context, resource, subnotes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        SubNote subNote = getItem(position);
        View noteView;

        if (subNote != null) {
            noteView = inflater.inflate(R.layout.subnote_list_item, parent, false);

            ((TextView) noteView.findViewById(R.id.detailedNote_listItemContent)).setText(
                    subNote.getContent()
            );
            //((TextView) noteView.findViewById(R.id.detailedNote_listItemTime)).setText(
            //        subNote.getReadableDate()
            //);
        } else {
            noteView = inflater.inflate(R.layout.note_list_item, parent, false);
        }

        return noteView;
    }
}
