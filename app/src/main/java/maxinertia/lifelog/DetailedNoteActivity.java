package maxinertia.lifelog;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import maxinertia.lifelog.data.Data;
import maxinertia.lifelog.data.Note;
import maxinertia.lifelog.data.Storage;
import maxinertia.lifelog.data.SubNote;
import maxinertia.lifelog.util.DetailListAdapter;
import maxinertia.lifelog.util.ListAdapter;

public class DetailedNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_note);

        int index = getIntent().getExtras().getInt("position");
        final Note note = Data.getNotes().get(index);
        //final Note note = getIntent().getExtras().getParcelable("note-clicked");

        verifyNote(note);
        setupTextViews(note);
        setupListView(note);
        setupAppendButton(note);
    }

    private void verifyNote(final Note note) {
        if(note==null) {
            Intent i = getParentActivityIntent();
            DetailedNoteActivity.this.startActivity(i);
        }
    }

    private void setupTextViews(final Note note) {
        TextView title = (TextView) findViewById(R.id.detailedNote_title);
        TextView content = (TextView) findViewById(R.id.detailedNote_content);
        TextView time = (TextView) findViewById(R.id.detailedNote_time);

        title.setText(note.getTitle());
        content.setText(note.getContent());
        time.setText(note.getReadableDate());
    }

    private void setupListView(final Note note) {
        ListView subNoteList = (ListView) findViewById(R.id.detailedNote_listView);
        DetailListAdapter adapter = new DetailListAdapter(
                this,
                R.layout.subnote_list_item,
                note.getSubNotes()
        );
        subNoteList.setAdapter(adapter);
        subNoteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(),((SubNote)parent.getItemAtPosition(position)).getReadableDate(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setupAppendButton(final Note note) {
        Button appendButton = (Button) findViewById(R.id.detailedNote_appendButton);
        appendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText appendField = (EditText)findViewById(R.id.detailedNote_subNoteField);
                String subNote_Content = appendField.getText().toString();
                appendField.setText("");

                note.addSubNote(subNote_Content);
                Storage.updateNote(getBaseContext(),note);

                ListView subNoteList = (ListView) findViewById(R.id.detailedNote_listView);
                //subNoteList.getAdapter().notifyDataSetChanged();
                //subNoteList.deferNotifyDataSetChanged();
                //subNoteList.invalidate();
                //subNoteList.refreshDrawableState();
                subNoteList.invalidateViews();
                View view = DetailedNoteActivity.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        });
    }
}
