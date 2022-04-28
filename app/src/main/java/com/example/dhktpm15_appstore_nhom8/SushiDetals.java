package com.example.dhktpm15_appstore_nhom8;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.resources.TextAppearance;

public class SushiDetals extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detals);

        String txtName=getIntent().getExtras().getString("name");
        String txtPrice=getIntent().getExtras().getString("price");
        String txtDes=getIntent().getExtras().getString("des");
        int imgMain=getIntent().getExtras().getInt("img");

        TextView txtNameView=findViewById(R.id.txtName);
        TextView txtPriceView=findViewById(R.id.txtPrice);
        TextView txtDesView=findViewById(R.id.txtDes);
        ImageView imgViewMain=findViewById(R.id.imgMain);


        txtNameView.setText(txtName);
        txtPriceView.setText(txtPrice);
        txtDesView.setText(txtDes);
        imgViewMain.setImageResource(imgMain);



    }
}
