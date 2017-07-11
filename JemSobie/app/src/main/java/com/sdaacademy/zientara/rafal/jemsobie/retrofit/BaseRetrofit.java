package com.sdaacademy.zientara.rafal.jemsobie.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sdaacademy.zientara.rafal.jemsobie.models.Restaurant;
import com.sdaacademy.zientara.rafal.jemsobie.service.RestaurantsApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Evil on 10.07.2017.
 */

public class BaseRetrofit {
    private static String ENDPOINT = "http://10.40.21.186:3000/";

    private final Retrofit retrofit;
    private final RestaurantsApi restaurantsApi;

    public BaseRetrofit() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())//potrzebne aby można było dać Observable zamiast Call
                .build();

        restaurantsApi = retrofit.create(RestaurantsApi.class);

        Call<List<Restaurant>> allRestaurantsCall = restaurantsApi.getAllRestaurants();
        allRestaurantsCall.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                if(response.isSuccessful()) {

                } else
                    ;//error

            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                t.getMessage();//error
            }
        });
    }

    public RestaurantsApi getRestaurantsApi() {
        return restaurantsApi;
    }
}
