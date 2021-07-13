package com.khanhduy.doancuoiki.Object;

import java.io.Serializable;

public class DichVuDoAn implements Serializable {
    public int Id;
    public String tenmonan;
    public Integer giamonan;
    public String hinhanh;
    public String hinhanh1;
    public String hinhanh2;
    public String hinhanh3;
    public String mota;
    public int trangthai;

    public DichVuDoAn(int id, String tenmonan, Integer giamonan, String hinhanh, String hinhanh1, String hinhanh2, String hinhanh3, String mota, int trangthai) {
        Id = id;
        this.tenmonan = tenmonan;
        this.giamonan = giamonan;
        this.hinhanh = hinhanh;
        this.hinhanh1 = hinhanh1;
        this.hinhanh2 = hinhanh2;
        this.hinhanh3 = hinhanh3;
        this.mota = mota;
        this.trangthai = trangthai;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenmonan() {
        return tenmonan;
    }

    public void setTenmonan(String tenmonan) {
        this.tenmonan = tenmonan;
    }

    public Integer getGiamonan() {
        return giamonan;
    }

    public void setGiamonan(Integer giamonan) {
        this.giamonan = giamonan;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getHinhanh1() {
        return hinhanh1;
    }

    public void setHinhanh1(String hinhanh1) {
        this.hinhanh1 = hinhanh1;
    }

    public String getHinhanh2() {
        return hinhanh2;
    }

    public void setHinhanh2(String hinhanh2) {
        this.hinhanh2 = hinhanh2;
    }

    public String getHinhanh3() {
        return hinhanh3;
    }

    public void setHinhanh3(String hinhanh3) {
        this.hinhanh3 = hinhanh3;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
}
