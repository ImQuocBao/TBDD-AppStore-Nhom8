package com.example.dhktpm15_appstore_nhom8.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.dhktpm15_appstore_nhom8.R;

import java.util.List;

public class CartDetail extends AppCompatActivity {

    ListView lvCart;
//    List<Cart> listCart;
//    CartAdapter adapter;
    int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_detail);
        lvCart = findViewById(R.id.lv_item1);

        mapping ();

//        adapter = new CartAdapter(this, R.layout.item_cart, listCart);
//        lvCart.setAdapter(adapter);
    }

    public void mapping () {
//        listCart = new ArrayList<Cart>();
//        listCart.add(new Cart("Unagi Roll", R.drawable.seekpng_1, 23));
//        listCart.add(new Cart("Unagi Roll 2", R.drawable.seekpng_1, 23));
    }
}