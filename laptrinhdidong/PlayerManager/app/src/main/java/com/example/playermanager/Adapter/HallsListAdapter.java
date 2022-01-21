package com.example.playermanager.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playermanager.Models.MD_Hall;
import com.example.playermanager.R;

import java.util.List;

public class HallsListAdapter extends RecyclerView.Adapter<HallsListAdapter.ViewHolder> {

    private Context context;
    private List<MD_Hall> hallsList;
    private HallsListAdapter.ClickListener clickListner;

    public HallsListAdapter(Context context,List<MD_Hall> model, HallsListAdapter.ClickListener listener) {
        this.context = context;
        this.hallsList = model;
        this.clickListner = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hall, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.name.setText(hallsList.get(position).getName());
        holder.address.setText(hallsList.get(position).getAddress());
        holder.capacity.setText(String.valueOf(hallsList.get(position).getTotalCapacity()));
        holder.reviews.setText(String.valueOf(hallsList.get(position).getReviewCount()));
        holder.ratingBar.setRating(Float.parseFloat(String.valueOf(hallsList.get(position).getRating())));


        /*holder.viewDetial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , HallDetailActivity.class);
                intent.putExtra("HallDataModel", hallsList.get(position));
                //intent.putExtra("hallName", hallsList.get(position).getName());
                //intent.putExtra("hallAddress", hallsList.get(position).getAddress());
                //intent.putExtra("hallCapacity", hallsList.get(position).getTotalCapacity());
                //intent.putExtra("hallReview", hallsList.get(position).getReviewCount());
                //intent.putExtra("hallRating", hallsList.get(position).getRating());
                context.startActivity(intent);
                //context.startActivity(new Intent(context, HallDetailActivity.class));
            }
        }); */

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListner.onClick(hallsList.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hallsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, address, capacity, reviews;
        RatingBar ratingBar;
        Button viewDetial;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.hallName);
            address = itemView.findViewById(R.id.hallAddress);
            capacity = itemView.findViewById(R.id.hallTotalCapacity);
            reviews = itemView.findViewById(R.id.hallTotalReviews);
            ratingBar = itemView.findViewById(R.id.rating);
            viewDetial = itemView.findViewById(R.id.hallViewDetails);
        }
    }

    public interface ClickListener {
        void onClick(MD_Hall model, int position);
    }
}

