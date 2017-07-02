package com.shubham16598.moviewall;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    RecyclerView mrecyclerView;
    MyCustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Fetchdata().execute();


    }

    public final static String BASE_URL ="api.themoviedb.org";
    public final static String PATH ="3";
    public final static String PATH1 = "movie";
    public final static String SORT ="popular";
    public final static String API_KEY = "05947c19dcc1963b0c0e6fcb66a32aef";


    public static URL buildUrl() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority(BASE_URL)
                .appendPath(PATH)
                .appendPath(PATH1)
                .appendPath(SORT)
                .appendQueryParameter("api_key", API_KEY);

        URL url = null;
        try {
            url = new URL(builder.toString());
            Log.e("buildUrl: ", String.valueOf(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e("buildUrl: ","url problem" ,e);
        }

        return url;
    }



    public class Fetchdata extends AsyncTask<Object, Object, ArrayList<Information>> {
        public ArrayList<Information> data = new ArrayList<>();
        StringBuilder jsonString = new StringBuilder();
        HttpURLConnection conn;
        public String BASE_POSTER_URL = "http://image.tmdb.org/t/p/w342/";
        @Override
        protected ArrayList<Information> doInBackground(Object... voids) {
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) buildUrl().openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(10000);
                conn.setRequestMethod("GET");

                // setDoOutput to true as we recieve data from json file
                conn.setDoOutput(true);

            } catch (IOException e1) {

                e1.printStackTrace();
                Log.e("doInBackground: ","Connection problem" );
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                    String line;

                    while ((line = reader.readLine()) != null) {
                        jsonString.append(line);
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
                Log.e("doInBackground: ","Response error" );
            } finally {
                conn.disconnect();
            }



            try {

                JSONObject baseJsonObject = new JSONObject(String.valueOf(jsonString));
                Log.i("Data", String.valueOf(baseJsonObject));
                JSONArray result = baseJsonObject.getJSONArray("results");
                Log.e("Data", String.valueOf(result));
                for (int i = 0;i<result.length();i++){
                    JSONObject currentMovie = result.getJSONObject(i);
                    String posterPath = currentMovie.getString("poster_path");
                    Information current = new Information();
                    current.imageID = BASE_POSTER_URL+posterPath;
                    data.add(current);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Data", "Problem parsing the posters movies", e);
            }
            Log.e("Data",data.toString());
            return data;
        }

        @Override
        protected void onPostExecute(ArrayList<Information> data) {
            adapter=new MyCustomAdapter(MainActivity.this,data);
            mrecyclerView = (RecyclerView) findViewById(R.id.recycleView);
            mrecyclerView.setAdapter(adapter);
            mrecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));




        }
    }

}