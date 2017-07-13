package com.sdaacademy.zientara.rafal.jemsobie.retrofit;

import android.support.annotation.NonNull;

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
//    private static String ENDPOINT = "http://10.40.21.186:3000/";
    private static String ENDPOINT = "http://192.168.1.7:3000/";
//    private static String ENDPOINT = "https://restaurants-8560c.firebaseio.com/";

    private final Retrofit retrofit;
    private final RestaurantsApi restaurantsApi;

    public BaseRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .build();

        restaurantsApi = retrofit.create(RestaurantsApi.class);
    }

    @NonNull
    private Gson getGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .setLenient()
                .create();
    }

    public RestaurantsApi getRestaurantsApi() {
        return restaurantsApi;
    }
}
