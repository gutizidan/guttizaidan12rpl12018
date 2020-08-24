package com.example.app.guttizaidan12rpl12018.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.example.app.guttizaidan12rpl12018.R;
import com.example.app.guttizaidan12rpl12018.model.SliderModel;
import com.smarteist.autoimageslider.SliderViewAdapter;


import java.util.ArrayList;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.ViewHolder> {

    private ArrayList<SliderModel> sliderModels;
    private Context context;
    public SliderAdapter(ArrayList<SliderModel> sliderModels, Context context) {
        this.sliderModels = sliderModels;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final SliderModel data = sliderModels.get(position);
        viewHolder.bind(data);
//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(data.getSldUrl().contains("http") || data.getSldUrl().contains("https") ){
//                    Intent httpIntent = new Intent(Intent.ACTION_VIEW);
//                    httpIntent.setData(Uri.parse(data.getSldUrl()));
//                    context.startActivity(httpIntent);
//                }
//            }
//        });
    }

    @Override
    public int getCount() {
        return sliderModels.size();
    }

    class ViewHolder extends SliderViewAdapter.ViewHolder {
        private ImageView ivBanner;
        private View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            ivBanner = itemView.findViewById(R.id.iv_banner);
        }

        void bind(final SliderModel slider){
                Glide.with(context).load(slider.getIMG_PATH()).into(ivBanner);
        }
    }
}
