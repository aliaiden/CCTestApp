package com.alihaider.myapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alihaider.myapp.R;
import com.alihaider.myapp.model.ForecastResponseDataModel;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class DaysForecastRVAdapter extends RecyclerView.Adapter<DaysForecastRVAdapter.ViewHolder> {
    private ArrayList<ForecastResponseDataModel> daysForecastList;
    private Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDay, tvTemperatureOne, tvTemperatureTwo;
        private ImageView ivWeatherImage;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDay = itemView.findViewById(R.id.tvDay);
            tvTemperatureOne = itemView.findViewById(R.id.tvTemperatureOne);
            tvTemperatureTwo = itemView.findViewById(R.id.tvTemperatureTwo);
            ivWeatherImage = itemView.findViewById(R.id.ivWeatherImage);
        }

    }

    public DaysForecastRVAdapter(Context context, ArrayList<ForecastResponseDataModel> notificationList) {
        this.daysForecastList = notificationList;
        this.mContext = context;
    }

    @Override
    public DaysForecastRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);
        View postView = inflater.inflate(R.layout.item_rv_days_forecast, parent, false);

        DaysForecastRVAdapter.ViewHolder viewHolder = new DaysForecastRVAdapter.ViewHolder(postView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final DaysForecastRVAdapter.ViewHolder holder, int position) {
        final ForecastResponseDataModel model = daysForecastList.get(position);

        if (model != null) {
//            holder.tvDay.setText(model.getDescription());
//            holder.tvTime.setText(model.getTime());
        }
    }

    @Override
    public int getItemCount() {
        return daysForecastList.size();
    }

    private ForecastResponseDataModel getItem(int adapterPosition) {
        return daysForecastList.get(adapterPosition);
    }


}