package com.alihaider.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

import android.os.Bundle;
import android.widget.Toast;

import com.alihaider.myapp.adapter.DaysForecastRVAdapter;
import com.alihaider.myapp.adapter.MainPagerAdapter;
import com.alihaider.myapp.model.ForecastResponseDataModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.indicator)
    CircleIndicator indicator;
    @BindView(R.id.rvDays)
    RecyclerView rvDays;

    MainPagerAdapter mainPagerAdapter;
    DaysForecastRVAdapter daysForecastRVAdapter;
    ArrayList<ForecastResponseDataModel> daysForecastList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        init();

        fetchWeatherDaysForecast();
    }

    private void fetchWeatherDaysForecast() {
        daysForecastList = new ArrayList<>();
        daysForecastRVAdapter = new DaysForecastRVAdapter(this, daysForecastList);
        rvDays.setAdapter(daysForecastRVAdapter);
    }

    private void init() {
        //init view pager
        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(mainPagerAdapter);
        indicator.setViewPager(viewPager);

        //init recyclerview
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        rvDays.setLayoutManager(layoutManager);
    }
}
