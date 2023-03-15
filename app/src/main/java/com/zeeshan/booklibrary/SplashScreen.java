package com.zeeshan.booklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity
{
    ImageView imageView;
    Animation animation;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView2);

        // hide status bar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        // hide toolbar
        getSupportActionBar().hide();

        animation = AnimationUtils.loadAnimation(this,R.anim.fadeout);
        imageView.setAnimation(animation);

        animation = AnimationUtils.loadAnimation(this,R.anim.blink);
        textView.setAnimation(animation);


        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
            }
        },3000);

    }
}