package app.theducksneezes.com.ldc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
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

    FirebaseStorage storage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whiteboard);

        // sets canvas view
        canvasView = (CanvasView) findViewById(R.id.canvas);
        imageView = (ImageView) findViewById(R.id.imageView);

        storage = FirebaseStorage.getInstance();

        // connects to Firebase database
        //DatabaseReference dbref = FirebaseDatabase.getInstance().getReference();

        /*
        // How to put images in Firebase
        Map<String, String> values = new HashMap<>();

        values.put("name", "rob");

        dbref.push().setValue(values, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null){

                    Log.i("Info", "Save Successful");
                }
                else {
                    Log.i("Info", "Save Failed");
                }

            }
        });
        */

    }

    // clears the canvas
    public void clearCanvas(View v){

        canvasView.clearCanvas();
        // if there is an image
        imageView.setImageResource(0);
    }


    public void sendImage(View v){
        // sets up canvas based on what is drawn
        CanvasView content = canvasView;
        content.setDrawingCacheEnabled(true);
        content.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        // creates the bitmap file from the canvas/drawing
        Bitmap bitmap = content.getDrawingCache();

        // saves the image of the drawing
        saveImage(bitmap);

        // where things might get funky
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();

        StorageReference storageRef = storage.getReference();

        StorageReference testRef = storageRef.child("test_sent.png");//works if test.png is saved

        UploadTask uploadTask = testRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
            }
        });
    }

    // note: to get this method to work, you have to manually grant permissions
    // in settings for this app.
    public void saveImage(Bitmap bm){

        File file = Environment.getExternalStorageDirectory();
        File newFile = new File(file, "test_received.png");

        try{
            FileOutputStream fileOutputStream = new FileOutputStream(newFile);
            bm.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            Toast.makeText(WhiteboardActivity.this,
                    "Save Bitmap: " + fileOutputStream.toString(),
                    Toast.LENGTH_LONG).show();

        } catch(Exception e){
            e.printStackTrace();
            Toast.makeText(WhiteboardActivity.this,
                    "Something wrong: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
    /*
    // saves the image, we need to find out how to send it
    public void sendImage(View v){

        *//* first we have to SAVE canvas into bitmap *//*
        // sets up saving canvas
        //View content = (CanvasView) findViewById(R.id.canvas);
        CanvasView content = canvasView;

        // info to save Canvas
        // https://stackoverflow.com/questions/7401432/drawing-on-canvas-and-save-image

        // didn't work, so try this:
        // http://android-er.blogspot.com/2015/12/open-image-free-draw-something-on.html
        content.setDrawingCacheEnabled(true);
        content.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);

        // sets up drawing cache for canvas view
        Bitmap bitmap = content.getDrawingCache();

        // names the file that will be sent out
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(path + "/image.png");

        // initializes output stream
        //FileOutputStream ostream = null;
        ByteArrayOutputStream baos = null;

        // create a storage reference from the app
        StorageReference storageRef = storage.getReference("Filename/" + path + "/image.png");

        try {

            // creates stream to send drawing
            //file.createNewFile();
            //ostream = new FileOutputStream(file);

            Log.i("TAG","Jumpers. Jumpers. JUMPERS!!!!!!");
            baos = new ByteArrayOutputStream();

            // compresses file
            //bitmap.compress(Bitmap.CompressFormat.PNG, 100, ostream);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] data = baos.toByteArray();

            UploadTask uploadTask = storageRef.putBytes(data);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                }
            });

            // sends the file out
            //ostream.flush();
            baos.flush();
            // closes the output stream
            //ostream.close();
            baos.close();

            Toast.makeText(getApplicationContext(), "image saved", Toast.LENGTH_LONG).show();

            *//* sends bitmap to Firebase *//*
            // possibly the code to share projects (check later)
            //Intent shareIntent = new Intent(Intent.ACTION_SEND);
            //shareIntent.setType("image/jpeg");

            // how it will parse
            //shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(""));


        } catch (Exception e) {
            // prints out error
            e.printStackTrace();
            // lets user know of error
            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
        }
    }*/

    // checks if other user has canvas ready
    public void syncCanvas(View view){
        //StorageReference storageRef = storage.getReference();
        StorageReference gsRef = storage.getReferenceFromUrl("gs://long-distance-contact.appspot.com");
        //StorageReference gsRef = storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/long-distance-contact.appspot.com/o/test.png?alt=media&token=30ec2985-bbbe-466a-9915-f717ffbdfcf2");

        gsRef.child("test_received.png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Got the download URL for 'users/me/profile.png'

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
        // test with Glide
        // https://firebase.google.com/docs/storage/android/download-files
        /* Glide.with(this)
                .using(new FirebaseImageLoader())
                .load(gsRef)
                .into(imageView);*/

        Toast.makeText(getApplicationContext(), "Loading", Toast.LENGTH_LONG).show();

        /* gsRef.getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                // Use the bytes to display the image
                try{

                    //Toast.makeText(getApplicationContext(), Integer.toString(bytes.length), Toast.LENGTH_LONG).show();
                    Bitmap bMap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    Toast.makeText(getApplicationContext(), "Loading", Toast.LENGTH_LONG).show();

                    // stuck here
                    Canvas newCanvas = new Canvas(bMap);
                    newCanvas.drawBitmap(bMap, 0, 0, null);

                    canvasView.draw(newCanvas);


                } catch(Exception e){
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                Toast.makeText(getApplicationContext(), "Needs work", Toast.LENGTH_LONG).show();
            }
        }); */
        /*Bitmap otherImage = null;
        try{
            // will have to be bitmap from firebase

        } catch (Exception e){
            Toast.makeText(getApplicationContext(), "no image", Toast.LENGTH_LONG).show();
        }

        // changes background to those of other user
        // code: https://stackoverflow.com/questions/4572564/how-to-change-the-background-color-of-a-saved-transparent-bitmap
        Bitmap newBitmap = Bitmap.createBitmap(
                otherImage.getWidth(), otherImage.getHeight(),
                otherImage.getConfig());

        // sets up canvas
        Canvas newCanvas = new Canvas(newBitmap);
        //newCanvas.drawColor(Color.WHITE);

        // draws in the bitmap
        newCanvas.drawBitmap(otherImage, 0, 0, null);*/

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