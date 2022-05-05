package com.example.dhktpm15_appstore_nhom8.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Sushi")
public class Sushi {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="uid")
    public int uid;
    @ColumnInfo(name = "img")
    private int img;
    @ColumnInfo(name="name")
    private String name;
    @ColumnInfo(name = "price")
    private String price;
    @ColumnInfo(name = "des")
    private String des;

    public Sushi(){

    }

    public Sushi(int img, String name, String price, String des) {
        this.img = img;
        this.name = name;
        this.price = price;
        this.des = des;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
