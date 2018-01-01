package com.example.kpn.book_listing_app;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class ListBooks extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Books>> {

    public static final int RESULT_NO = 10;
    private CustomAdapter adapter;
    private ListView lv_books;
    private TextView tv_nothing;
    private ProgressBar pb_books;

    private String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_books);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        URL = i.getStringExtra("URL");
        lv_books = (ListView) findViewById(R.id.lv_books);
        tv_nothing = (TextView) findViewById(R.id.tv_nothing);
        pb_books = (ProgressBar) findViewById(R.id.pb_books);
        lv_books.setEmptyView(tv_nothing);

        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = networkInfo!=null && networkInfo.isConnectedOrConnecting();

        if(isConnected)
            getLoaderManager().initLoader(0, null, this);

        else {
            pb_books.setVisibility(View.GONE);
            tv_nothing.setText("Check Network connection");
        }

    }


    @Override
    public Loader<List<Books>> onCreateLoader(int i, Bundle bundle) {
        return new BookLoader(this,URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Books>> loader, List<Books> data) {

        pb_books.setVisibility(View.GONE);

        if( data != null )
        {
            adapter = new CustomAdapter(this, R.layout.list_item, data);
            lv_books.setAdapter(adapter);
        }

        else
            tv_nothing.setText("No results found");
    }

    @Override
    public void onLoaderReset(Loader<List<Books>> loader)
    {
        if(adapter!=null)
            adapter.clear();
    }
}
