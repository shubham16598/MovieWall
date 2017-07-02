package com.shubham16598.moviewall;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by shubham16598 on 1/7/17.
 */

public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.MyViewHolder>{

    private ArrayList<Information> data;
    private Context context;
    public LayoutInflater inflater;

    public MyCustomAdapter(Context context, ArrayList<Information> data) {

        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public MyCustomAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = inflater.inflate(R.layout.grid_items, parent, false);

        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }
    @Override
    public void onBindViewHolder(MyCustomAdapter.MyViewHolder holder,final int position) {
        final Information getdata = data.get(position);
        Picasso.with(context).load(getdata.getImageID()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,DetailActivity.class);
                i.putExtra("poster",getdata.getImageID());
                i.putExtra("synopsis", getdata.getSynopsis());
                i.putExtra("rating",getdata.getRating());
                i.putExtra("backposter",getdata.getBackPoster());
                i.putExtra("releasedate",getdata.getReleaseDate());
                i.putExtra("originaltitle",getdata.getOriginalTitle());
                context.startActivity(i);
                Log.e(TAG, "onClick: Intent starteed" );

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.poster_image);
        }
    }
}
