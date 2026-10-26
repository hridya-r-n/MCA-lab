package com.example.customadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
    Context context;
    String[] titles;
    int[] images;
    LayoutInflater inflator;


    public CustomAdapter(Context context, String[] titles, int[] images){
        this.context=context;
        this.titles=titles;
        this.images=images;
        this.inflator=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public String getItem(int position){
        return titles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        convertView=inflator.inflate(R.layout.list_item,parent,false);
        ImageView imageView=convertView.findViewById(R.id.img);
        TextView textView=convertView.findViewById(R.id.txt);
        textView.setText(titles[position]);
        imageView.setImageResource(images[position]);
        return convertView;
    }
}
