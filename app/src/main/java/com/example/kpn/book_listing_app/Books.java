package com.example.kpn.book_listing_app;

/**
 * Created by kpn on 12/8/17.
 */

public class Books {

    String title;
    String imageUrl, volumeLink,author;


    public Books(String title, String imageUrl, String volumeLink, String author) {
        this.title = title;
        this.imageUrl = imageUrl ;
        this.volumeLink = volumeLink;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

     public String getImageUrl(){return imageUrl;}

    public String getVolumeLink() {return volumeLink;}

    public String getAuthor() {return author;}
}
