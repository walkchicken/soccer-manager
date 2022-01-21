package com.example.playermanager.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.playermanager.Models.MD_Review;
import com.example.playermanager.R;

import java.util.ArrayList;


public class ReviewsListAdapter extends RecyclerView.Adapter<ReviewsListAdapter.ViewHolder> {

    Context context;
    ArrayList<MD_Review> mdReviews;

    public ReviewsListAdapter(Context context, ArrayList<MD_Review> mdReviews) {
        this.context = context;
        this.mdReviews = mdReviews;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_review, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,review;
        RatingBar rating;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            review = itemView.findViewById(R.id.review);
            rating = itemView.findViewById(R.id.rating);
        }
    }
}

