package app.theducksneezes.com.ldc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class EventDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
    }

    // opens to Calendar Activity
    public void openToCalendar(View view){
        Intent intent = new Intent(getApplicationContext(), CalendarActivity.class );
        startActivity(intent);
    }
}