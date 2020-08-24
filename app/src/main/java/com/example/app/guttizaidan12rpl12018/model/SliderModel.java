package com.example.app.guttizaidan12rpl12018.model;

import android.os.Parcel;
import android.os.Parcelable;


public class SliderModel implements Parcelable {
    private int IMG_ID;
    private int UNIT_ID;
    private String IMG_PATH;

    public int getIMG_ID() {
        return IMG_ID;
    }

    public void setIMG_ID(int IMG_ID) {
        this.IMG_ID = IMG_ID;
    }

    public int getUNIT_ID() {
        return UNIT_ID;
    }

    public void setUNIT_ID(int UNIT_ID) {
        this.UNIT_ID = UNIT_ID;
    }

    public String getIMG_PATH() {
        return IMG_PATH;
    }

    public void setIMG_PATH(String IMG_PATH) {
        this.IMG_PATH = IMG_PATH;
    }



    public SliderModel(String IMG_PATH){
        this.IMG_PATH = IMG_PATH;
    }

    public SliderModel(Parcel in ){
        IMG_PATH = in.readString();
    }

    public static final Creator<SliderModel> CREATOR = new Creator<SliderModel>() {
        @Override
        public SliderModel createFromParcel(Parcel in) {
            return new SliderModel(in);
        }

        @Override
        public SliderModel[] newArray(int size) {
            return new SliderModel[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(IMG_PATH);
    }


//    private String sldJudul;
//    private String sldImgPath;
//    private String sldUrl;
//
//    public SliderModel(String sldJudul, String sldImgPath, String sldUrl) {
//        this.sldJudul = sldJudul;
//        this.sldImgPath = sldImgPath;
//        this.sldUrl = sldUrl;
//    }
//
//    public String getSldJudul() {
//        return sldJudul;
//    }
//
//    public String getSldImgPath() {
//        return sldImgPath;
//    }
//
//    public String getSldUrl() {
//        return sldUrl;
//    }
}


