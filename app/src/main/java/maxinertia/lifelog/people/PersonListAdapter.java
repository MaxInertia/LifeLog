package maxinertia.lifelog.people;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.LinkedList;

import maxinertia.lifelog.R;
import maxinertia.lifelog.data.Person;

/**
 *
 */
public class PersonListAdapter extends ArrayAdapter<Person> {

    /**
     * Constructor for the NoteListAdapter class.
     * @param context The context of MainActivity.class
     * @param resource The layout for each row of the list
     * @param items The items that will populate the list
     */
    public PersonListAdapter(Context context, int resource, LinkedList<Person> items) {
        super(context, resource, items);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        Person item = getItem(position);
        View view;

        if(item!=null) {
            if(convertView == null) {
                view = inflater.inflate(R.layout.person_list_item, parent, false);
            } else {
                view = convertView;
            }

            ((TextView) view.findViewById(R.id.personListItem_name)).setText(
                    item.getName()
            );
            ((TextView) view.findViewById(R.id.personListItem_age)).setText(
                    "" + item.getAge()
            );

            return view;
        } else {
            view = inflater.inflate(R.layout.person_list_item, parent, false);
        }

        return view;
    }

}
