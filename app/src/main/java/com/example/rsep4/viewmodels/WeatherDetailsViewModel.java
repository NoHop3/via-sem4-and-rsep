package com.example.rsep4.viewmodels;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rsep4.models.WeatherModel;
import com.example.rsep4.network.APIService;
import com.example.rsep4.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherDetailsViewModel extends ViewModel {
    private MutableLiveData<WeatherModel> weatherObject;
    private String city;
    public WeatherDetailsViewModel(){
        weatherObject = new MutableLiveData<>();
        city = "";
    }

    public MutableLiveData<WeatherModel> getWeatherObjectObserver() {
        return weatherObject;
    }

    public void createWeather(WeatherModel weatherToAdd){
        APIService apiService = RetrofitInstance.getRetrofitClient().create(APIService.class);
        Call<WeatherModel> call = apiService.addWeather(weatherToAdd);
        call.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(@NonNull Call<WeatherModel> call, @NonNull Response<WeatherModel> response) {
                Log.d("response", response.toString());
                weatherObject.postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<WeatherModel> call, @NonNull Throwable t) {
                Log.e("error creating weather", t.toString());

                weatherObject.postValue(null);
            }
        });
    }

    public void getWeatherDetails(String city){
        APIService apiService = RetrofitInstance.getRetrofitClient().create(APIService.class);
        Call<List<WeatherModel>> call = apiService.getWeatherForCity(city);
        Log.e("error weather details", call.request().toString());

        call.enqueue(new Callback<List<WeatherModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<WeatherModel>> call, @NonNull Response<List<WeatherModel>> response) {
                Log.d("response", response.body().toString());
                weatherObject.postValue(response.body().get(0));
            }

            @Override
            public void onFailure(@NonNull Call<List<WeatherModel>> call, @NonNull Throwable t) {
                Log.e("error weather details", t.toString());

                weatherObject.postValue(null);
            }
        });
    }

    public void updateWeatherDetails(String city, WeatherModel weatherToUpdate){
        APIService apiService = RetrofitInstance.getRetrofitClient().create(APIService.class);
        Call<WeatherModel> call = apiService.updateWeatherForCity(city, weatherToUpdate);
        call.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(@NonNull Call<WeatherModel> call, @NonNull Response<WeatherModel> response) {
                Log.d("response", response.toString());
                weatherObject.postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<WeatherModel> call, @NonNull Throwable t) {
                Log.d("response", t.toString());

                weatherObject.postValue(null);
            }
        });
    }

    public void deleteWeatherDetails(String city){
        APIService apiService = RetrofitInstance.getRetrofitClient().create(APIService.class);
        Call<WeatherModel> call = apiService.deleteWeatherForCity(city);
        call.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(@NonNull Call<WeatherModel> call, @NonNull Response<WeatherModel> response) {
                Log.d("response", response.toString());
                weatherObject.postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<WeatherModel> call, @NonNull Throwable t) {
                Log.d("response", t.toString());

                weatherObject.postValue(null);
            }
        });
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
