package app.theducksneezes.com.ldc;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class GameMenuActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);


    }

    // opens to Whiteboard Activity
    public void openToWhiteBoard(View view){
        Intent intent = new Intent(getApplicationContext(), WhiteboardActivity.class );
        startActivity(intent);
    }

    // opens to Blocks Activity
    public void openToBlocks(View view){
        Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class );
        startActivity(intent);
    }

}
