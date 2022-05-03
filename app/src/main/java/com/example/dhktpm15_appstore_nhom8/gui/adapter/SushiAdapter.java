package com.example.dhktpm15_appstore_nhom8.gui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dhktpm15_appstore_nhom8.R;
import com.example.dhktpm15_appstore_nhom8.entity.Sushi;

import java.util.List;

public class SushiAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Sushi>listSushi;

    public SushiAdapter(Context context, int layout, List<Sushi> listSushi) {
        this.context = context;
        this.layout = layout;
        this.listSushi = listSushi;
    }

    @Override
    public int getCount() {
      return   listSushi.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        TextView tvName=view.findViewById(R.id.tvName);
        TextView tvPrice=view.findViewById(R.id.tvPrice);
        ImageView img=view.findViewById(R.id.imgView_main);

        Sushi itemsushi=listSushi.get(i);
        tvName.setText(itemsushi.getName());
        tvPrice.setText(itemsushi.getPrice());
        img.setImageResource(itemsushi.getImg());

        return view;
    }
}
