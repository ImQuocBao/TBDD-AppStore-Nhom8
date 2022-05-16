package com.example.dhktpm15_appstore_nhom8.gui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dhktpm15_appstore_nhom8.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddSushi extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sushi);

        EditText edtName = findViewById(R.id.edtName);
        EditText edtPrice = findViewById(R.id.edtPrice);
        EditText edtUrl = findViewById(R.id.edtURl);

        Button btnAddProduct = findViewById(R.id.btnAddProduct);

        int id = getIntent().getExtras().getInt("id");

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = edtName.getText().toString();
                String price = edtPrice.getText().toString();
                String url = edtUrl.getText().toString();

                Map<String, Object> sushi = new HashMap<>();
                sushi.put("name", name);
                sushi.put("price", price);
                sushi.put("img", url);
                sushi.put("des", "");
                sushi.put("id", id);

                db.collection("sushi")
                        .add(sushi)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("SUCCESS", "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("Fail", "Error adding document", e);
                            }
                        });
            }
        });


    }
}