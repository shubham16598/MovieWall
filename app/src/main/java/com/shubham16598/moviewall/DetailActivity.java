package com.shubham16598.moviewall;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.fujiyuu75.sequent.Animation;
import com.fujiyuu75.sequent.Sequent;
import com.squareup.picasso.Picasso;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

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
        ScrollView scroll = (ScrollView)findViewById(R.id.scrollview);
        Sequent.origin(scroll).anim(context, Animation.FADE_IN_UP).start();


        Log.e("onCreate: ", "Started");
        posterimage = (ImageView)findViewById(R.id.poster);
        back_image = (ImageView)findViewById(R.id.app_bar_image);
        synopsis = (TextView)findViewById(R.id.synopsis);
        rating = (TextView)findViewById(R.id.rating);
        releasedate = (TextView)findViewById(R.id.release_date);

        Bundle bundle = getIntent().getExtras();
        float rating1 = bundle.getFloat("rating");
        String rating2 = Float.toString(rating1);
        try {
            getSupportActionBar().setTitle(getIntent().getStringExtra("originaltitle"));
            synopsis.setText(getIntent().getStringExtra("synopsis"));
            releasedate.setText(getIntent().getStringExtra("releasedate"));
            rating.setText(rating2);
            Picasso.with(context).load(getIntent().getStringExtra("backposter")).placeholder(R.drawable.placeholder).into(back_image);
            Picasso.with(context).load(getIntent().getStringExtra("poster")).into(posterimage);
        }catch (Exception e){
            Log.i("onCreate: ","sorry");
        }
        TextView sy = (TextView)findViewById(R.id.heading);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),"fonts/Fresca-Regular.ttf");
        synopsis.setTypeface(custom_font);
        sy.setTypeface(custom_font);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==android.R.id.home) {
            finish();
        }
        return true;
    }
}
