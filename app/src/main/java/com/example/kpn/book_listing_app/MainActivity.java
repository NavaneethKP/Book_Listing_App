package com.example.kpn.book_listing_app;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText et_search;
    private TextInputLayout tl_search;
    private String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_search = (TextInputEditText) findViewById(R.id.et_search);
        tl_search = (TextInputLayout) findViewById(R.id.tl_search);

    }

    public void displayBooks(View view) {

        String search_text=et_search.getText().toString();
        search_text=search_text.replace(" ","+");

        //TODO :set error for the TextInputEditText
        URL ="https://www.googleapis.com/books/v1/volumes?q="+search_text;
        Intent intent = new Intent(this, ListBooks.class);
        intent.putExtra("URL", URL);
        startActivity(intent);

    }
}
