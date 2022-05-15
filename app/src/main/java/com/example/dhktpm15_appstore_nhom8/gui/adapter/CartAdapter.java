package com.example.dhktpm15_appstore_nhom8.gui.adapter;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dhktpm15_appstore_nhom8.R;
import com.example.dhktpm15_appstore_nhom8.entity.Cart;

import java.util.List;

public class CartAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Cart> listCart;

    public CartAdapter(Context context, int layout, List<Cart> listCart) {
        this.context = context;
        this.layout = layout;
        this.listCart = listCart;
    }

    @Override
    public int getCount() {
        return listCart.size();
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


        ImageView imgMain = view.findViewById(R.id.imageView2);
        TextView txtName = view.findViewById(R.id.txtName);
        TextView txtPrice = view.findViewById(R.id.txtPrice);
        TextView txtQuan = view.findViewById(R.id.txtCount);
        Cart cartItem = listCart.get(i);

        txtName.setText(cartItem.getName());
        txtPrice.setText(cartItem.getPrice() + " $");
        Log.d("cart", cartItem.toString());
        imgMain.setImageResource(cartItem.getImage());
        txtQuan.setText(cartItem.getQuantity() + "");

        return view;
    }
}
