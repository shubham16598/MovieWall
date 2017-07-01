package com.shubham16598.moviewall;

import android.net.Uri;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by shubham16598 on 1/7/17.
 */

public class AsyncCall {
    public final static String BASE_URL ="https://api.themoviedb.org";
    public final static String PATH ="3/movie";
    public final static String SORT ="popular";
    public final static String API_KEY = "05947c19dcc1963b0c0e6fcb66a32aef";


    public static URL buildUrl() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority(BASE_URL)
                .appendPath(PATH)
                .appendPath(SORT)
                .appendQueryParameter("api_key", API_KEY);

        URL url = null;
        try {
            url = new URL(builder.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl() throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) buildUrl().openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }



    public class Fetchdata extends AsyncTask<Void, Void, String> {
        public String getresponse = null;

        @Override
        protected String doInBackground(Void... voids) {
            try {
                getresponse = getResponseFromHttpUrl();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return getresponse;

        }

    }
}
