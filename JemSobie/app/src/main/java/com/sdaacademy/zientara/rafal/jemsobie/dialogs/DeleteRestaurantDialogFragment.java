package com.sdaacademy.zientara.rafal.jemsobie.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sdaacademy.zientara.rafal.jemsobie.MainActivity;
import com.sdaacademy.zientara.rafal.jemsobie.R;
import com.sdaacademy.zientara.rafal.jemsobie.models.Restaurant;
import com.sdaacademy.zientara.rafal.jemsobie.retrofit.BaseRetrofit;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Evil on 10.07.2017.
 */

public class DeleteRestaurantDialogFragment extends DialogFragment {

    @BindView(R.id.deleteRestaurant_message)
    TextView messageText;

    @BindView(R.id.deleteRestaurant_deleteButton)
    Button deleteButton;

    @BindView(R.id.deleteRestaurant_cancelButton)
    Button cancelButton;

    private Restaurant restaurant;

    public static DeleteRestaurantDialogFragment newInstance(Restaurant restaurant) {
        DeleteRestaurantDialogFragment dialogFragment = new DeleteRestaurantDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("RESTAURANT_KEY", restaurant);
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //// TODO: 11.07.2017 pobierz restauracje
        Bundle arguments = getArguments();
        if (arguments != null)
            restaurant = arguments.getParcelable("RESTAURANT_KEY");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_delete_restaurant, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //// TODO: 11.07.2017 szczegoly restauracji
        messageText.append("\n" + restaurant.getName());
    }

    @OnClick(R.id.deleteRestaurant_deleteButton)
    public void deleteRestaurant() {
        //// TODO: 11.07.2017 id restauracji?
        new BaseRetrofit()
                .getRestaurantsApi()
                .deleteRestaurants(restaurant.getId())
                .enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(getContext(), "Jupi!", Toast.LENGTH_SHORT).show();
                    onDeleteSuccessCallback();
                    dismiss();
                } else {
                    String errorMessage = getErrorString(response);
                    Toast.makeText(getContext(), "Failed! : " + response.errorBody(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {
                Toast.makeText(getContext(), "Failed! : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public interface OnDeleteSuccess {
        void onDeleteSuccess();
    }

    private void onDeleteSuccessCallback() {
        FragmentActivity activity = getActivity();
        if(activity instanceof OnDeleteSuccess && activity instanceof MainActivity) {
            ((OnDeleteSuccess) activity).onDeleteSuccess();
        } else
            Log.w(getClass().getSimpleName(), String.format("Don't forget to implement %s", OnDeleteSuccess.class.getSimpleName()));
    }

    private String getErrorString(Response<Restaurant> response) {
        String errorMessage = null;
        try {
            errorMessage = response.errorBody().string();
        } catch (IOException e) {
            e.printStackTrace();
            errorMessage = e.getMessage();
        }
        return errorMessage;
    }

    @OnClick(R.id.deleteRestaurant_cancelButton)
    public void cancel() {
        dismiss();
    }


}
