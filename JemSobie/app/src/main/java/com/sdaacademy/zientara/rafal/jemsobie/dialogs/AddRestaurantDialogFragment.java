package com.sdaacademy.zientara.rafal.jemsobie.dialogs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.sdaacademy.zientara.rafal.jemsobie.MainActivity;
import com.sdaacademy.zientara.rafal.jemsobie.R;
import com.sdaacademy.zientara.rafal.jemsobie.models.Restaurant;
import com.sdaacademy.zientara.rafal.jemsobie.retrofit.BaseRetrofit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Evil on 10.07.2017.
 */

public class AddRestaurantDialogFragment extends DialogFragment {

    @BindView(R.id.addRestaurant_comment)
    EditText commentEditText;

    @BindView(R.id.addRestaurant_name)
    EditText nameEditText;

    @BindView(R.id.addRestaurant_rating)
    EditText ratingEditText;

    @BindView(R.id.addRestaurant_addButton)
    Button addButton;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_restaurant, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.addRestaurant_addButton)
    public void addRestaurant() {
        Restaurant restaurant = getRestaurant();

        Call<Restaurant> restaurantCall = new BaseRetrofit().getRestaurantsApi().postRestaurants(restaurant);
        restaurantCall.enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(getContext(), "Wyslane! :D", Toast.LENGTH_SHORT).show();
                    dismiss();
                    //clickRefreshList();
                } else {

                    Toast.makeText(getContext(), "Nie wyslalem :(\n" + response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {
                Toast.makeText(getContext(), "Nie wyslalem\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @NonNull
    private Restaurant getRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(nameEditText.getText().toString().trim());
        restaurant.setComment(commentEditText.getText().toString().trim());
        restaurant.setRating(Float.parseFloat(ratingEditText.getText().toString()));
        return restaurant;
    }
}
