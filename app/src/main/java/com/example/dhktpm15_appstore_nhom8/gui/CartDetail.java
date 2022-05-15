package com.example.dhktpm15_appstore_nhom8.gui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dhktpm15_appstore_nhom8.R;
import com.example.dhktpm15_appstore_nhom8.entity.Cart;
import com.example.dhktpm15_appstore_nhom8.gui.adapter.CartAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CartDetail extends AppCompatActivity {

    ListView lvCart;
    List<Cart> listCart;
    CartAdapter adapter;
    int position = -1;
    private FirebaseAuth fAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_detail);
        lvCart = findViewById(R.id.lv_item1);

        mapping ();

        adapter = new CartAdapter(this, R.layout.item_cart, listCart);
        lvCart.setAdapter(adapter);
    }

    public void mapping () {
        listCart = new ArrayList<Cart>();
        List<Cart> arrCart = new ArrayList<>();
        FirebaseDatabase.getInstance("https://projectandroidnhom8-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("Cart")
                .child(fAuth.getCurrentUser().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            int sum = 0;
                            for (DataSnapshot drinkSnapshot : snapshot.getChildren()){
                                Cart cart = drinkSnapshot.getValue(Cart.class);
                                arrCart.add(cart);
                                sum += cart.getTotalPrice();

                            }
                            listCart.clear();
                            listCart.addAll(arrCart);
                            TextView txtTotal = findViewById(R.id.txtTotal);
                            txtTotal.setText(sum + "$");
                            adapter.notifyDataSetChanged();
                        }
                        else{

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }
}