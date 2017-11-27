package app.theducksneezes.com.ldc;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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