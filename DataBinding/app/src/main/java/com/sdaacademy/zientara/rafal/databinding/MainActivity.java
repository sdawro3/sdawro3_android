package com.sdaacademy.zientara.rafal.databinding;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sdaacademy.zientara.rafal.databinding.databinding.ActivityMainBinding;
import com.sdaacademy.zientara.rafal.databinding.models.Beer;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setStrongBeerText("To jest mocne piwo!");
        binding.setWeakBeerText("Slabe piwo :(");
        binding.setBeer(getFakeBeer());

        View view = new View(this);
        view.setVisibility(View.GONE);
    }

    private Beer getFakeBeer() {
        Beer beer = new Beer();
        beer.setName("Slaskie mocne");
        beer.setPower(12f);
        beer.setPrice(2.49f);
        return beer;
    }

    public void showTyskie(View view) {
        binding.setBeer(getTyskie());
    }

    public void showKasztelan(View view) {
        binding.setBeer(getKasztelan());
    }

    public void moreSpiritus(View view){
        binding.getBeer().setPower(15);
    }

    public void lowePrice(View view){
        binding.getBeer().setPrice(0.01f);
    }

    private Beer getKasztelan() {
        return new Beer("Kasztelan", 4.5F, 2.99F);
    }

    private Beer getTyskie() {
        return new Beer("Tyskie", 5F, 3.29F);
    }
}
