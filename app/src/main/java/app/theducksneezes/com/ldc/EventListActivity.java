package app.theducksneezes.com.ldc;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;

public class EventListActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        dbHelper = new DatabaseHelper(this);
        if (dbHelper.getData() != null) {
            Cursor data = dbHelper.getData();
            Event newEvent;
            ArrayAdapter<Event> eventArray = new ArrayAdapter<>(this, R.layout.activity_event_listview);

            while (data.moveToNext()) {
                newEvent = new Event(new SpannableStringBuilder(data.getString(1)), new SpannableStringBuilder(data.getString(2)), data.getLong(3), data.getInt(4), data.getInt(5), data.getInt(6), data.getInt(7));
                eventArray.add(newEvent);
            }

            ListView eventList = findViewById(R.id.eventList);
            eventList.setAdapter(eventArray);

            eventList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg, View view, int i, long l) {
                    Intent intent = new Intent(getApplicationContext(), EventViewActivity.class);

                    Event selectedEvent = (Event) arg.getItemAtPosition(i);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("event", selectedEvent);
                    intent.putExtras(bundle);

                    startActivity(intent);
                }
            });
        }
    }

    // opens to Calendar Activity
    public void openToCalendar(View view){
        Intent intent = new Intent(getApplicationContext(), CalendarActivity.class );
        startActivity(intent);
    }
}