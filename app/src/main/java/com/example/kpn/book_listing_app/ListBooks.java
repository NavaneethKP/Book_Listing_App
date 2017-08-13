package com.example.kpn.book_listing_app;



import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListBooks extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Books>> {

    private List<Books> booksList = new ArrayList<>();
    private CustomAdapter adapter;
    private ListView lv_books;

    private String URL ="https://www.googleapis.com/books/v1/volumes?q=android&maxResults=3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_books);

        lv_books = (ListView) findViewById(R.id.lv_books);
        getLoaderManager().initLoader(0, null, this);


    }


    @Override
    public Loader<List<Books>> onCreateLoader(int i, Bundle bundle) {
        return new BookLoader(this,URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Books>> loader, List<Books> data) {

        if( data != null)
        {
            adapter = new CustomAdapter(this, R.layout.list_item, data);
            lv_books.setAdapter(adapter);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Books>> loader) {
        adapter.clear();
    }
}
