package com.example.sqlite2_tablayout.model;

import java.io.Serializable;

public class CongViec implements Serializable {
    private int id;
    private String tenCV,noidungCV,ngayHT,tinhtrang,congtac;

    public CongViec() {
    }

    public CongViec(String tenCV, String noidungCV, String ngayHT, String tinhtrang, String congtac) {
        this.tenCV = tenCV;
        this.noidungCV = noidungCV;
        this.ngayHT = ngayHT;
        this.tinhtrang = tinhtrang;
        this.congtac = congtac;
    }

    public CongViec(int id, String tenCV, String noidungCV, String ngayHT, String tinhtrang, String congtac) {
        this.id = id;
        this.tenCV = tenCV;
        this.noidungCV = noidungCV;
        this.ngayHT = ngayHT;
        this.tinhtrang = tinhtrang;
        this.congtac = congtac;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenCV() {
        return tenCV;
    }

    public void setTenCV(String tenCV) {
        this.tenCV = tenCV;
    }

    public String getNoidungCV() {
        return noidungCV;
    }

    public void setNoidungCV(String noidungCV) {
        this.noidungCV = noidungCV;
    }

    public String getNgayHT() {
        return ngayHT;
    }

    public void setNgayHT(String ngayHT) {
        this.ngayHT = ngayHT;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public String getCongtac() {
        return congtac;
    }

    public void setCongtac(String congtac) {
        this.congtac = congtac;
    }
}
