package app.theducksneezes.com.ldc;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EventDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
    }

    // opens to Event List Activity
    public void openToEventList(View view){
        EditText nameText = findViewById(R.id.eventNameInput);
        EditText descText = findViewById(R.id.eventDescInput);
        Intent intent = new Intent(getApplicationContext(), EventListActivity.class );

        Bundle extras = getIntent().getExtras();
        String name = nameText.getText().toString();
        String desc = descText.getText().toString();
        long date = 0;
        int startMin = 0;
        int startHour = 0;
        int endMin = 0;
        int endHour = 0;
        if (extras != null) {
            date = extras.getLong("date");
            startMin = extras.getInt("startMin");
            startHour = extras.getInt("startHour");
            endMin = extras.getInt("endMin");
            endHour = extras.getInt("endHour");
        }

        DatabaseHelper db = new DatabaseHelper(this);
        db.addData(name, desc, date, startMin, startHour, endMin, endHour);

        startActivity(intent);
    }
}