package com.khanhduy.doancuoiki.Object;

public class LoaiPhong{
   public int Id;
   public String Tenloaiphong;
   public String Hinhanhloaiphong;

    public LoaiPhong(int id, String tenloaiphong, String hinhanhloaiphong) {
        Id = id;
        Tenloaiphong = tenloaiphong;
        Hinhanhloaiphong = hinhanhloaiphong;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenloaiphong() {
        return Tenloaiphong;
    }

    public void setTenloaiphong(String tenloaiphong) {
        Tenloaiphong = tenloaiphong;
    }

    public String getHinhanhloaiphong() {
        return Hinhanhloaiphong;
    }

    public void setHinhanhloaiphong(String hinhanhloaiphong) {
        Hinhanhloaiphong = hinhanhloaiphong;
    }
}
