package com.sdacademy.zientara.rafal.flowersstore;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.sdacademy.zientara.rafal.flowersstore.adapters.CoolListAdapter;
import com.sdacademy.zientara.rafal.flowersstore.models.Flower;
import com.sdacademy.zientara.rafal.flowersstore.services.FlowersApi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CoolListAdapter.OnItemClicked {
    public static final String BASE_URL = "http://services.hanselandpetal.com";
    private Retrofit retrofit;
    private FlowersApi flowersApi;
    private List<Flower> flowersList;
    private CoolListAdapter coolListAdapter;

    @BindView(R.id.mainActivity_listView)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        prepareRetrofit();
        prepareAdapter();
        getFlowersRxJava();
    }

    private void prepareAdapter() {
        flowersList = new ArrayList<>();
        coolListAdapter = new CoolListAdapter(this, flowersList, this);
        listView.setAdapter(coolListAdapter);
    }

    private void prepareRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//dopiero teraz mozemy zwracac Observable
                .build();

        flowersApi = retrofit.create(FlowersApi.class);
    }

    private void getFlowersRxJava() {
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
                        coolListAdapter.notifyDataSetChanged();
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

    @Override
    public void onRowClicked(Flower flower) {
        showToast("Kliknales " + flower.getName());
    }

    @Override
    public void onPriceClicked(Flower flower) {
        showToast(flower.getName() + " jest za drogi!");
    }
}
