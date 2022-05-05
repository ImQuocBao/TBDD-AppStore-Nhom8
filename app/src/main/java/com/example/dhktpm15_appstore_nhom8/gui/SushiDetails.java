package com.example.dhktpm15_appstore_nhom8.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dhktpm15_appstore_nhom8.R;

public class SushiDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sushi_details);

        String txtName = getIntent().getExtras().getString("name");
        String txtDes = getIntent().getExtras().getString("des");
        String txtPrice = getIntent().getExtras().getString("price");
        int imgMain = getIntent().getExtras().getInt("img");

        TextView txtNameView = findViewById(R.id.txtName);
        TextView txtPriceView = findViewById(R.id.txtPrice);
        ImageView imgViewMain = findViewById(R.id.imgMain);
        txtNameView.setText(txtName);
        txtPriceView.setText(txtPrice);

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText edtCount = findViewById(R.id.edtCount);
                Intent intent = new Intent(SushiDetails.this, CartDetail.class);
                if(Integer.parseInt(edtCount.getText().toString()) < 1) {

                } else {

                }
                startActivity(intent);
            }
        });

        imgViewMain.setImageResource(imgMain);
    }
}