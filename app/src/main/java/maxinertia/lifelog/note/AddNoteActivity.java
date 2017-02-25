package maxinertia.lifelog.note;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import maxinertia.lifelog.R;
import maxinertia.lifelog.data.Note;
import maxinertia.lifelog.data.Storage;


public class AddNoteActivity extends AppCompatActivity {

    Date creationDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        Button saveButton = (Button) findViewById(R.id.addNote_saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        setTimeField();
    }

    public void setTimeField() {
        creationDate = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM d, yyyy, HH:MM a");
        TextView timeField = (TextView) findViewById(R.id.addNote_timeField);
        timeField.setText(dateFormatter.format(creationDate));
        getSupportActionBar().setTitle(dateFormatter.format(creationDate));
    }

    public void save() {
        EditText titleField = (EditText) findViewById(R.id.addNote_titleField);
        EditText contentField = (EditText) findViewById(R.id.addNote_contentField);
        Note note = new Note(titleField.getText().toString(),contentField.getText().toString(),creationDate, new Date());
        Storage.saveNote(getBaseContext(), note);

        AddNoteActivity.this.startActivity(getParentActivityIntent());
    }

}
