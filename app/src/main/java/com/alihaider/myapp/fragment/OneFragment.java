package com.alihaider.myapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alihaider.myapp.R;
import com.alihaider.myapp.RestaurantsListActivity;
import com.alihaider.myapp.vm.WeatherVM;
import com.google.gson.JsonElement;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindAnim;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.alihaider.myapp.Utils.isInternetConnectionAvailable;

public class OneFragment extends Fragment {
    @BindView(R.id.tvDay)
    TextView tvDay;
    @BindView(R.id.tvWeatherDescription)
    TextView tvWeatherDescription;
    @BindView(R.id.tvTemperature)
    TextView tvTemperature;
    @BindView(R.id.tvHumidity)
    TextView tvHumidity;
    @BindView(R.id.tvWind)
    TextView tvWind;
    @BindView(R.id.tvPrecipitation)
    TextView tvPrecipitation;
    @BindView(R.id.ivWeatherImage)
    ImageView ivWeatherImage;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private View view;
    private WeatherVM viewModel;

    String weatherDescription, centigrade, fahrenheit, dayOfTheWeek, humidity, windSpeed;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view = v;
        ButterKnife.bind(this, view);

        viewModel = ViewModelProviders.of(this).get(WeatherVM.class);

        if (isInternetConnectionAvailable(3000)) {
            progressBar.setVisibility(View.VISIBLE);
            //handling of webservice error
            viewModel.weatherErrorObservable()
                    .observe(this, errorMessage -> Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show());

            viewModel.getCurrentWeather("Karachi,PK", "b6907d289e10d714a6e88b30761fae22")
                    .observe(this, apiResponse -> consumeResponse(apiResponse));
        } else {
            Toast.makeText(getActivity(), "Please check internet and try again", Toast.LENGTH_SHORT).show();
        }
    }

    private void consumeResponse(JsonElement apiResponse) {
        fahrenheit = apiResponse.getAsJsonObject().get("list").getAsJsonArray().get(0).getAsJsonObject().get("main").getAsJsonObject().get("temp").getAsString();
        fahrenheit = "" + Math.round((Float.parseFloat(fahrenheit)));
        centigrade = "" + Math.round((Float.parseFloat(fahrenheit) - 32) * 5 / 9);
        weatherDescription = apiResponse.getAsJsonObject().get("list").getAsJsonArray().get(0).getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").getAsString();
        humidity = apiResponse.getAsJsonObject().get("list").getAsJsonArray().get(0).getAsJsonObject().get("main").getAsJsonObject().get("humidity").getAsString();
        windSpeed = apiResponse.getAsJsonObject().get("list").getAsJsonArray().get(0).getAsJsonObject().get("wind").getAsJsonObject().get("speed").getAsString();

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        dayOfTheWeek = sdf.format(d);

        tvDay.setText(dayOfTheWeek);
        tvWeatherDescription.setText(weatherDescription);
        tvTemperature.setText(centigrade);
        tvHumidity.setText(humidity);
        tvWind.setText(windSpeed);
        tvPrecipitation.setText(humidity);
        ivWeatherImage.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_weather));

        progressBar.setVisibility(View.GONE);
    }

    @OnClick({R.id.tvRestaurants, R.id.tvCentigrade, R.id.tvFahrenheit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvCentigrade:
                tvTemperature.setText(centigrade);
                break;
            case R.id.tvFahrenheit:
                tvTemperature.setText(fahrenheit);
                break;
            case R.id.tvRestaurants:
                startActivity(new Intent(getActivity(), RestaurantsListActivity.class));
                break;
        }
    }
}