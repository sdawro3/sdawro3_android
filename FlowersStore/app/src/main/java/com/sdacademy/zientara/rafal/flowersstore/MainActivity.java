package com.sdacademy.zientara.rafal.flowersstore;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.sdacademy.zientara.rafal.flowersstore.models.Flower;
import com.sdacademy.zientara.rafal.flowersstore.services.FlowersApi;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String BASE_URL = "http://services.hanselandpetal.com";
    private Retrofit retrofit;
    private FlowersApi flowersApi;
    private List<Flower> flowersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flowersList = new ArrayList<>();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//dopiero teraz mozemy zwracac Observable
                .build();

        flowersApi = retrofit.create(FlowersApi.class);



        /*flowersApi.getAllFlowers().enqueue(new Callback<List<Flower>>() {
            @Override
            public void onResponse(Call<List<Flower>> call, Response<List<Flower>> response) {
                showToast("response");
            }

            @Override
            public void onFailure(Call<List<Flower>> call, Throwable t) {
                showToast("fail");
            }
        });*/

        flowersApi.getAllFlowersRxJava()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Flower>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull List<Flower> flowers) {
//                        MainActivity.this.flowersList = flowers;
                        flowersList.addAll(flowers);
                        Log.d("RxJava", "New flowers ! " + flowers.size());
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d("RxJava", "DONE!");
                        showToast("DONE!");
                    }
                });
    }

    @NonNull
    private Gson getGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .setLenient()
                .create();
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
