package com.khanhduy.doancuoiki.Object;

public class DonDatMonAn {
    private int MaDonPhong;
    private String TenKhachHang;
    private String Email;

    public DonDatMonAn( int maDonPhong, String tenKhachHang, String email) {
        MaDonPhong = maDonPhong;
        TenKhachHang = tenKhachHang;
        Email = email;
    }


    public int getMaDonPhong() {
        return MaDonPhong;
    }

    public void setMaDonPhong(int maDonPhong) {
        MaDonPhong = maDonPhong;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        TenKhachHang = tenKhachHang;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
