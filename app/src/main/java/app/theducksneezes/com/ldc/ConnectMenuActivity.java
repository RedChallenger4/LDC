package app.theducksneezes.com.ldc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ConnectMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_menu);
    }

    // opens to Main Menu
    public void openToMenu(View view){
        Intent intent = new Intent(getApplicationContext(), DrawerMenuActivity.class );
        startActivity(intent);
    }

    // opens to Text Activity
    public void openToText(View view){
        Intent intent = new Intent(getApplicationContext(), ConnectActivity.class );
        intent.putExtra("title", "Chat");
        startActivity(intent);
    }

    // opens to Phone Call Activity
    public void openToPhoneCall(View view){
        Intent intent = new Intent(getApplicationContext(), ConnectActivity.class );
        intent.putExtra("title", "Phonecall");
        startActivity(intent);
    }

    // opens to Video Call Activity
    public void openToVideoCall(View view){
        Intent intent = new Intent(getApplicationContext(), ConnectActivity.class );
        intent.putExtra("title", "Videocall");
        startActivity(intent);
    }

    // opens to Disconnect Activity
    public void openToDisconnect(View view){
        Intent intent = new Intent(getApplicationContext(), DisconnectActivity.class );
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
        if (id == R.id.homeButton) {
            Intent intent = new Intent(getApplicationContext(), DrawerMenuActivity.class );
            startActivity(intent);
        } else if (id == R.id.nav_game) {
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
