package com.sdacademy.zientara.rafal.flowersstore.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Evil on 12.07.2017.
 */

public class Flower {
    private String category;
    private Double price;
    private String instructions;
    private String photo;
    private String name;
    @SerializedName("productId")
    private Long id;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
