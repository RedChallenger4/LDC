package app.theducksneezes.com.ldc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WhiteboardActivity extends AppCompatActivity {

    private CanvasView canvasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whiteboard);

        canvasView = (CanvasView) findViewById(R.id.canvas);
    }

    public void clearCanvas(View v){
        canvasView.clearCanvas();
    }
}
