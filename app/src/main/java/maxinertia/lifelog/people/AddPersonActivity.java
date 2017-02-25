package maxinertia.lifelog.people;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

import maxinertia.lifelog.R;

import maxinertia.lifelog.data.Person;
import maxinertia.lifelog.data.Storage;


public class AddPersonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        Button saveButton = (Button) findViewById(R.id.addPerson_saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    public void save() {
        EditText nameField = (EditText) findViewById(R.id.addPerson_nameField);
        EditText addressField = (EditText) findViewById(R.id.addPerson_addressField);
        EditText ageField = (EditText) findViewById(R.id.addPerson_ageField);
        EditText dobField = (EditText) findViewById(R.id.addPerson_dateOfBirthField);

        int age;
        if(!ageField.getText().toString().equals("")) {
            try{
                age = Integer.parseInt(ageField.getText().toString());
            } catch (NumberFormatException numberFormatException) {
                age = -1;
                // TODO: Inform user that the age input is invalid
            }
        } else {
            age = -1;
        }

        Person person = new Person(
                nameField.getText().toString(),
                age,
                dobField.getText().toString(),
                addressField.getText().toString(),
                "",
                new Date()
        );

        Storage.savePerson(getBaseContext(), person);
        AddPersonActivity.this.startActivity(getParentActivityIntent());
    }
}
