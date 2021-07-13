package com.khanhduy.doancuoiki.Object;

import java.io.Serializable;

public class Phong implements Serializable {
    public int id;
    public String tenphong;
    public Integer giaphong;
    public String hinh1;
    public String hinh2;
    public String hinh3;
    public String hinh4;
    public Integer giamoi;
    public int giamgia;
    public String mota;
    public int idPhong;
    public int trangthai;

    public Phong(int id, String tenphong, Integer giaphong, String hinh1, String hinh2, String hinh3, String hinh4, Integer giamoi, int giamgia, String mota, int idPhong, int trangthai) {
        this.id = id;
        this.tenphong = tenphong;
        this.giaphong = giaphong;
        this.hinh1 = hinh1;
        this.hinh2 = hinh2;
        this.hinh3 = hinh3;
        this.hinh4 = hinh4;
        this.giamoi = giamoi;
        this.giamgia = giamgia;
        this.mota = mota;
        this.idPhong = idPhong;
        this.trangthai = trangthai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenphong() {
        return tenphong;
    }

    public void setTenphong(String tenphong) {
        this.tenphong = tenphong;
    }

    public Integer getGiaphong() {
        return giaphong;
    }

    public void setGiaphong(Integer giaphong) {
        this.giaphong = giaphong;
    }

    public String getHinh1() {
        return hinh1;
    }

    public void setHinh1(String hinh1) {
        this.hinh1 = hinh1;
    }

    public String getHinh2() {
        return hinh2;
    }

    public void setHinh2(String hinh2) {
        this.hinh2 = hinh2;
    }

    public String getHinh3() {
        return hinh3;
    }

    public void setHinh3(String hinh3) {
        this.hinh3 = hinh3;
    }

    public String getHinh4() {
        return hinh4;
    }

    public void setHinh4(String hinh4) {
        this.hinh4 = hinh4;
    }

    public Integer getGiamoi() {
        return giamoi;
    }

    public void setGiamoi(Integer giamoi) {
        this.giamoi = giamoi;
    }

    public int getGiamgia() {
        return giamgia;
    }

    public void setGiamgia(int giamgia) {
        this.giamgia = giamgia;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public int getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(int idPhong) {
        this.idPhong = idPhong;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
}
