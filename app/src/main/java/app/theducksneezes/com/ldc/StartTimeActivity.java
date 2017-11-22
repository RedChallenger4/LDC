package app.theducksneezes.com.ldc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class StartTimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_time);
    }

    // opens to End Time Activity
    public void openToEndTime(View view){
        Intent intent = new Intent(getApplicationContext(), EndTimeActivity.class );
        startActivity(intent);
    }
}
