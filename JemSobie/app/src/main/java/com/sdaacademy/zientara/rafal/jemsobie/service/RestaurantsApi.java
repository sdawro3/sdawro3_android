package com.sdaacademy.zientara.rafal.jemsobie.service;

import com.sdaacademy.zientara.rafal.jemsobie.models.Restaurant;

import java.util.List;
import java.util.Observer;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Evil on 08.07.2017.
 */

public interface RestaurantsApi {
    @GET("/restaurants/.json")
    Call<List<Restaurant>> getAllRestaurants();

    @GET("/restaurants/{id}")
    Call<Restaurant> getRestaurant(@Path("id") Long id);

    @POST("/restaurants")
    Call<Restaurant> postRestaurants(@Body Restaurant restaurant);

    @PUT("/restaurants/{id}")
    Call<Restaurant> putRestaurants(@Path("id") Long id, @Body Restaurant restaurant);

    @DELETE("/restaurants/{id}")
    Call<Restaurant> deleteRestaurants(@Path("id") Long id);
}
