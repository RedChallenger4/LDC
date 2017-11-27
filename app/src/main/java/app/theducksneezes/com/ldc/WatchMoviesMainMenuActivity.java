package app.theducksneezes.com.ldc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WatchMoviesMainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_movies_main_menu);
    }

    // opens to MyVideos
    public void openToMyVideos(View view){
        Intent intent = new Intent(getApplicationContext(), DrawerMenuActivity.class );
        startActivity(intent);
    }

    // opens to Netflix
    public void openToNetflix(View view){
        Intent intent = new Intent(getApplicationContext(), DrawerMenuActivity.class );
        startActivity(intent);
    }
}
