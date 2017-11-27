package app.theducksneezes.com.ldc;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Anders on 11/26/2017.
 */

public class EventViewActivity extends AppCompatActivity {

    Event event;
    DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);

        event = (Event) getIntent().getSerializableExtra("event");

        TextView textView = this.findViewById(R.id.eventNameData);
        textView.setText(event.getName());

        textView = this.findViewById(R.id.eventDescData);
        textView.setText(event.getDesc());

        textView = this.findViewById(R.id.eventDateData);
        textView.setText(event.dateString());

        textView = this.findViewById(R.id.eventStartData);

        textView.setText(((Integer) event.getStartHour()).toString() + ":" + ((Integer) event.getStartMin()).toString());

        textView = this.findViewById(R.id.eventEndData);
        textView.setText(((Integer) event.getEndHour()).toString() + ":" + ((Integer) event.getEndMin()).toString());

        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteEvent(event.getName(), event.getDesc());

                openToEventList(view);
            }
        });
    }

    // opens to Event List Activity
    public void openToEventList(View view){
        Intent intent = new Intent(getApplicationContext(), EventListActivity.class );
        startActivity(intent);
    }
}
