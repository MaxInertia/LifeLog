package maxinertia.lifelog.people;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import maxinertia.lifelog.R;
import maxinertia.lifelog.data.Data;
import maxinertia.lifelog.data.Person;
import maxinertia.lifelog.data.Storage;
import maxinertia.lifelog.data.SubNote;
import maxinertia.lifelog.util.DetailListAdapter;
import maxinertia.lifelog.util.InfoRowView;

public class DetailedPersonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_person);

        int index = getIntent().getExtras().getInt("position");
        final Person person = Data.getPeople().get(index);

        setupTextViews(person);
        setupListView(person);
        setupAddNoteButton(person);
    }

    private void setupTextViews(final Person person) {
        InfoRowView.setupView(DetailedPersonActivity.this, R.id.detailedPerson_name, "Name:", person.getName());
        InfoRowView.setupView(DetailedPersonActivity.this, R.id.detailedPerson_age, "Age:", ""+person.getAge());
        InfoRowView.setupView(DetailedPersonActivity.this, R.id.detailedPerson_dateOfBirth, "Date of Birth:", person.getDateOfBirth());
        InfoRowView.setupView(DetailedPersonActivity.this, R.id.detailedPerson_address, "Address:", person.getAddress());
    }

    private void setupListView(final Person person) {
        ListView subNoteList = (ListView) findViewById(R.id.detailedPerson_listView);
        DetailListAdapter adapter = new DetailListAdapter(
                this,
                R.layout.subnote_list_item,
                person.getNotes()
        );
        subNoteList.setAdapter(adapter);
        subNoteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(),((SubNote)parent.getItemAtPosition(position)).getReadableDate(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setupAddNoteButton(final Person person) {
        Button appendButton = (Button) findViewById(R.id.detailedPerson_addNoteButton);
        appendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText appendField = (EditText)findViewById(R.id.detailedPerson_subNoteField);
                String subNote_Content = appendField.getText().toString();
                appendField.setText("");

                person.addNote(subNote_Content);
                Storage.updatePerson(getBaseContext(), person);

                ListView subNoteList = (ListView) findViewById(R.id.detailedPerson_listView);
                subNoteList.invalidateViews();
                View view = DetailedPersonActivity.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        });
    }
}
