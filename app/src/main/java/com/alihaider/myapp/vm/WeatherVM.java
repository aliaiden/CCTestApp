package com.alihaider.myapp.vm;

import android.app.Application;

import com.alihaider.myapp.retro.RetrofitInstance;
import com.alihaider.myapp.retro.WebServices;
import com.google.gson.JsonElement;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;

public class WeatherVM extends AndroidViewModel {
    final MutableLiveData<String> errorMutableLiveData = new MutableLiveData<>();

    public WeatherVM(Application application) {
        super(application);
    }

    public LiveData<JsonElement> getCurrentWeather(String cityCountry, String apiKey) {
        final MutableLiveData<JsonElement> dataMutableLiveData = new MutableLiveData<>();

        WebServices getResponse = RetrofitInstance.getInstance().create(WebServices.class);
        Call<JsonElement> call = getResponse.userSignIn(cityCountry, apiKey);

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, retrofit2.Response<JsonElement> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        dataMutableLiveData.setValue(response.body().getAsJsonObject());

                    } else {
                        errorMutableLiveData.setValue("Null Body");
                    }
                } else {
                    errorMutableLiveData.setValue("Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                errorMutableLiveData.setValue("Something went wrong " + t.getMessage());
            }
        });

        return dataMutableLiveData;
    }

    public LiveData<String> weatherErrorObservable() {
        return errorMutableLiveData;
    }
}
