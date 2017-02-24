package maxinertia.lifelog;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import maxinertia.lifelog.data.Data;
import maxinertia.lifelog.data.Note;
import maxinertia.lifelog.data.Storage;
import maxinertia.lifelog.util.ListAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        if(Data.noteCount()==0) {
            int loadedNoteCount = Storage.loadNotes(getBaseContext());
            Toast.makeText(getBaseContext(),"Loaded notes: "+loadedNoteCount, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        ListAdapter adapter = new ListAdapter(
                this,
                R.layout.note_list_item,
                Data.getNotes()
        );
        ListView noteList = (ListView) findViewById(R.id.main_listView);
        noteList.setAdapter(adapter);
        noteList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Note noteClicked = (Note) parent.getItemAtPosition(position);
                        Intent intent = new Intent(MainActivity.this, DetailedNoteActivity.class);
                        intent.putExtra("note-clicked",noteClicked);
                        intent.putExtra("position",position);
                        MainActivity.this.startActivity(intent);
                    }
                }
        );


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
