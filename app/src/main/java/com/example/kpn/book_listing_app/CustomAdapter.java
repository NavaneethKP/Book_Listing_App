package com.example.kpn.book_listing_app;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kpn on 12/8/17.
 */

public class CustomAdapter extends ArrayAdapter<Books> {


    private List<Books> objects;

    public CustomAdapter(@NonNull Context context, @LayoutRes int resource , List<Books> list )  {
        super(context, resource);
        this.objects=list;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Nullable
    @Override
    public Books getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Books object = objects.get(position);

        LayoutInflater inflater = LayoutInflater.from(getContext());

        View rowview = inflater.inflate(R.layout.list_item,parent,false);

        TextView tv_title = rowview.findViewById(R.id.tv_title);

        tv_title.setText(object.getTitle());

        return rowview;

    }
}
