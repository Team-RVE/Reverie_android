package com.example.reverie.Splash;


import android.content.Intent;
import android.os.*;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.reverie.MainActivity;
import com.example.reverie.R;

public class SplashActivity extends AppCompatActivity {
    private ImageView logo;
    private TextView slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logo = findViewById(R.id.logo);
        slogan = findViewById(R.id.slogan);
        if (slogan != null) slogan.setAlpha(0f); // 처음에 안 보이게

        // 0.8초 뒤 로고 올리고 문구 표시
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                moveLogoUp();
                showSlogan();
            }
        }, 1000);
    }

    private void moveLogoUp() { // 로고 살짝 위로
        float dp = 64f * getResources().getDisplayMetrics().density; // 64dp
        if (logo != null) logo.animate().translationYBy(-dp).setDuration(800);
    }

    private void showSlogan() { // 문구 나타난 뒤 메인으로
        if (slogan != null) {
            slogan.animate()
                    .alpha(1f).setDuration(700).setStartDelay(200)
                    .withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            openMain();
                        }
                    });
        } else {
            openMain();
        }
    }

    private void openMain () {  // 메인 화면으로 이동
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
