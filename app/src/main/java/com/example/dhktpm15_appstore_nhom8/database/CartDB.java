package com.example.dhktpm15_appstore_nhom8.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dhktpm15_appstore_nhom8.dao.CartDao;
import com.example.dhktpm15_appstore_nhom8.entity.Cart;

@Database(entities = {Cart.class}, version = 1)
public abstract class CartDB extends RoomDatabase {

    private static final String DATABASE_NAME = "CartDB.db";
    private static CartDB instance;

    public static synchronized CartDB getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), CartDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract CartDao cartDao();
}
