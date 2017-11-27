package app.theducksneezes.com.ldc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CalendarView;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
    }

    // opens to Start Time Activity
    public void openToStartTime(View view){
        CalendarView calendar = (CalendarView) findViewById(R.id.calendarView);
        long date = calendar.getDate();
        Intent intent = new Intent(getApplicationContext(), StartTimeActivity.class );
        intent.putExtra("date", date);
        startActivity(intent);
    }

    // opens to Event List Activity
    public void openToEventList(View view){
        Intent intent = new Intent(getApplicationContext(), EventListActivity.class );
        startActivity(intent);
    }
}