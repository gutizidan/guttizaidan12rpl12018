package com.example.app.guttizaidan12rpl12018;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {
    private boolean isFormFilled = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences sharedPreferences = getSharedPreferences("Tugas PTS", MODE_PRIVATE);
        final String ide = sharedPreferences.getString("id", "");
        System.out.println(ide + "pppppppp");
        isFormFilled = true;
        if (ide.isEmpty()) {
            isFormFilled = false;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (isFormFilled){

                    startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                    finish();
                }else{
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }
            }
        },3000);
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.spin_kit);
    }
}
