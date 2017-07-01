package com.shubham16598.moviewall;

import java.util.ArrayList;

/**
 * Created by shubham16598 on 1/7/17.
 */

public class Data {
    public static ArrayList<Information> getData(){
        ArrayList<Information> data = new ArrayList<>();
       int image =  R.drawable.ani_deer_three;
        for (int i = 0; i < 20; i++) {

            Information current = new Information();
            current.imageID = image;

            data.add(current);
        }
        return data;
    }
}
