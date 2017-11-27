package app.theducksneezes.com.ldc;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TimePicker;

public class EndTimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_time);
    }

    // opens to Event Details Activity
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void openToEventDetails(View view){
        TimePicker endPicker = findViewById(R.id.endPicker);
        Intent intent = new Intent(getApplicationContext(), EventDetailsActivity.class );

        Bundle extras = getIntent().getExtras();
        long date = 0;
        int startMin = 0;
        int startHour = 0;
        if (extras != null) {
            date = extras.getLong("date");
            startMin = extras.getInt("startMin");
            startHour = extras.getInt("startHour");
        }
        intent.putExtra("date", date);
        intent.putExtra("startMin", startMin);
        intent.putExtra("startHour", startHour);
        intent.putExtra("endMin", endPicker.getMinute());
        intent.putExtra("endHour", endPicker.getHour());

        startActivity(intent);
    }
}