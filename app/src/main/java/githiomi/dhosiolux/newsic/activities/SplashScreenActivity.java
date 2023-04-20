package githiomi.dhosiolux.newsic.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import githiomi.dhosiolux.newsic.R;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Keep splash screen open for 4 seconds
        new Handler().postDelayed(() -> {

            startActivity(new Intent(this, MainActivity.class));
            finish(); // To the destroy activity

        }, 4000);
    }
}