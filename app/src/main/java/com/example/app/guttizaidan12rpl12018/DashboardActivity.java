package com.example.app.guttizaidan12rpl12018;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.app.guttizaidan12rpl12018.adapter.SliderAdapter;
import com.example.app.guttizaidan12rpl12018.model.SliderModel;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.OkHttpClient;

public class DashboardActivity extends AppCompatActivity {

    ImageView profile;
    private TextView nama;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        profile = findViewById(R.id.profile);
        SharedPreferences sharedPreferences = getSharedPreferences("Tugas PTS", MODE_PRIVATE);
        final String ide = sharedPreferences.getString("nama","");
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View dlgView = LayoutInflater.from(DashboardActivity.this).inflate(R.layout.dialog_profile, null);
                final Dialog dialog = new Dialog(DashboardActivity.this, android.R.style.Theme_Material_Dialog);
                nama = (TextView) dlgView.findViewById(R.id.tvNamaProfile);
                nama.setText(ide);



                ((LinearLayout) dlgView.findViewById(R.id.divDelete)).setOnClickListener(new View.OnClickListener() {
                    private void doNothing() {

                    }

                    @Override
                    public void onClick(View view) {
                        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Tugas PTS", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.apply();
                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        startActivity(i);
                        finish();

                    }
                });

                dialog.setContentView(dlgView);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });



    }

}
