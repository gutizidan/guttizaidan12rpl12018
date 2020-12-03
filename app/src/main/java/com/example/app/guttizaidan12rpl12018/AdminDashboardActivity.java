package com.example.app.guttizaidan12rpl12018;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AdminDashboardActivity extends AppCompatActivity {
    CardView DivUserManagement,DivCarManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        DivUserManagement = findViewById(R.id.DivUserManagement);
        DivUserManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                startActivity(intent);
                Toast.makeText(AdminDashboardActivity.this, "User Management", Toast.LENGTH_SHORT).show();

            }
        });DivCarManagement = findViewById(R.id.DivCarManagement);
        DivCarManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminCarManageActivity.class);
                startActivity(intent);
                Toast.makeText(AdminDashboardActivity.this, "Car Management", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
