package com.example.dhktpm15_appstore_nhom8.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.dhktpm15_appstore_nhom8.entity.Sushi;

import java.util.List;

@Dao
public interface SushiDao {

    @Query("SELECT * FROM Sushi")
    public List<Sushi> getAll();

    @Insert
    void insertSushi(Sushi sushi);

    @Query("SELECT * FROM Sushi WHERE uid = :uid")
    Sushi getSushiFromId(int uid);
}
