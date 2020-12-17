package alireza.sn.math;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;


// for example for show how to draw a rectangle
//   .   .   .   .   .   .   .   .   .   .   .   .   .   .
//   .   .   .   .   .   .   .   .   .   .   .   .   .   .
//   .   .   .left,top   _   _   _  _    .   .   .   .   .
//   .   .   .   |   .   .   .   .   |   .   .   .   .   .
//   .   .   .   |   .   .   .   .   |   .   .   .   .   .
//   .   .   .   |   _   _   _  _ right,bottom   .   .   .
//   .   .   .   .   .   .   .   .   .   .   .   .   .   .
//   .   .   .   .   .   .   .   .   .   .   .   .   .   .
//   .   .   .   .   .   .   .   .   .   .   .   .   .   .

// for example for show how to draw a circle
//   .   .   .   .   .   .   .   .   .   .   .   .   .   .   .
//   .   .   .   .   .   .   .   .   .   .   .   .   .   .   .
//   .   .   .   .   .   .   .   0   0   0   .   .   .   .   .
//   .   .   .   .   .   .   0   .   |   .   0   .   .   .   .
//   .   .   .   .   .   0   .   .   |   .   .   0   .   .   .
//   .   .   .   .   .   0   .   .   |   .   .   0   .   .   .
//   .   .   .   .   .   0-------(cx,cy)---------0   .   .   .
//   .   .   .   .   .   0   .   .   |   .   .   0   .   .   .
//   .   .   .   .   .   .   0   .   |   .   0   .   .   .   .
//   .   .   .   .   .   .   .   0   | -> radius   .   .   .
//   .   .   .   .   .   .   .   .   0   .   .   .   .   .   .
//   .   .   .   .   .   .   .   .   .   .   .   .   .   .   .
//   .   .   .   .   .   .   .   .   .   .   .   .   .   .   .

class MyCanvas extends View {
    Paint paint;
    Rect rectangle;
    Path path;
    Bitmap bitmap;

    int x_bitmap =0;
    int y_bitmap = 0;
    int x_dir =1;
    int y_dir =1;
    int bitmap_with;
    int bitmap_height;

    public MyCanvas(Context context) {
        super(context);
        // like  a brush for painting in label (main page)
        paint = new Paint();

        //for draw a rectangle
        rectangle = new Rect();

        //initialize the path for draw a triangle in onDraw method
        path = new Path();

        //init a bitmap that is a icon laugh
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.icon_calculator);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //set a rectangle with coordinates
        rectangle.set(300,250,400,500);

        //set path for draw a triangle
        path.moveTo(50,50);
        path.lineTo(300,400);
        path.moveTo(300,400);
        path.lineTo(75,600);
        path.moveTo(75,600);
        path.lineTo(50,50);

        //complete feature of paint(brush)
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);

        //draw a rectangle
        canvas.drawRect(rectangle,paint);

        //draw a circle
        canvas.drawCircle(300,600,70,paint);
        canvas.drawCircle(300,600,80,paint);
        canvas.drawCircle(300,600,90,paint);
        canvas.drawCircle(300,600,100,paint);

        //draw a triangle by path
        canvas.drawPath(path,paint);

        //use of option of bitmap factory
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        // set again a bitmap with a option(for get the with and height of icon maybe
        BitmapFactory.decodeResource(getResources(),R.drawable.icon_calculator,options);
        bitmap_with = options.outWidth;
        bitmap_height = options.outHeight;

        // for backing the bitmap
        if (x_bitmap>=getWidth()-bitmap_with)
            x_dir=-3;

        if (x_bitmap<=0)
            x_dir=3;

        if (y_bitmap>=getHeight()-bitmap_height)
            y_dir=-3;

        if (y_bitmap<=0)
            y_dir =3;

        //move the bit map
        x_bitmap+=x_dir;
        y_bitmap+=y_dir;

        //draw a bitmap
        canvas.drawBitmap(bitmap,x_bitmap,y_bitmap,null);
        invalidate();
    }
}
