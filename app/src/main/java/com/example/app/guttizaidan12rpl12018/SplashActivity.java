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
        final String role = sharedPreferences.getString("role", "");
        System.out.println(role + "pppppppp");
        isFormFilled = true;
        if (role.isEmpty()) {
            isFormFilled = false;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (isFormFilled){
                    if (role.equalsIgnoreCase("Customer") ){
                        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                        startActivity(intent);
                        Toast.makeText(SplashActivity.this, role, Toast.LENGTH_SHORT).show();
                        finish();
                        finishAffinity();
                    }else if (role.equalsIgnoreCase("Admin") ){
                        Intent intent = new Intent(getApplicationContext(), AdminDashboardActivity.class);
                        startActivity(intent);
                        Toast.makeText(SplashActivity.this, role, Toast.LENGTH_SHORT).show();
                        finish();
                        finishAffinity();}

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
