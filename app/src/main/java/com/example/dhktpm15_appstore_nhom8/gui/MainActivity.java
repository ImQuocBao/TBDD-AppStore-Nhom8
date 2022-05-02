package com.example.dhktpm15_appstore_nhom8.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.dhktpm15_appstore_nhom8.R;

public class MainActivity extends AppCompatActivity {

    private List<Sushi> listSushi;
    private SushiAdapter sushiAdapter;
    private GridView gridView;
    private SushiDB sushiDB;
    private TextView tvName,tvDescription;
    private ImageButton btn_menu,ImageButton4,ImageButton2,ImageButton3;
    View oldViewSelected = null;
    int itemSelected = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        gridView=findViewById(R.id.gdView);
        this.initUI();
        List<Sushi> list=new ArrayList<>();
        Sushi theSushi=new Sushi(R.drawable.seekpng_2,"UnagiRol","$22,90");
        Sushi theSushi1=new Sushi(R.drawable.daco_139818_1,"Sake Furai","$21,90");
       listSushi=SushiDB.getInstance(this).sushiDao().getAll();
       sushiAdapter=new SushiAdapter(this,R.layout.row_items,listSushi);
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
    }

    private void initUI() {
        tvName = findViewById(R.id.textView);
        tvDescription=findViewById(R.id.textView2);
        btn_menu=findViewById(R.id.btn_menu);
        ImageButton2=findViewById(R.id.imageButton2);
        ImageButton3=findViewById(R.id.imageButton3);
        ImageButton4=findViewById(R.id.imageButton4);





    }


}