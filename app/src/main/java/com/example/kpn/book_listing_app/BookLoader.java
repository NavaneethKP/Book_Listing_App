package com.example.kpn.book_listing_app;

import android.content.AsyncTaskLoader;
import android.content.Context;

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
        List<Books> list = new ArrayList<>();

        if ( url != null)
        {
            try
            {
                list = QueryUtils.extractBooks(url) ;
            }
            catch (JSONException e) {
                e.printStackTrace();
            }

            return list ;
        }

        return null ;

    }
}
