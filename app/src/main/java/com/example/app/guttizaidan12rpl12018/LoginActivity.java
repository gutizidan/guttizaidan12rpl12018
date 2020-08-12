package com.example.app.guttizaidan12rpl12018;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.util.HashMap;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;


public class LoginActivity extends AppCompatActivity {
    private EditText et_username, et_password;
    private boolean isFormFilled = false;
    private SharedPreferences preferences;
    private CircularProgressButton btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);
        et_password = findViewById(R.id.etPassword);
        et_username = findViewById(R.id.etEmail);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            private void doNothing() {
            }

            @Override
            public void onClick(View v) {
            }
        });
    }
}
