package com.example.dhktpm15_appstore_nhom8.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dhktpm15_appstore_nhom8.dao.SushiDao;
import com.example.dhktpm15_appstore_nhom8.entity.Sushi;

@Database(entities = {Sushi.class}, version = 1)
public abstract class SushiDB extends RoomDatabase {

    private static final String DATABASE_NAME = "sushi.db";
    private static SushiDB instance;

    public static synchronized SushiDB getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), SushiDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
            return instance;
    }

    public  abstract SushiDao sushiDao();

}
