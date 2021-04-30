package com.example.driverassistant.Home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.driverassistant.R;

public class Welcome extends AppCompatActivity {
    private Animation imgAnimation;
    private ImageView imgWelcome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.home_welcome);

        // animations
        imgAnimation = AnimationUtils.loadAnimation(this, R.anim.welcome_img_anim);

        // bind UI
        imgWelcome = findViewById(R.id.welcome_img);

        // hook animation
        imgWelcome.setAnimation(imgAnimation);

        // welcome screen for 3s
        new Handler().postDelayed(() -> {
            startActivity(new Intent(Welcome.this, Home.class));
            finish();
        }, 3000);
    }
}
