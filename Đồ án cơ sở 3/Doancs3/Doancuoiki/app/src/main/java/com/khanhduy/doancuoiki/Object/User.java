package com.khanhduy.doancuoiki.Object;

import java.io.Serializable;
import java.util.Date;

public class User  implements Serializable {
    public int id;
    public String name;
    public Date birthday;
    public String address;
    public String email;
    public String phone;
    public String taikhoan;
    public String password;

    public User() {

    }

    public User(int id, String name, Date birthday, String address, String email, String phone, String taikhoan, String password) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.taikhoan = taikhoan;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
