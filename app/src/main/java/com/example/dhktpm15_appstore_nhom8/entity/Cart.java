package com.example.dhktpm15_appstore_nhom8.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "CartDB")
public class Cart {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @NonNull
    private String id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "image")
    private int image;

    @ColumnInfo(name = "price")
    private String price;

    @ColumnInfo(name = "quantity")
    private int quantity;

    @ColumnInfo(name = "totalPrice")
    private float totalPrice;

    public Cart(String key, String name, int image, String price, int quantity, float totalPrice) {
        this.id = key;
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Cart() {
    }

    public String getId() {
        return id;
    }

    public void setId(String key) {
        this.id = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalQuantity) {
        this.totalPrice = totalQuantity;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image=" + image +
                ", price='" + price + '\'' +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
