package com.sdaacademy.zientara.rafal.databinding.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.sdaacademy.zientara.rafal.databinding.BR;

/**
 * Created by Evil on 19.06.2017.
 */

public class Beer extends BaseObservable {

    private String name;
    private float power;
    private float price;

    public Beer() {
    }

    public Beer(String name, float power, float price) {
        this.name = name;
        this.power = power;
        this.price = price;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
        notifyPropertyChanged(BR.power);
    }

    @Bindable
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
        notifyPropertyChanged(BR.price);
    }

    public boolean isStrongBeer() {
        return power > 9;
    }
}
