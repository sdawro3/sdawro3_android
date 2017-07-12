package com.sdaacademy.zientara.rafal.jemsobie.dialogs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sdaacademy.zientara.rafal.jemsobie.MainActivity;
import com.sdaacademy.zientara.rafal.jemsobie.R;
import com.sdaacademy.zientara.rafal.jemsobie.interfaces.ForceRefreshList;
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

public class AddEditRestaurantDialogFragment extends DialogFragment {

    @BindView(R.id.addRestaurant_comment)
    EditText commentEditText;

    @BindView(R.id.addRestaurant_name)
    EditText nameEditText;

    @BindView(R.id.addRestaurant_rating)
    EditText ratingEditText;

    @BindView(R.id.addRestaurant_addButton)
    Button addButton;

    @BindView(R.id.addRestaurant_editButton)
    Button editButton;

    private Restaurant restaurant;

    public static AddEditRestaurantDialogFragment newInstance(Restaurant restaurant) {
        AddEditRestaurantDialogFragment dialogFragment = new AddEditRestaurantDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("RESTAURANT_KEY", restaurant);
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null)
            restaurant = arguments.getParcelable("RESTAURANT_KEY");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_restaurant, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refreshActionState();
    }

    private void refreshActionState() {
        if (restaurant != null)
            updateView(restaurant);
        else {
            addButton.setVisibility(View.VISIBLE);
            editButton.setVisibility(View.GONE);
        }
    }

    private void updateView(Restaurant restaurant) {
        addButton.setVisibility(View.GONE);
        editButton.setVisibility(View.VISIBLE);
        nameEditText.setText(restaurant.getName());
        commentEditText.setText(restaurant.getComment());
        ratingEditText.setText(restaurant.getRating() + "");
    }

    @OnClick(R.id.addRestaurant_addButton)
    public void addRestaurant() {
        Restaurant restaurant = getRestaurant();

        Call<Restaurant> restaurantCall = new BaseRetrofit().getRestaurantsApi().postRestaurants(restaurant);
        restaurantCall.enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Wyslane! :D", Toast.LENGTH_SHORT).show();
                    dismiss();
                    onEditOrAddSuccessCallback();
                } else
                    Toast.makeText(getContext(), "Nie wyslalem :(\n" + response.errorBody().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {
                Toast.makeText(getContext(), "Nie wyslalem\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.addRestaurant_editButton)
    public void editRestaurant() {
        Restaurant restaurant = getRestaurant();

        Call<Restaurant> restaurantCall = new BaseRetrofit()
                .getRestaurantsApi()
                .putRestaurants(restaurant.getId(), restaurant);
        restaurantCall.enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Zedytowane! :D", Toast.LENGTH_SHORT).show();
                    dismiss();
                    onEditOrAddSuccessCallback();
                } else
                    Toast.makeText(getContext(), "Nie zedytowalem :(\n" + response.errorBody().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {
                Toast.makeText(getContext(), "Nie wyslalem\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onEditOrAddSuccessCallback() {
        FragmentActivity activity = getActivity();
        if (activity instanceof ForceRefreshList && activity instanceof MainActivity)
            ((ForceRefreshList) activity).refreshList();
        else
            Log.w(getClass().getSimpleName(), String.format("Don't forget to implement %s", ForceRefreshList.class.getSimpleName()));
    }

    @NonNull
    private Restaurant getRestaurant() {
        if (restaurant == null)
            restaurant = new Restaurant();
        restaurant.setName(nameEditText.getText().toString().trim());
        restaurant.setComment(commentEditText.getText().toString().trim());
        restaurant.setRating(Float.parseFloat(ratingEditText.getText().toString()));
        return restaurant;
    }
}
