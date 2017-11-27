package app.theducksneezes.com.ldc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GoOutsideMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_outside);
    }

    // opens to Hiking Activity
    public void openToHiking(View view){
        Intent intent = new Intent(getApplicationContext(), DrawerMenuActivity.class );
        startActivity(intent);
    }

    // opens to Biking Activity
    public void openToBiking(View view){
        Intent intent = new Intent(getApplicationContext(), DrawerMenuActivity.class );
        startActivity(intent);
    }

    // opens to Create your Own Activity
    public void openToCreateYourOwn(View view){
        Intent intent = new Intent(getApplicationContext(), DrawerMenuActivity.class );
        startActivity(intent);
    }
}
