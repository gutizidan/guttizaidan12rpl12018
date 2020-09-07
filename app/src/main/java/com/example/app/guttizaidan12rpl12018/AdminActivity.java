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
import com.example.app.guttizaidan12rpl12018.adapter.rv_adapter;
import com.example.app.guttizaidan12rpl12018.model.rv_model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminActivity extends AppCompatActivity {
    private List<rv_model> rv_modelArrayList = new ArrayList<>();
    private rv_adapter rv_adapter1;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout ;
    private TextView TvDialog,TvDialogNama,TvDialog2;
    private String nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        SharedPreferences sharedPreferences = getSharedPreferences("Tugas PTS", MODE_PRIVATE);
        final String id = sharedPreferences.getString("id", "");
        recyclerView = findViewById(R.id.my_recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_view);
        nama = sharedPreferences.getString("nama","");

        HashMap<String, String> body = new HashMap<>();
        body.put("id", id);
        AndroidNetworking.post("http://192.168.6.82/TugasAPI2/show_user.php")
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
                                    rv_model item = new rv_model();
                                    item.setId(aData.getString("ID"));
                                    item.setEmail(aData.getString("EMAIL"));
                                    item.setNama(aData.getString("NAMA"));

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

        rv_adapter1 = new rv_adapter(getApplicationContext(), rv_modelArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(rv_adapter1);
        rv_adapter1.setOnItemClickCallback(new rv_adapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(rv_model data) {
                showSelectedHero(data);
            }
        });


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rv_modelArrayList.clear();
                HashMap<String, String> body = new HashMap<>();
                body.put("id", id);
                AndroidNetworking.post("http://192.168.6.82/TugasAPI2/show_user.php")
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
                                            rv_model item = new rv_model();
                                            item.setId(aData.getString("ID"));
                                            item.setEmail(aData.getString("EMAIL"));
                                            item.setNama(aData.getString("NAMA"));

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

                rv_adapter1 = new rv_adapter(getApplicationContext(), rv_modelArrayList);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(rv_adapter1);
                rv_adapter1.setOnItemClickCallback(new rv_adapter.OnItemClickCallback() {
                    @Override
                    public void onItemClicked(rv_model data) {
                        showSelectedHero(data);
                    }
                });

                swipeRefreshLayout.setRefreshing(false);

            }
        });


    }
    private void showSelectedHero(final rv_model hero) {
        Toast.makeText(this, "Kamu memilih " + hero.getNama(), Toast.LENGTH_SHORT).show();
        View dlgView = LayoutInflater.from(AdminActivity.this).inflate(R.layout.dialog_profile, null);
        final Dialog dialog = new Dialog(AdminActivity.this, android.R.style.Theme_Material_Dialog);
        TvDialog = dlgView.findViewById(R.id.TvLogout);
        TvDialog.setText("Delete");
        TvDialogNama = dlgView.findViewById(R.id.tvNamaProfile);
        TvDialogNama.setText(hero.getNama());
        ((LinearLayout) dlgView.findViewById(R.id.divDelete)).setOnClickListener(new View.OnClickListener() {
            private void doNothing() {

            }

            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("Tugas PTS", MODE_PRIVATE);
                final String ide = sharedPreferences.getString("id", "");
                HashMap<String, String> body = new HashMap<>();
                body.put("id", ide);
                body.put("id_delete", hero.getId());
                AndroidNetworking.post("http://192.168.6.82/TugasAPI2/delete.php")
                        .addBodyParameter(body)
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                String status = response.optString("STATUS");
                                String message = response.optString("MESSAGE");
                                String sender = response.optString("SENDER");
                                if (status.equalsIgnoreCase("SUCCESS")) {
                                    JSONObject payload = response.optJSONObject("PAYLOAD");

                                    Toast.makeText(AdminActivity.this, message, Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();


                                }
                                else {
                                    Toast.makeText(AdminActivity.this, message, Toast.LENGTH_SHORT).show();
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

            }
        });

        dialog.setContentView(dlgView);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }
    public void viewAdminLogout(View v){
        View dlgView = LayoutInflater.from(AdminActivity.this).inflate(R.layout.dialog_profile, null);
        final Dialog dialog = new Dialog(AdminActivity.this, android.R.style.Theme_Material_Dialog);
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
