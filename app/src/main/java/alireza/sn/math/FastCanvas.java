package alireza.sn.math;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class FastCanvas extends SurfaceView implements Runnable {
    boolean canDraw = false;
    Thread thread = null;

    Bitmap bitmap;
    Canvas canvas;
    SurfaceHolder surfaceHolder;

    Paint paint;
    Paint paint_fill;
    Path path;

    int x1;
    int y1;

    int x2;
    int y2;

    int x3;
    int y3;

    int x4;
    int y4;

    public FastCanvas(Context context) {
        super(context);
        x1=10;
        y1=10;

        x2=10;
        y2=10;

        x3=10;
        y3=10;

        x4=10;
        y4=10;

        surfaceHolder = getHolder();
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.background);
    }

    private void setPath() {
        path = new Path();

        path.moveTo(10,10);
        path.lineTo(130,10);
        path.moveTo(130,10);
        path.lineTo(130,240);
        path.moveTo(130,240);
        path.lineTo(10,240);
        path.moveTo(10,240);
        path.lineTo(10,10);
    }

    private void preparePaint() {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(3f);

        paint_fill = new Paint();
        paint_fill.setStyle(Paint.Style.FILL);
        paint_fill.setColor(Color.WHITE);
    }

    @Override
    public void run() {
        preparePaint();
        setPath();


        while (canDraw) {
            //carry out something...

            if (!surfaceHolder.getSurface().isValid()){

                continue;
            }

            canvas = surfaceHolder.lockCanvas();
            canvas.drawBitmap(bitmap,0,0,null);
            canvas.drawPath(path,paint);

            paint_fill.setColor(Color.WHITE);
            coordinateCircle(3,x1,y1,1);
            canvas.drawCircle(x1,y1,5,paint_fill);

            paint_fill.setColor(Color.RED);
            coordinateCircle(5,x2,y2,2);
            canvas.drawCircle(x2,y2,10,paint_fill);

            paint_fill.setColor(Color.GRAY);
            coordinateCircle(30,x3,y3,3);
            canvas.drawCircle(x3,y3,15,paint_fill);

            paint_fill.setColor(Color.GREEN);
            coordinateCircle( 1,x4,y4,4);
            canvas.drawCircle(x4,y4,20,paint_fill);

            surfaceHolder.unlockCanvasAndPost(canvas);
        }

    }

    private void coordinateCircle(int speed , int x , int y, int witch) {
        if (x<130 && y>=10 && y<=20)
            x+=speed;
        else if (y<240 &&x>=130 && x<=140)
            y+=speed;
        else if(x>10)
            x-=speed;
        else if(y>10)
            y-=speed;

        if (witch ==1){
            x1 = x;
            y1 = y;
        }
        else if (witch == 2){
            x2 = x;
            y2 = y;
        }
        else if (witch == 3){
            x3 = x;
            y3 = y;
        }
        else if (witch == 4){
            x4 = x;
            y4 = y;
        }
    }

    public void resume() {
        canDraw = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause() {
        canDraw = false;

        while (true)
        try {
            thread.join();
            break;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread = null;
    }
}

