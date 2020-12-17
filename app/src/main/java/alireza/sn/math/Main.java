package alireza.sn.math;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void onClickAnimate1(View view) {
        startActivity(new Intent(this,AnimationActivity1.class));
    }

    public void onClickAnimate2(View view) {
        startActivity(new Intent(this,AnimationActivity2.class));
    }
}