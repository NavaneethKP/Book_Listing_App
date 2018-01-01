package com.example.kpn.book_listing_app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by kpn on 12/8/17.
 */

public class CustomAdapter extends ArrayAdapter<Books> {

    private Context context;
    private List<Books> objects;

    public CustomAdapter(@NonNull Context context, @LayoutRes int resource , List<Books> list )  {
        super(context, resource);
        this.context = context;
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

        final Books object = objects.get(position);

        LayoutInflater inflater = LayoutInflater.from(getContext());

        View rowview = inflater.inflate(R.layout.list_item,parent,false);

        RelativeLayout rl_list = rowview.findViewById(R.id.rl_list);
        TextView tv_title = rowview.findViewById(R.id.tv_title);
        TextView tv_author = rowview.findViewById(R.id.tv_author);
        ImageView iv_image = rowview.findViewById(R.id.iv_image);

        rl_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri webpage_url = Uri.parse(object.getVolumeLink());
                Intent webview = new Intent(Intent.ACTION_VIEW, webpage_url);
                v.getContext().startActivity(webview);

            }
        });

        tv_title.setText(object.getTitle());
        tv_author.setText(object.getAuthor());

        Picasso.with(context).load(object.getImageUrl()).into(iv_image);

        return rowview;

    }
}
