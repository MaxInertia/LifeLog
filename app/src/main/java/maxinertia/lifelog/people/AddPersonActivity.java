package maxinertia.lifelog.people;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;

import maxinertia.lifelog.GUI_Utility;
import maxinertia.lifelog.R;

import maxinertia.lifelog.data.Person;
import maxinertia.lifelog.data.Storage;


public class AddPersonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        setupInputFields();

        Button saveButton = (Button) findViewById(R.id.addPerson_saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    private void setupInputFields() {
        GUI_Utility.insertInputField(this,R.id.addPerson_fieldLayout,null,"Name here");
        GUI_Utility.insertDivider(this,R.id.addPerson_fieldLayout);
        GUI_Utility.insertInputField(this,R.id.addPerson_fieldLayout,null,"Age");
        GUI_Utility.insertDivider(this,R.id.addPerson_fieldLayout);
        GUI_Utility.insertInputField(this,R.id.addPerson_fieldLayout,null,"DateOfBirth");
    }

    public void save() {
        EditText nameField = GUI_Utility.getInputField(this,R.id.addPerson_fieldLayout,0);
        //EditText addressField = GUI_Utility.getInputField(this,R.id.addPerson_fieldLayout,1);
        EditText ageField = GUI_Utility.getInputField(this,R.id.addPerson_fieldLayout,1);
        EditText dobField = GUI_Utility.getInputField(this,R.id.addPerson_fieldLayout,2);

        int age;
        if(!ageField.getText().toString().equals("")) {
            try{
                age = Integer.parseInt(ageField.getText().toString());
            } catch (NumberFormatException numberFormatException) {
                age = -1;
                // TODO: Inform user that the age row_input_withtitle is invalid
            }
        } else {
            age = -1;
        }

        Person person = new Person(
                nameField.getText().toString(),
                age,
                dobField.getText().toString(),
                "",//addressField.getText().toString(),
                "",
                new Date()
        );

        Storage.savePerson(getBaseContext(), person);
        AddPersonActivity.this.startActivity(getParentActivityIntent());
    }
}
