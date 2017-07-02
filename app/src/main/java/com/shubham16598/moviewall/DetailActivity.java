package com.shubham16598.moviewall;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    Context context = this;
    ImageView posterimage;
    ImageView back_image;
    TextView synopsis;
    TextView rating;
    TextView releasedate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Log.e("onCreate: ", "Started");
        posterimage = (ImageView)findViewById(R.id.poster);
        back_image = (ImageView)findViewById(R.id.app_bar_image);
        synopsis = (TextView)findViewById(R.id.synopsis);
        rating = (TextView)findViewById(R.id.rating);
        releasedate = (TextView)findViewById(R.id.release_date);
        try {
            getSupportActionBar().setTitle(getIntent().getStringExtra("originaltitle"));
            synopsis.setText(getIntent().getStringExtra("synopsis"));
            releasedate.setText(getIntent().getStringExtra("releasedate"));
            rating.setText(getIntent().getStringExtra("rating"));
            Picasso.with(context).load(getIntent().getStringExtra("backposter")).placeholder(R.drawable.placeholder).into(back_image);
            Picasso.with(context).load(getIntent().getStringExtra("poster")).into(posterimage);
        }catch (Exception e){
            Log.i("onCreate: ","sorry");
        }
    }
}