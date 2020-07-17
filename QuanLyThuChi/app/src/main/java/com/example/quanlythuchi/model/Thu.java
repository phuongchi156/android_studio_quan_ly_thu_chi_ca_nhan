package com.example.quanlythuchi.model;

public class Thu {
    private int id;
    private String Tenkhoanthu;
    private String Sotien;
    private String Thoigian;

    public Thu(int id, String tenkhoanthu, String sotien, String thoigian) {
        this.id = id;
        Tenkhoanthu = tenkhoanthu;
        Sotien = sotien;
        Thoigian = thoigian;
    }

    public Thu() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenkhoanthu() {
        return Tenkhoanthu;
    }

    public void setTenkhoanthu(String tenkhoanthu) {
        Tenkhoanthu = tenkhoanthu;
    }

    public String getSotien() {
        return Sotien;
    }

    public void setSotien(String sotien) {
        Sotien = sotien;
    }

    public String getThoigian() {
        return Thoigian;
    }

    public void setThoigian(String thoigian) {
        Thoigian = thoigian;
    }
}
