package com.example.kpn.book_listing_app;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kpn on 13/8/17.
 */

public class BookLoader extends AsyncTaskLoader<List<Books>> {

    private String url;

    public BookLoader(Context context, String url)
    {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<Books> loadInBackground()
    {
        List<Books> list;

        if ( url != null)
        {
            list = QueryUtils.extractBooks(url) ;
            return list ;
        }

        return null ;

    }
}
