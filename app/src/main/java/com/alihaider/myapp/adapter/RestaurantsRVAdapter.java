package com.alihaider.myapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.alihaider.myapp.R;
import com.alihaider.myapp.model.RestaurantsDataModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class RestaurantsRVAdapter extends RecyclerView.Adapter<RestaurantsRVAdapter.ViewHolder> {
    private ArrayList<RestaurantsDataModel> notificationList;
    private Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvCity, tvRating;
        private ImageView ivImage;
        private RatingBar rbRating;

        public ViewHolder(View itemView) {
            super(itemView);
            tvRating = itemView.findViewById(R.id.tvRating);
            tvName = itemView.findViewById(R.id.tvName);
            tvCity = itemView.findViewById(R.id.tvCity);
            ivImage = itemView.findViewById(R.id.ivImage);
            rbRating = itemView.findViewById(R.id.rbRating);
        }

    }

    public RestaurantsRVAdapter(Context context, ArrayList<RestaurantsDataModel> notificationList) {
        this.notificationList = notificationList;
        this.mContext = context;
    }

    @Override
    public RestaurantsRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.item_rv_restaurant, parent, false);

        RestaurantsRVAdapter.ViewHolder viewHolder = new RestaurantsRVAdapter.ViewHolder(postView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RestaurantsRVAdapter.ViewHolder holder, int position) {
        final RestaurantsDataModel model = notificationList.get(position);

        if (model != null) {
            Picasso.get().load(model.getImageURL()).into(holder.ivImage);

            holder.tvRating.setText(model.getRating() + "");
            holder.tvName.setText(model.getName());
            holder.tvCity.setText(model.getLocation());
            holder.rbRating.setRating(model.getRating());
        }
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    private RestaurantsDataModel getItem(int adapterPosition) {
        return notificationList.get(adapterPosition);
    }


}