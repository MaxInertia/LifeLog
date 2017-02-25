package maxinertia.lifelog.note;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import maxinertia.lifelog.R;
import maxinertia.lifelog.data.Data;

/**
 * A fragment representing a list of Notes.
 */
public class NoteFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_note, container, false);

        NoteListAdapter adapter = new NoteListAdapter(
                getActivity(),
                R.layout.note_list_item,
                Data.getNotes()
        );
        ListView noteList = (ListView) fragmentView.findViewById(R.id.noteFragment_listView);
        noteList.setAdapter(adapter);
        noteList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), DetailedNoteActivity.class);
                        intent.putExtra("position", position);
                        getActivity().startActivity(intent);
                    }
                }
        );

        return fragmentView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}