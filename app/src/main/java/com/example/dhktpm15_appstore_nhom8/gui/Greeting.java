package com.example.dhktpm15_appstore_nhom8.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.dhktpm15_appstore_nhom8.R;

import java.util.Objects;

public class Greeting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);

        Button btnChoose = findViewById(R.id.btnChoose);

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Greeting.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}