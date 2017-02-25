package maxinertia.lifelog.people;

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
 * A fragment representing a list of People.
 */
public class PeopleFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_people, container, false);

        PersonListAdapter adapter = new PersonListAdapter(
                getActivity(),
                R.layout.person_list_item,
                Data.getPeople()
        );
        ListView noteList = (ListView) fragmentView.findViewById(R.id.peopleFragment_listView);
        noteList.setAdapter(adapter);
        noteList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), DetailedPersonActivity.class);
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
