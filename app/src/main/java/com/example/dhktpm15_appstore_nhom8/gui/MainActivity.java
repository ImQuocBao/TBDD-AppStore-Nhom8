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
import android.widget.ImageView;
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

        initUI();

        sushiAdapter = new SushiAdapter(this, R.layout.row_items, listSushi);
        gridView.setAdapter(sushiAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, SushiDetails.class);
                Bundle b = new Bundle();
                b.putString("name", listSushi.get(i).getName());
                b.putString("price", listSushi.get(i).getPrice());
                b.putString("img", listSushi.get(i).getImg());
                b.putString("id", listSushi.get(i).getUid() + "");
                b.putInt("size", listSushi.size());
                intent.putExtras(b);
                startActivity(intent);

            }
        });

        ImageView btnCart = findViewById(R.id.btnCart);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CartDetail.class);
                startActivity(intent);
            }
        });

//        Button btnAdd = findViewById(R.id.btnAddItem);
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, AddSushi.class);
//                Bundle b = new Bundle();
//                b.putInt("id", listSushi.size());
//                intent.putExtras(b);
//                startActivity(intent);
//            }
//        });

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
        tvDescription = findViewById(R.id.textView2);
        gridView = findViewById(R.id.gv_suShi);

        listSushi = new ArrayList<Sushi>();
//        listSushi.add(new Sushi(1,R.drawable.sushi1, "Combo 1", "99.99", ""));
//        listSushi.add(new Sushi(2,R.drawable.sushi2, "Combo 2", "79.39", ""));
//        listSushi.add(new Sushi(3,R.drawable.sushi3, "Sashimi", "120.0", ""));
//        listSushi.add(new Sushi(4,R.drawable.sushi4, "Combo 3", "199.99", ""));
//        listSushi.add(new Sushi(5,R.drawable.sushi5, "Combo 4", "399.99", ""));
//        listSushi.add(new Sushi(6,R.drawable.sushi6, "Combo 5", "39.99", ""));
//
//        addDataToFireStore();
        if(getAllSushiFromLocal().size() < 1) {
            List<Sushi> arrSushi = new ArrayList<Sushi>();
            db.collection("sushi")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    int id = Integer.parseInt(document.getData().get("id").toString());
                                    String img = document.getData().get("img").toString();
                                    String name = document.getData().get("name").toString();
                                    String price = document.getData().get("price").toString();

                                    Sushi ss = new Sushi(id, img, name, price, "");
                                    ss.setUid(id);
                                    arrSushi.add(ss);
                                }
                                Log.d("Vo", "onComplete: " + arrSushi.toString());
                                listSushi.clear();
                                listSushi.addAll(arrSushi);
                                sushiAdapter.notifyDataSetChanged();
                            } else {
                                Log.w("Error", "Error getting documents.", task.getException());
                            }
                        }
                    });

            addSushiToLocalUsingRoom(listSushi);
        }else {
            listSushi = getAllSushiFromLocal();
        }
    }
}