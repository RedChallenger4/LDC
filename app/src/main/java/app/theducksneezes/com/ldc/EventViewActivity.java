package app.theducksneezes.com.ldc;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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
        if (id == R.id.homeButton) {
            Intent intent = new Intent(getApplicationContext(), DrawerMenuActivity.class );
            startActivity(intent);
        } else if (id == R.id.nav_game) {
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
