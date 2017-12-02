package app.theducksneezes.com.ldc;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by redch on 11/16/2017.
 */

public class CanvasView extends View {

    public int width;
    public int height;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mPaint;
    private float mX, mY;
    private static final float TOLERANCE = 5;
    Context context;

    // guessing around
    AttributeSet attrs;
    boolean status;
    ImageView imageView;

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        // hope this works
        this.attrs = attrs;
        status = false;
        //imageView = (ImageView) findViewById(R.id.imageView);

        mPath = new Path();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(4f);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

    }

    // draws on canvas ability
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*
        if (imageView.getDrawable() != null) {

            imageView.buildDrawingCache();
            Bitmap imageViewBmap = imageView.getDrawingCache();
            mCanvas = new Canvas(imageViewBmap);

            imageView.setImageResource(0);
        }*/

        canvas.save();
        canvas.drawBitmap(mBitmap,0, 0, null);
        canvas.restore();

        canvas.drawPath(mPath, mPaint);
    }

    // communicates the drawing ability initial
    private void startTouch(float x, float y){
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    // when user moves the drawing finger
    private void moveTouch(float x, float y){
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if(dx >= TOLERANCE || dy >= TOLERANCE){
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    // clears the canvas
    public void clearCanvas(){
        mPath.reset();
        invalidate();
    }

    // when user lifts up finger
    private void upTouch(){
        mPath.lineTo(mX,mY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                moveTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                upTouch();
                invalidate();
                break;
        }

        return true;
    }

    public void drawOver(){

        /*
        try {
            imageView = (ImageView) findViewById(R.id.imageView);
            imageView.setDrawingCacheEnabled(true);
            imageView.buildDrawingCache();

            Bitmap imageViewBmap = imageView.getDrawingCache();

            mBitmap = imageViewBmap;
            invalidate();
            requestLayout();
            //Canvas canvas = new Canvas(bitmap);
            //mCanvas = canvas;

        } catch(Exception e){
            e.printStackTrace();
        }*/
    }
}
