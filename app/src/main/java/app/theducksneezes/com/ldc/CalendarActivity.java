package app.theducksneezes.com.ldc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
    }

    // opens to Pick Date Activity
    public void openToPickDate(View view){
        Intent intent = new Intent(getApplicationContext(), PickDateActivity.class );
        startActivity(intent);
    }
}