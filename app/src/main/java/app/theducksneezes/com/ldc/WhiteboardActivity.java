package app.theducksneezes.com.ldc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class WhiteboardActivity extends AppCompatActivity {

    private CanvasView canvasView;
    FirebaseStorage storage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whiteboard);

        // sets canvas view
        canvasView = (CanvasView) findViewById(R.id.canvas);

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
    }

    // saves the image, we need to find out how to send it
    public void sendImage(View v){

        /* first we have to SAVE canvas into bitmap */
        // sets up saving canvas
        //View content = (CanvasView) findViewById(R.id.canvas);
        CanvasView content = canvasView;

        // info to save Canvas
        // https://stackoverflow.com/questions/7401432/drawing-on-canvas-and-save-image
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
            file.createNewFile();
            //ostream = new FileOutputStream(file);
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

            // closes the output stream
            //ostream.close();

            Toast.makeText(getApplicationContext(), "image saved", Toast.LENGTH_LONG).show();

            /* sends bitmap to Firebase */
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
    }

    // checks if other user has canvas ready
    public void refreshCanvas(View view){
        Bitmap otherImage = null;
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
        newCanvas.drawBitmap(otherImage, 0, 0, null);

    }
}