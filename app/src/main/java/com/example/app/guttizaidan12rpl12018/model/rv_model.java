package com.example.app.guttizaidan12rpl12018.model;

public class rv_model {
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String nama;
    private String email;
    private String id;
    private String NOHP;
    private String ALAMAT;
    private String NOKTP;

    public String getNOHP() {
        return NOHP;
    }

    public void setNOHP(String NOHP) {
        this.NOHP = NOHP;
    }

    public String getALAMAT() {
        return ALAMAT;
    }

    public void setALAMAT(String ALAMAT) {
        this.ALAMAT = ALAMAT;
    }

    public String getNOKTP() {
        return NOKTP;
    }

    public void setNOKTP(String NOKTP) {
        this.NOKTP = NOKTP;
    }
}
