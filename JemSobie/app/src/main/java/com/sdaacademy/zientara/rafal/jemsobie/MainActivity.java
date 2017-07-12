package com.sdaacademy.zientara.rafal.jemsobie;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.sdaacademy.zientara.rafal.jemsobie.adapter.RecyclerViewAdapter;
import com.sdaacademy.zientara.rafal.jemsobie.adapter.RestaurantsHolderArrayAdapter;
import com.sdaacademy.zientara.rafal.jemsobie.dialogs.AddEditRestaurantDialogFragment;
import com.sdaacademy.zientara.rafal.jemsobie.dialogs.DeleteRestaurantDialogFragment;
import com.sdaacademy.zientara.rafal.jemsobie.interfaces.ForceRefreshList;
import com.sdaacademy.zientara.rafal.jemsobie.models.Restaurant;
import com.sdaacademy.zientara.rafal.jemsobie.retrofit.BaseRetrofit;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ForceRefreshList {

    @BindView(R.id.main_refreshButton)
    Button refreshButton;

    @BindView(R.id.main_addButton)
    Button addButton;

    @BindView(R.id.main_removeFirstButton)
    Button removeFirstButton;

    @BindView(R.id.main_listView)
    ListView listView;

    @BindView(R.id.main_recyclerView)
    RecyclerView recyclerView;

    private List<Restaurant> restaurantList;
    private ProgressDialog progressDialog;
    private ArrayAdapter adapter;
    private RecyclerViewAdapter recyclerViewAdapter;
    private BaseRetrofit baseRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        prepareRestApi();
        prepareListAdapter();
        downloadRestaurants();
    }

    private void downloadRestaurants() {
        showProgressDialog();
        clearList();
        updateListWithoutRxJava();
//        updateListRxJava();
    }

    private void prepareRestApi() {
        baseRetrofit = new BaseRetrofit();
    }

    private void updateListWithoutRxJava() {
        Call<List<Restaurant>> allPeople = baseRetrofit.getRestaurantsApi().getAllRestaurants();
        allPeople.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                if (response.isSuccessful()) {
                    restaurantList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Restaurant count : " + restaurantList.size(), Toast.LENGTH_SHORT).show();
                }
                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Zjebalo sie\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
                hideProgressDialog();
            }
        });
    }

    @OnClick(R.id.main_removeFirstButton)
    public void removeFirstElementFromRecyclerView() {
        if (restaurantList.size() > 2) {
            int position = 1;
            restaurantList.remove(position);
            recyclerViewAdapter.notifyItemRemoved(position);
            adapter.notifyDataSetChanged();
        }
    }

    private void clearList() {
        restaurantList.clear();
        adapter.notifyDataSetChanged();
        recyclerViewAdapter.notifyDataSetChanged();
    }

    private void hideProgressDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Getting informations...");
        progressDialog.show();
    }

    private void prepareListAdapter() {
        restaurantList = new ArrayList<>();
//        ListAdapter adapter = new RestaurantsAdapterNope(getApplicationContext(), restaurantList);
//        ListAdapter adapter = new RestaurantsArrayAdapter(getApplicationContext(), restaurantList);
        adapter = new RestaurantsHolderArrayAdapter(getApplicationContext(), restaurantList);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("CLICK", "id:" + i);
                final Restaurant restaurant = restaurantList.get(i);
                showDeleteOrEditDialog(restaurant);
                return true;
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(), restaurantList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void showDeleteOrEditDialog(final Restaurant restaurant) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                .setMessage("What do U want to do?")
                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showDeleteRestaurantDialog(restaurant);
                        dialog.dismiss();
                    }
                })
                .setNeutralButton("Cancel", null)
                .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showEditRestaurantDialog(restaurant);
                        dialog.dismiss();
                    }
                });
        dialog.show();
    }

    private void showEditRestaurantDialog(Restaurant restaurant) {
        AddEditRestaurantDialogFragment dialogFragment = AddEditRestaurantDialogFragment.newInstance(restaurant);
        dialogFragment.show(getSupportFragmentManager(), null);
    }

    private void showDeleteRestaurantDialog(Restaurant restaurant) {
        DeleteRestaurantDialogFragment dialogFragment = DeleteRestaurantDialogFragment.newInstance(restaurant);
        dialogFragment.show(getSupportFragmentManager(), null);
    }

    @OnClick(R.id.main_addButton)
    public void clickShowAddRestaurantDialog() {
        AddEditRestaurantDialogFragment addRestaurantDialogFragment = new AddEditRestaurantDialogFragment();
        addRestaurantDialogFragment.show(getSupportFragmentManager(), null);
    }

    @OnClick(R.id.main_refreshButton)
    public void clickRefreshList() {
        downloadRestaurants();
    }

    @Override
    public void refreshList() {
        clickRefreshList();
    }
}
