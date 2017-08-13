package com.example.kpn.book_listing_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListBooks extends AppCompatActivity {

    private List<Books> booksList = new ArrayList<>();
    private CustomAdapter adapter;
    private ListView lv_books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_books);


        booksList.add(new Books("Title 1","Author 1"));
        booksList.add(new Books("Title 2","Author 1"));
        booksList.add(new Books("Title 3","Author 1"));
        booksList.add(new Books("Title 4","Author 1"));
        booksList.add(new Books("Title 5","Author 1"));
        booksList.add(new Books("Title 6","Author 1"));
        booksList.add(new Books("Title 7","Author 1"));
        booksList.add(new Books("Title 8","Author 1"));
        booksList.add(new Books("Title 9","Author 1"));

        adapter = new CustomAdapter(this,R.layout.list_item,booksList);
        lv_books = (ListView) findViewById(R.id.lv_books);
        lv_books.setAdapter(adapter);
    }
}
