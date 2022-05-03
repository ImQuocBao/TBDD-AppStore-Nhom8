package com.example.dhktpm15_appstore_nhom8.gui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dhktpm15_appstore_nhom8.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth fAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText Email = findViewById(R.id.edtRegEmail);
        EditText password = findViewById(R.id.edtRegPass);
        Button btnLogin = findViewById(R.id.btnLogin);
        progressDialog = new ProgressDialog(this);

        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null ) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            Bundle b = new Bundle();
            b.putString("emailUser", fAuth.getCurrentUser().getEmail());
            intent.putExtras(b);
            startActivity(intent);
            finishAffinity();
        }

        TextView txtRegister = findViewById(R.id.txtRegister);
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email.getText().toString().trim();
                String passwordFeild = password.getText().toString().trim();
                progressDialog.show();
                fAuth.signInWithEmailAndPassword(email, passwordFeild).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            Bundle b = new Bundle();
                            b.putString("emailUser", email);
                            intent.putExtras(b);
                            startActivity(intent);
                            finishAffinity();
                        } else {
                            Toast.makeText(LoginActivity.this, "Error !!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}