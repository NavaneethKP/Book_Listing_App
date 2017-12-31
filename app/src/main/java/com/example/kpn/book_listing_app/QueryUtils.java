package com.example.kpn.book_listing_app;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kpn on 13/8/17.
 */

public class QueryUtils  {

    private static String ITEMS = "items";
    private static String VOLUME_INFO = "volumeInfo";
    private static String VOLUME_INFO_TITLE = "title";
    private static String VOLUME_INFO_AUTHORS = "authors";
    private static String IMAGE_LINKS = "imageLinks";
    private static String IMAGE_URL = "smallThumbnail";
    private static String VOLUME_LINK = "canonicalVolumeLink";

    //TODO:Add more features like Author etc.

    public QueryUtils()
    {
    }

    public static  List<Books> extractBooks (String url_string)
    {
        URL url = createurl(url_string);
        String jsonResponse = "";
        List<Books> books = new ArrayList<>();
        //Log.d("TAG3","response:"+jsonResponse);
        try
        {
            jsonResponse = makeHttpRequest(url);
            books = getFeatures(jsonResponse);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return books;
    }

    private static List<Books> getFeatures(String jsonResponse)
    {
        List<Books> books = new ArrayList<>();

        if (TextUtils.isEmpty(jsonResponse))
            return null ;


        JSONObject root = null;
        try {
            root = new JSONObject(jsonResponse);
            JSONArray items = root.getJSONArray(ITEMS);

            for (int i = 0; i < ListBooks.RESULT_NO; i++) {
                JSONObject object = items.getJSONObject(i);
                JSONObject volumeInfo = object.getJSONObject(VOLUME_INFO);
                String title = volumeInfo.getString(VOLUME_INFO_TITLE);

                /*JSONArray authors = volumeInfo.getJSONArray(VOLUME_INFO_AUTHORS);

                if(authors.getString(0)!=null)
                    author = authors.getString(0);
                */

                JSONObject imageLinks = volumeInfo.getJSONObject(IMAGE_LINKS);
                String imageUrl = imageLinks.getString(IMAGE_URL);


                String volumeLink = volumeInfo.getString(VOLUME_LINK);
                books.add(new Books(title,imageUrl,volumeLink,"author"));
            }
        }catch (JSONException e)
        {e.printStackTrace();}

        return books ;


    }

    private static String makeHttpRequest(URL url) throws IOException {

        String json_response = "";
        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;

        if ( url == null)
        {
            return json_response;
        }

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200)
            {
                inputStream = urlConnection.getInputStream();
                json_response = getResponse(inputStream);
                return json_response;
            }
            else Log.d("TAG", "Error code : " + urlConnection.getResponseCode());
        }

        catch (IOException e)
        {
            Log.e("TAG" , " Error in url " , e );
        }

        finally
        {
            if ( urlConnection != null )
                urlConnection.disconnect();

            if ( inputStream != null)
                inputStream.close();

        }

        return json_response;

    }

    private static String getResponse(InputStream inputStream) throws IOException
    {
        StringBuilder builder = new StringBuilder();

       if ( inputStream != null)
       {
           InputStreamReader inputStreamReader = new InputStreamReader( inputStream , Charset.forName("UTF-8"));
           BufferedReader reader = new BufferedReader( inputStreamReader );
           String line = reader.readLine();
            while( line != null )
            {
                builder.append(line);
                line = reader.readLine();
            }

            return builder.toString();
       }
        return null ;

    }

    private static URL createurl(String url_string)
    {
        URL url = null;
        try
        {
            url = new URL(url_string);
        }

        catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;

    }


}
