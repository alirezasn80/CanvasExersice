package alireza.sn.math;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class AnimationActivity2 extends AppCompatActivity {
    FastCanvas fastCanvas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fastCanvas = new FastCanvas(this);
        setContentView(fastCanvas);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fastCanvas.resume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        fastCanvas.pause();
    }
}