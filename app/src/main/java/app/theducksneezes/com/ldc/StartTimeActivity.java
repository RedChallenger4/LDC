package app.theducksneezes.com.ldc;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TimePicker;

public class StartTimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_time);
    }

    // opens to End Time Activity
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void openToEndTime(View view){
        TimePicker startPicker = findViewById(R.id.startPicker);
        Intent intent = new Intent(getApplicationContext(), EndTimeActivity.class );

        Bundle extras = getIntent().getExtras();
        long date = 0;
        if (extras != null) {
            date = extras.getLong("date");
        }
        intent.putExtra("date", date);
        intent.putExtra("startMin", startPicker.getMinute());
        intent.putExtra("startHour", startPicker.getHour());

        startActivity(intent);
    }
}
