package com.example.kpn.book_listing_app;

/**
 * Created by kpn on 12/8/17.
 */

public class Books {

    String title;
    String author;

    public Books(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
