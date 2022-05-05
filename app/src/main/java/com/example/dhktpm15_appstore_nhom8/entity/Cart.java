package com.example.dhktpm15_appstore_nhom8.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "CartDB")
public class Cart {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="uid")
    private int uid;

    @ColumnInfo(name ="sushi")
    private int sushiId;

    @ColumnInfo(name = "count")
    private int count;

    public int getSushiId() {
        return sushiId;
    }

    public void setSushiId(int sushiId) {
        this.sushiId = sushiId;
    }

    public Cart() {
    }

    public Cart(int sushiId, int count) {
        this.sushiId = sushiId;
        this.count = count;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
