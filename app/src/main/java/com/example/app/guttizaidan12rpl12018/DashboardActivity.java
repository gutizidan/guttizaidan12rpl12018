package com.example.app.guttizaidan12rpl12018;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

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
    private SliderView sliderView;
    private SliderAdapter sliderAdapter;
    private ArrayList<SliderModel> sliderModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        sliderView = findViewById(R.id.sliderInfo);
        setupSlider();
        getBanners();
    }
    private void setupSlider() {
        sliderAdapter = new SliderAdapter(sliderModels, this);
        sliderView.setSliderAdapter(sliderAdapter);
    }

    private void getBanners(){
        //retry on fail connection
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .build();
        AndroidNetworking.get("https://www.themealdb.com/api/json/v1/1/search.php?s=fish")
                .setPriority(Priority.LOW)
                .setOkHttpClient(okHttpClient)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("RBA", "MEALS: " + response);
                        try {
                            JSONArray payload = response.getJSONArray("meals");

                            for (int i = 0; i < payload.length(); i++) {
                                JSONObject data = payload.getJSONObject(i);
                                String SLD_IMG_PATH = data.getString("strMealThumb");
                                sliderModels.add(new SliderModel(
                                        SLD_IMG_PATH
                                ));
                            }
                            sliderAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("", "onError: " + anError.getErrorBody() );
                    }
                });
    }
}
