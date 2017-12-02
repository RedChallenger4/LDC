package app.theducksneezes.com.ldc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WhiteboardActivity extends AppCompatActivity {

    private CanvasView canvasView;
    private ImageView imageView;
    private CanvasView content;

    FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whiteboard);

        // sets canvas view
        canvasView = (CanvasView) findViewById(R.id.canvas);

        // sets image view to show other user's image
        imageView = (ImageView) findViewById(R.id.imageView);

        // sets storage to connect to Firebase
        storage = FirebaseStorage.getInstance();
    }

    // clears the canvas
    public void clearCanvas(View v){

        // clears the canvas
        canvasView.clearCanvas();

        // clears if there is an image
        imageView.setImageResource(0);

        // clears drawing cache or existing photos
        content.destroyDrawingCache();
    }

    // sends the image to other user
    public void sendImage(View v){
        // sets up canvas based on what is drawn
        content = canvasView;

        // clears drawing cache or existing photos
        content.destroyDrawingCache();

        // enables drawing cache
        content.setDrawingCacheEnabled(true);
        content.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        // creates the bitmap file from the canvas/drawing
        Bitmap bitmap = content.getDrawingCache();

        // output stream to create bitmap to Firebase
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // creates the PNG image
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);


        // array to hold data stream
        byte[] data = baos.toByteArray();

        // gets reference from Firebase Storage
        StorageReference storageRef = storage.getReference();

        // gets file (input YOUR name
        // after "test_sent_" to test what the user sends in Firebase storage)
        StorageReference testRef
                = storageRef.child("test_sent_Joshua.png");

        // initialized upload task for Firebase
        UploadTask uploadTask = testRef.putBytes(data);

        //
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Toast.makeText(WhiteboardActivity.this,
                        "Sorry, it didn't send. Problem with our code",
                        Toast.LENGTH_LONG).show();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();

                Toast.makeText(WhiteboardActivity.this,
                        "Drawing sent to other user",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    // grabs the image from the other user
    public void syncCanvas(View view){
        // sets reference to Firebase Storage
        StorageReference gsRef = storage.getReferenceFromUrl("gs://long-distance-contact.appspot.com");

        // sets reference to SPECIFIC iamge from Firebase Storage
        gsRef.child("test_received.png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Got the download URL for 'users/me/profile.png'

                // displays the image into ImageView or user's screen
                Picasso.with(WhiteboardActivity.this)
                        .load(uri)
                        .into(imageView);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors

            }
        });

        // signals that canvas has been sent
        Toast.makeText(getApplicationContext(),
                "You received other user's image!",
                Toast.LENGTH_LONG).show();

        // draws the imageView to canvas
        //imageView.buildDrawingCache();

        //Bitmap bMap = BitmapFactory.decodeResource()

        //imageView.setDrawingCacheEnabled(true);
        //imageView.buildDrawingCache();

        //Bitmap imageViewBmap = imageView.getDrawingCache();
        // //canvasView.drawOver();
        //Canvas imageCanvas = new Canvas(imageViewBmap);

        //BitmapDrawable drawable = new BitmapDrawable(getResources(), imageViewBmap);
        //canvasView.onDraw(imageCanvas);
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            canvasView.setForeground(drawable);
        }*/
        //CanvasView canvas = new CanvasView(canvasView.context, canvasView.attrs);
        //canvas.draw(imageCanvas);

        //canvasView = canvas;

    }

    // Setting Menu inflater
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // Menu Options selected
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