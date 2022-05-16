package com.example.dhktpm15_appstore_nhom8.gui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dhktpm15_appstore_nhom8.R;
import com.example.dhktpm15_appstore_nhom8.entity.Cart;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class SushiDetails extends AppCompatActivity {

    private FirebaseAuth fAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sushi_details);

        String txtName = getIntent().getExtras().getString("name");
        String txtPrice = getIntent().getExtras().getString("price");
        String id = getIntent().getExtras().getString("id");
        int imgMain = getIntent().getExtras().getInt("img");
        Log.d("id" , id + "");

        TextView txtNameView = findViewById(R.id.txtName);
        TextView txtPriceView = findViewById(R.id.txtPrice);
        ImageView imgViewMain = findViewById(R.id.imgMain);
        txtNameView.setText(txtName);
        txtPriceView.setText(txtPrice);

        ImageButton btnBack = findViewById(R.id.imageButton);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SushiDetails.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText edtCount = findViewById(R.id.edtCount);

                Intent intent = new Intent(SushiDetails.this, CartDetail.class);

                DatabaseReference userCart = FirebaseDatabase
                        .getInstance("https://projectandroidnhom8-default-rtdb.asia-southeast1.firebasedatabase.app")
                        .getReference("Cart").child(fAuth.getCurrentUser().getUid());;

                userCart.child(id + "")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                int quantity = 1;

                                if(Integer.parseInt(edtCount.getText().toString()) > 1) {
                                    quantity = Integer.parseInt(edtCount.getText().toString());
                                }

                                if (snapshot.exists()){
                                    Cart cart = snapshot.getValue(Cart.class);
                                    cart.setQuantity(cart.getQuantity() + quantity);
                                    Map<String,Object> updateData = new HashMap<>();
                                    updateData.put("quantity",cart.getQuantity());
                                    updateData.put("totalPrice",cart.getQuantity()*Float.parseFloat(cart.getPrice()));

                                    userCart.child(id + "")
                                            .updateChildren(updateData)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful()) {
                                                        Log.d("DONE", "add thanh cong");
                                                    }
                                                }
                                            });
                                }
                                else {
                                    Cart cart = new Cart();
                                    cart.setName(txtName);
                                    cart.setImage(imgMain);
                                    cart.setId(id);
                                    cart.setQuantity(quantity);
                                    cart.setPrice(txtPrice);
                                    cart.setTotalPrice(Float.parseFloat(txtPrice));

                                    userCart.child(id + "")
                                            .setValue(cart)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Log.d("Succes", "ADD THANH CONG");
                                                }
                                            });
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });

                startActivity(intent);
            }
        });

        imgViewMain.setImageResource(imgMain);
    }
}