package com.shubham16598.moviewall;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by shubham16598 on 1/7/17.
 */

public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.MyViewHolder>{

    private ArrayList<Information> data;
    private Context mcontext;
    public LayoutInflater inflater;

    public MyCustomAdapter(Context context, ArrayList<Information> data) {

        mcontext = context;
        this.data = data;
        inflater = LayoutInflater.from(mcontext);
    }


    @Override
    public MyCustomAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = inflater.inflate(R.layout.grid_items, parent, false);

        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyCustomAdapter.MyViewHolder holder,final int position) {
        Picasso.with(mcontext).load(new Information().imageID).into(holder.imageView);
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
