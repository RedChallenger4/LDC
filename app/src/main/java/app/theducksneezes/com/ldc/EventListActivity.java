package app.theducksneezes.com.ldc;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.view.Menu;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.nav_game) {
            Intent intent = new Intent(getApplicationContext(), GameMenuActivity.class );
            startActivity(intent);
        } else if (id == R.id.nav_schedule) {
            Intent intent = new Intent(getApplicationContext(), CalendarActivity.class );
            startActivity(intent);
        } else if (id == R.id.nav_outside) {
            Intent intent = new Intent(getApplicationContext(), GoOutsideMenuActivity.class );
            startActivity(intent);
        } else if (id == R.id.nav_movie) {
            Intent intent = new Intent(getApplicationContext(), WatchMoviesMainMenuActivity.class );
            startActivity(intent);
        } else if (id == R.id.nav_chat) {
            Intent intent = new Intent(getApplicationContext(), ConnectActivity.class );
            intent.putExtra("title", "Chat");
            startActivity(intent);
        } else if (id == R.id.nav_call) {
            Intent intent = new Intent(getApplicationContext(), ConnectActivity.class );
            intent.putExtra("title", "Phonecall");
            startActivity(intent);
        } else if (id == R.id.nav_video) {
            Intent intent = new Intent(getApplicationContext(), ConnectActivity.class );
            intent.putExtra("title", "Videocall");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}