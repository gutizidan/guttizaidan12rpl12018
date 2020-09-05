package com.example.app.guttizaidan12rpl12018;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import java.util.HashMap;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class RegisterActivity extends AppCompatActivity {
    private EditText et_username, et_password , et_email , et_alamat , et_nomorhp , et_nomorktp;
    private boolean isFormFilled = false;
    private SharedPreferences preferences;
    private CircularProgressButton btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnRegister = findViewById(R.id.btnRegister);
        et_password = findViewById(R.id.etPassword);
        et_username = findViewById(R.id.etUsername);
        et_email = findViewById(R.id.etEmail);
        et_alamat = findViewById(R.id.etAlamat);
        et_nomorhp = findViewById(R.id.etNomorhp);
        et_nomorktp = findViewById(R.id.etNomorKtp);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            private void doNothing() {
            }

            @Override
            public void onClick(View v) {
                isFormFilled = true;
                final String username = et_username.getText().toString();
                final String password = et_password.getText().toString();
                final String email = et_email.getText().toString();
                final String alamat = et_alamat.getText().toString();
                final String nomorhp = et_nomorhp.getText().toString();
                final String nomorktp = et_nomorktp.getText().toString();

                if (username.isEmpty() || password.isEmpty() || email.isEmpty() || alamat.isEmpty() ||nomorhp.isEmpty() || nomorktp.isEmpty() ) {
                    Toast.makeText(RegisterActivity.this, "Harap lengkapi isian yang tersedia", Toast.LENGTH_SHORT).show();
                    isFormFilled = false;
                }
                if (isFormFilled) {
                    btnRegister.startAnimation();
                    HashMap<String, String> body = new HashMap<>();
                    body.put("nama", username);
                    body.put("password", password);
                    body.put("email", email);
                    body.put("nohp", nomorhp);
                    body.put("alamat", alamat);
                    body.put("noktp", nomorktp);
                    AndroidNetworking.post("http://192.168.6.82/TugasAPI2/register.php")
                            .addBodyParameter(body)
                            .setPriority(Priority.MEDIUM)
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.d("GZS", "respon : " + response);

                                    String status = response.optString("STATUS");
                                    String message = response.optString("MESSAGE");
                                    String sender = response.optString("SENDER");
                                    if (status.equalsIgnoreCase("SUCCESS")) {
                                        JSONObject payload = response.optJSONObject("PAYLOAD");
                                        String U_ID = payload.optString("LOGIN_ID");
                                        String U_NAME = payload.optString("LOGIN_NAME");


                                        preferences = getSharedPreferences("Tugas PTS", Context.MODE_PRIVATE);
                                        preferences.edit()
                                                .putString("id", U_ID)
                                                .putString("nama", U_NAME)
                                                .apply();


                                        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(RegisterActivity.this, sender, Toast.LENGTH_SHORT).show();
                                        finish();
                                        finishAffinity();
                                    }
                                    else {
                                        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                                    }

                                    btnRegister.revertAnimation();
                                }

                                @Override
                                public void onError(ANError anError) {
                                    btnRegister.revertAnimation();
                                    Toast.makeText(RegisterActivity.this, "ERROR LUR", Toast.LENGTH_SHORT).show();
                                    Log.d("GZS", "onError: " + anError.getErrorBody(    ));
                                    Log.d("GZS", "onError: " + anError.getLocalizedMessage());
                                    Log.d("GZS", "onError: " + anError.getErrorDetail());
                                    Log.d("GZS", "onError: " + anError.getResponse());
                                    Log.d("GZS  ", "onError: " + anError.getErrorCode());
                                }
                            });
                }
            }
        });
    }
}
