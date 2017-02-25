package maxinertia.lifelog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import maxinertia.lifelog.data.Data;
import maxinertia.lifelog.data.Storage;
import maxinertia.lifelog.note.AddNoteActivity;
import maxinertia.lifelog.people.AddPersonActivity;

public class MainActivity extends AppCompatActivity {

    private static int selectedBotNavItemID = R.id.bottomNavMenu_notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Storage.loadData(getBaseContext());
        setupNavigationView();
    }

    public void setupNavigationView() {
        final BottomNavigationView mBottomNav = (BottomNavigationView) findViewById(R.id.bottomNavigationBar);

        mBottomNav.findViewById(selectedBotNavItemID).performClick();
        switch(selectedBotNavItemID) {
            case R.id.bottomNavMenu_notes:
                findViewById(R.id.main_noteFragment).setVisibility(View.VISIBLE);
                findViewById(R.id.main_peopleFragment).setVisibility(View.INVISIBLE);
                break;
            case R.id.bottomNavMenu_people:
                findViewById(R.id.main_noteFragment).setVisibility(View.INVISIBLE);
                findViewById(R.id.main_peopleFragment).setVisibility(View.VISIBLE);
                break;
        }

        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.bottomNavMenu_notes:
                        if(selectedBotNavItemID == item.getItemId()) {
                            Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                            MainActivity.this.startActivity(intent);
                        } else {
                            findViewById(R.id.main_peopleFragment).setVisibility(View.INVISIBLE);
                            findViewById(R.id.main_noteFragment).setVisibility(View.VISIBLE);
                            selectedBotNavItemID = item.getItemId();
                            Toast.makeText(getBaseContext(), ""+Data.getNotes().size(), Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.bottomNavMenu_people:
                        if(selectedBotNavItemID == item.getItemId()) {
                            Intent intent = new Intent(MainActivity.this, AddPersonActivity.class);
                            MainActivity.this.startActivity(intent);
                        } else {
                            findViewById(R.id.main_peopleFragment).setVisibility(View.VISIBLE);
                            findViewById(R.id.main_noteFragment).setVisibility(View.INVISIBLE);
                            selectedBotNavItemID = item.getItemId();
                            Toast.makeText(getBaseContext(),""+Data.getPeople().size(), Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.bottomNavMenu_log:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
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
