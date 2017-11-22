package app.theducksneezes.com.ldc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ConnectMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_menu);
    }

    // opens to Main Menu
    public void openToMenu(View view){
        Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class );
        startActivity(intent);
    }

    // opens to Text Activity
    public void openToText(View view){
        Intent intent = new Intent(getApplicationContext(), TextActivity.class );
        startActivity(intent);
    }

    // opens to Phone Call Activity
    public void openToPhoneCall(View view){
        Intent intent = new Intent(getApplicationContext(), PhoneCallActivity.class );
        startActivity(intent);
    }

    // opens to Video Call Activity
    public void openToVideoCall(View view){
        Intent intent = new Intent(getApplicationContext(), VideoCallActivity.class );
        startActivity(intent);
    }

    // opens to Disconnect Activity
    public void openToDisconnect(View view){
        Intent intent = new Intent(getApplicationContext(), DisconnectActivity.class );
        startActivity(intent);
    }
}
