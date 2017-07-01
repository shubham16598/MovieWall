package com.shubham16598.moviewall;
import android.icu.text.IDNA;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by shubham16598 on 1/7/17.
 */

public class Data {
    public String BASE_POSTER_URL = " http://image.tmdb.org/t/p/w185/";

    public String jsonString;
    //Dummy Data
    /*public static ArrayList<Information> getData(){
        ArrayList<Information> data = new ArrayList<>();
       int image =  R.drawable.ani_deer_three;
        for (int i = 0; i < 20; i++) {

            Information current = new Information();
            current.imageID = image;

            data.add(current);
        }
        return data;
    }*/
    public ArrayList<Information> getData() throws ExecutionException, InterruptedException {
        AsyncCall outer = new AsyncCall();
        jsonString = outer.new Fetchdata()
                .execute().get();
        ArrayList<Information> data = new ArrayList<>();
        try {

            JSONObject baseJsonObject = new JSONObject(jsonString);
            JSONArray result = baseJsonObject.getJSONArray("results");
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
        return data;


    }


}
