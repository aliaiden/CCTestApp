package com.alihaider.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alihaider.myapp.adapter.RestaurantsRVAdapter;
import com.alihaider.myapp.model.RestaurantsDataModel;

import java.util.ArrayList;

public class RestaurantsListActivity extends AppCompatActivity {
    @BindView(R.id.tvSortBy)
    TextView tvSortBy;
    @BindView(R.id.tvFilter)
    TextView tvFilter;

    @BindView(R.id.rvRestaurants)
    RecyclerView rvRestaurants;
    private ArrayList<RestaurantsDataModel> restaurantsList;
    RestaurantsRVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants_list);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        init();
        fetchRestaurants();
    }

    private void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvRestaurants.setLayoutManager(layoutManager);
    }

    private void fetchRestaurants() {
        restaurantsList = new ArrayList<>();

        restaurantsList.add(new RestaurantsDataModel("1", "ABC Restaurant", "Karachi", "https://media-cdn.tripadvisor.com/media/photo-s/0e/cc/0a/dc/restaurant-chocolat.jpg", 5, 15));
        restaurantsList.add(new RestaurantsDataModel("1", "DEF Restaurant", "Karachi", "https://media-cdn.tripadvisor.com/media/photo-s/0e/cc/0a/dc/restaurant-chocolat.jpg", 4, 24));
        restaurantsList.add(new RestaurantsDataModel("1", "HIJ Restaurant", "Karachi", "https://media-cdn.tripadvisor.com/media/photo-s/0e/cc/0a/dc/restaurant-chocolat.jpg", 3, 33));
        restaurantsList.add(new RestaurantsDataModel("1", "KLM Restaurant", "Karachi", "https://media-cdn.tripadvisor.com/media/photo-s/0e/cc/0a/dc/restaurant-chocolat.jpg", 2, 42));
        restaurantsList.add(new RestaurantsDataModel("1", "NOP Restaurant", "Karachi", "https://media-cdn.tripadvisor.com/media/photo-s/0e/cc/0a/dc/restaurant-chocolat.jpg", 1, 51));
        restaurantsList.add(new RestaurantsDataModel("1", "QRS Restaurant", "Karachi", "https://media-cdn.tripadvisor.com/media/photo-s/0e/cc/0a/dc/restaurant-chocolat.jpg", 5, 12));
        restaurantsList.add(new RestaurantsDataModel("1", "ABC Restaurant", "Karachi", "https://media-cdn.tripadvisor.com/media/photo-s/0e/cc/0a/dc/restaurant-chocolat.jpg", 5, 12));
        restaurantsList.add(new RestaurantsDataModel("1", "ABC Restaurant", "Karachi", "https://media-cdn.tripadvisor.com/media/photo-s/0e/cc/0a/dc/restaurant-chocolat.jpg", 5, 12));
        restaurantsList.add(new RestaurantsDataModel("1", "ABC Restaurant", "Karachi", "https://media-cdn.tripadvisor.com/media/photo-s/0e/cc/0a/dc/restaurant-chocolat.jpg", 5, 12));
        restaurantsList.add(new RestaurantsDataModel("1", "ABC Restaurant", "Karachi", "https://media-cdn.tripadvisor.com/media/photo-s/0e/cc/0a/dc/restaurant-chocolat.jpg", 5, 12));
        restaurantsList.add(new RestaurantsDataModel("1", "ABC Restaurant", "Karachi", "https://media-cdn.tripadvisor.com/media/photo-s/0e/cc/0a/dc/restaurant-chocolat.jpg", 5, 12));
        restaurantsList.add(new RestaurantsDataModel("1", "ABC Restaurant", "Karachi", "https://media-cdn.tripadvisor.com/media/photo-s/0e/cc/0a/dc/restaurant-chocolat.jpg", 5, 12));

        adapter = new RestaurantsRVAdapter(this, restaurantsList);
        rvRestaurants.setAdapter(adapter);
    }

    @OnClick({R.id.tvSortBy, R.id.tvFilter, R.id.ivBack})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.tvSortBy:
                tvSortBy.setTextColor(getResources().getColor(R.color.colorBrown));
                tvFilter.setTextColor(getResources().getColor(android.R.color.black));

                PopupMenu popup1 = new PopupMenu(RestaurantsListActivity.this, tvSortBy);
                popup1.getMenuInflater().inflate(R.menu.menu_sort_by, popup1.getMenu());

                popup1.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(RestaurantsListActivity.this, "Sort By " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popup1.show();
                break;
            case R.id.tvFilter:
                tvFilter.setTextColor(getResources().getColor(R.color.colorBrown));
                tvSortBy.setTextColor(getResources().getColor(android.R.color.black));

                PopupMenu popup2 = new PopupMenu(RestaurantsListActivity.this, tvFilter);
                popup2.getMenuInflater().inflate(R.menu.menu_filter_by, popup2.getMenu());

                popup2.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(RestaurantsListActivity.this, "Filter By " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popup2.show();
                break;
        }
    }
}
