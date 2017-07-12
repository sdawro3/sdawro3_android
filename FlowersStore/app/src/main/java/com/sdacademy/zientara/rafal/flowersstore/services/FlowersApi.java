package com.sdacademy.zientara.rafal.flowersstore.services;

import com.sdacademy.zientara.rafal.flowersstore.models.Flower;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Evil on 12.07.2017.
 */

public interface FlowersApi {

    @GET("/feeds/flowers.json")
    Call<List<Flower>> getAllFlowers();

    @GET("/feeds/flowers.json")
    Observable<List<Flower>> getAllFlowersRxJava();
}
