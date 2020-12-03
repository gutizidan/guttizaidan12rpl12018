package com.example.app.guttizaidan12rpl12018;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.app.guttizaidan12rpl12018.adapter.rv_adaptercar;
import com.example.app.guttizaidan12rpl12018.model.car_model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminCarManageActivity extends AppCompatActivity {
    private List<car_model> rv_modelArrayList = new ArrayList<>();
    private rv_adaptercar rv_adapter1;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout ;
    private TextView TvDialog2;
    private EditText etEmailDialog,etNamaDialog,etNoHPDialog,etNoKTPDialog,etAlamatDialog;
    private String nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_car_manage);
        SharedPreferences sharedPreferences = getSharedPreferences("Tugas PTS", MODE_PRIVATE);
        final String id = sharedPreferences.getString("id", "");
        recyclerView = findViewById(R.id.my_recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_view);
        nama = sharedPreferences.getString("nama","");

        HashMap<String, String> body = new HashMap<>();
        body.put("id", id);
        AndroidNetworking.post("http://192.168.6.136/TugasAPI2/showcaradmin.php")
                .addBodyParameter(body)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("GZS", "respon : " + response);

                            String status = response.optString("STATUS");
                            String message = response.optString("MESSAGE");
                            String sender = response.optString("SENDER");
                            if (status.equalsIgnoreCase("SUCCESS")) {
                                JSONArray orders = response.optJSONObject("PAYLOAD").optJSONArray("DATA");

                                if (orders == null) return;
                                System.out.println(orders.length()+"gzs");

                                for (int i = 0; i < orders.length(); i++) {
                                    final JSONObject aData = orders.optJSONObject(i);
                                    System.out.println(aData.get("ID")+"ayo ojo error :(");
                                    car_model item = new car_model();
                                    item.setId(aData.getString("ID"));
                                    item.setKode(aData.getString("KODE"));
                                    item.setMerk(aData.getString("MERK"));
                                    item.setJenis(aData.getString("JENIS"));
                                    item.setWarna(aData.getString("WARNA"));
                                    item.setHargasewa(aData.getString("HARGASEWA"));

                                    rv_modelArrayList.add(item);
                                }rv_adapter1.notifyDataSetChanged();



                            } else {
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getApplicationContext(), "ERROR LUR", Toast.LENGTH_SHORT).show();
                        Log.d("GZS", "onError: " + anError.getErrorBody());
                        Log.d("GZS", "onError: " + anError.getLocalizedMessage());
                        Log.d("GZS", "onError: " + anError.getErrorDetail());
                        Log.d("GZS", "onError: " + anError.getResponse());
                        Log.d("GZS  ", "onError: " + anError.getErrorCode());
                    }
                });

        rv_adapter1 = new rv_adaptercar(getApplicationContext(), rv_modelArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(rv_adapter1);
        rv_adapter1.setOnItemClickCallback(new rv_adaptercar.OnItemClickCallback() {
            @Override
            public void onItemClicked(car_model data) {
                showSelectedHero(data);
            }
        });


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rv_modelArrayList.clear();
                HashMap<String, String> body = new HashMap<>();
                body.put("id", id);
                AndroidNetworking.post("http://192.168.6.136/TugasAPI2/show_user.php")
                        .addBodyParameter(body)
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    Log.d("GZS", "respon : " + response);

                                    String status = response.optString("STATUS");
                                    String message = response.optString("MESSAGE");
                                    String sender = response.optString("SENDER");
                                    if (status.equalsIgnoreCase("SUCCESS")) {
                                        JSONArray orders = response.optJSONObject("PAYLOAD").optJSONArray("DATA");

                                        if (orders == null) return;
                                        System.out.println(orders.length()+"gzs");

                                        for (int i = 0; i < orders.length(); i++) {
                                            final JSONObject aData = orders.optJSONObject(i);
                                            System.out.println(aData.get("ID")+"ayo ojo error :(");
                                            car_model item = new car_model();
                                            item.setId(aData.getString("ID"));
                                            item.setKode(aData.getString("KODE"));
                                            item.setMerk(aData.getString("MERK"));
                                            item.setJenis(aData.getString("JENIS"));
                                            item.setWarna(aData.getString("WARNA"));
                                            item.setHargasewa(aData.getString("HARGASEWA"));

                                            rv_modelArrayList.add(item);
                                        }rv_adapter1.notifyDataSetChanged();



                                    } else {
                                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            @Override
                            public void onError(ANError anError) {
                                Toast.makeText(getApplicationContext(), "ERROR LUR", Toast.LENGTH_SHORT).show();
                                Log.d("GZS", "onError: " + anError.getErrorBody());
                                Log.d("GZS", "onError: " + anError.getLocalizedMessage());
                                Log.d("GZS", "onError: " + anError.getErrorDetail());
                                Log.d("GZS", "onError: " + anError.getResponse());
                                Log.d("GZS  ", "onError: " + anError.getErrorCode());
                            }
                        });

                rv_adapter1 = new rv_adaptercar(getApplicationContext(), rv_modelArrayList);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(rv_adapter1);
                rv_adapter1.setOnItemClickCallback(new rv_adaptercar.OnItemClickCallback() {
                    @Override
                    public void onItemClicked(car_model data) {
                        showSelectedHero(data);
                    }
                });

                swipeRefreshLayout.setRefreshing(false);

            }
        });


    }
    private void showSelectedHero(final car_model hero) {
        Toast.makeText(this, "Kamu memilih " + hero.getKode(), Toast.LENGTH_SHORT).show();
        View dlgView = LayoutInflater.from(AdminCarManageActivity.this).inflate(R.layout.dialog_car, null);
        final Dialog dialog = new Dialog(AdminCarManageActivity.this, android.R.style.Theme_Material_Dialog);

        etEmailDialog = dlgView.findViewById(R.id.etEmailDialog);
        etNamaDialog = dlgView.findViewById(R.id.etNamaDialog);
        etNoHPDialog = dlgView.findViewById(R.id.etNoHPDialog);
        etNoKTPDialog = dlgView.findViewById(R.id.etNoKTPDialog);
        etAlamatDialog = dlgView.findViewById(R.id.etAlamatDialog);

        etEmailDialog.setText(hero.getKode());
        etNamaDialog.setText(hero.getMerk());
        etNoHPDialog.setText(hero.getJenis());
        etNoKTPDialog.setText(hero.getWarna());
        etAlamatDialog.setText(hero.getHargasewa());
        SharedPreferences sharedPreferences = getSharedPreferences("Tugas PTS", MODE_PRIVATE);
        final String id_auth = sharedPreferences.getString("id", "");


        ((LinearLayout) dlgView.findViewById(R.id.divSaveUser)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AndroidNetworking.post("http://192.168.6.136/TugasAPI2/updatecar.php")
                        .addBodyParameter("kode", etEmailDialog.getText().toString().trim().toUpperCase())
                        .addBodyParameter("merk", etNamaDialog.getText().toString().trim().toUpperCase())
                        .addBodyParameter("jenis", etNoHPDialog.getText().toString().trim().toUpperCase())
                        .addBodyParameter("warna", etNoKTPDialog.getText().toString().trim().toUpperCase())
                        .addBodyParameter("hargasewa", etAlamatDialog.getText().toString().trim().toUpperCase())
                        .addBodyParameter("id", hero.getId())
                        .addBodyParameter("id_auth", id_auth)
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("GZS", "respon : " + response);

                                String status = response.optString("STATUS");
                                String message = response.optString("MESSAGE");
                                if (status.equalsIgnoreCase("SUCCESS")) {
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onError(ANError anError) {
                                Toast.makeText(getApplicationContext(), "ERROR LUR", Toast.LENGTH_SHORT).show();
                                Log.d("GZS", "onError: " + anError.getErrorBody(    ));
                                Log.d("GZS", "onError: " + anError.getLocalizedMessage());
                                Log.d("GZS", "onError: " + anError.getErrorDetail());
                                Log.d("GZS", "onError: " + anError.getResponse());
                                Log.d("GZS  ", "onError: " + anError.getErrorCode());
                            }
                        });
            }
        });



        dialog.setContentView(dlgView);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }
    public void viewAdminLogout(View v){
        View dlgView = LayoutInflater.from(AdminCarManageActivity.this).inflate(R.layout.dialog_profile, null);
        final Dialog dialog = new Dialog(AdminCarManageActivity.this, android.R.style.Theme_Material_Dialog);
        TvDialog2 = (TextView) dlgView.findViewById(R.id.tvNamaProfile);
        TvDialog2.setText(nama);



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

}
