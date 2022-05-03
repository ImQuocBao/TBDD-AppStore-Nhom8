package com.example.dhktpm15_appstore_nhom8.gui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.dhktpm15_appstore_nhom8.R;
import com.example.dhktpm15_appstore_nhom8.database.SushiDB;
import com.example.dhktpm15_appstore_nhom8.entity.Sushi;
import com.example.dhktpm15_appstore_nhom8.gui.adapter.SushiAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    private List<Sushi> listSushi;
    private SushiAdapter sushiAdapter;
    private GridView gridView;
    private TextView tvName,tvDescription;
    private ImageButton btn_menu,ImageButton4,ImageButton2,ImageButton3;
    View oldViewSelected = null;
    int itemSelected = -1;
    private FirebaseAuth fAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // check user login
        fAuth = FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser() == null) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        this.initUI();

        sushiAdapter = new SushiAdapter(this, R.layout.row_items, listSushi);
        gridView.setAdapter(sushiAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                view.setBackgroundColor(getResources().getColor(R.color.black));
                oldViewSelected = view;
                System.out.println(i);
                itemSelected = i;
            }
        });

        Button btnLogOut = findViewById(R.id.btnSignOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fAuth.signOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void addSushiToLocalUsingRoom(List<Sushi> arrSushi) {
        for (Sushi ss: arrSushi) {
            SushiDB.getInstance(this).sushiDao().insertSushi(ss);
        }
    }

    public List<Sushi> getAllSushiFromLocal () {
        return SushiDB.getInstance(this).sushiDao().getAll();
    }

    public void addDataToFireStore() {
        for (Sushi ss: listSushi) {

            Map<String, Object> sushi = new HashMap<>();
            sushi.put("id", ss.getUid());
            sushi.put("name", ss.getName());
            sushi.put("img",  ss.getImg());
            sushi.put("des", ss.getDes());
            sushi.put("price", ss.getPrice());

            db.collection("sushi")
                .add(sushi)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Success ", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("error", "Error adding document", e);
                    }
                });
        }
    }

    private void initUI() {
        tvName = findViewById(R.id.textView);
        tvDescription=findViewById(R.id.textView2);
        ImageButton2=findViewById(R.id.imageButton2);
        gridView=findViewById(R.id.gdView);

        listSushi = new ArrayList<Sushi>();
        if(getAllSushiFromLocal().size() < 1) {
            listSushi = readDataFromFirebase();
            addSushiToLocalUsingRoom(listSushi);
        }
        listSushi = getAllSushiFromLocal();
//        addDataToFireStore();

        for (Sushi ss: listSushi) {
            Log.d("item" + ss.getUid(), ss.getName());
        }
    }

    public List<Sushi> readDataFromFirebase() {
        List<Sushi> arrSushi = new ArrayList<Sushi>();
        db.collection("sushi")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                int id = Integer.parseInt(document.getData().get("id").toString());
                                int img = Integer.parseInt(document.getData().get("img").toString());
                                String name = document.getData().get("name").toString();
                                String price = document.getData().get("price").toString();
                                String des = document.getData().get("des").toString();

                                Sushi ss = new Sushi( img, name, price, des);
                                ss.setUid(id);
                                arrSushi.add(ss);
                            }
                        } else {
                            Log.w("Error", "Error getting documents.", task.getException());
                        }
                    }
                });
        return arrSushi;
    }
}