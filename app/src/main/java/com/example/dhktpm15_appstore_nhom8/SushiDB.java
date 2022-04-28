package com.example.dhktpm15_appstore_nhom8;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
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
