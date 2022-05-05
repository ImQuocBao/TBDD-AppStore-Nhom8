package com.example.dhktpm15_appstore_nhom8.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.dhktpm15_appstore_nhom8.entity.Cart;

import java.util.List;

@Dao
public interface CartDao {

    @Query("SELECT * FROM CartDB")
    List<Cart> getAll();

    @Insert
    void insertSushi(Cart cart);
}
