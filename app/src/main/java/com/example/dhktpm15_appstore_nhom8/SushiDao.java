package com.example.dhktpm15_appstore_nhom8;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SushiDao {
    @Query("SELECT * FROM sushi")
    List<Sushi> getAll();
    @Insert
    void insertAll(Sushi sushi);
}
