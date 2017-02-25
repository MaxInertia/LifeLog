package maxinertia.lifelog.people;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import maxinertia.lifelog.R;
import maxinertia.lifelog.data.Data;
import maxinertia.lifelog.data.Person;
import maxinertia.lifelog.util.InfoRowView;

public class DetailedPersonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_person);

        int index = getIntent().getExtras().getInt("position");
        final Person person = Data.getPeople().get(index);

        setupTextViews(person);
    }

    private void setupTextViews(final Person person) {
        InfoRowView.setupView(DetailedPersonActivity.this, R.id.detailedPerson_name, "Name:", person.getName());
        InfoRowView.setupView(DetailedPersonActivity.this, R.id.detailedPerson_age, "Age:", ""+person.getAge());
        InfoRowView.setupView(DetailedPersonActivity.this, R.id.detailedPerson_dateOfBirth, "Date of Birth:", person.getDateOfBirth());
        InfoRowView.setupView(DetailedPersonActivity.this, R.id.detailedPerson_address, "Address:", person.getAddress());
    }
}
