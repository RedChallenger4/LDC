package app.theducksneezes.com.ldc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class EndTimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_time);
    }

    // opens to Event Details Activity
    public void openToEventDetails(View view){
        Intent intent = new Intent(getApplicationContext(), EventDetailsActivity.class );
        startActivity(intent);
    }
}