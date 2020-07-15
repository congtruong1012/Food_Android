package com.example.food.Model;

import java.io.Serializable;

public class FoodModel implements Serializable {
    private String idFood;
    private String name;
    private int price;
    private String img;

    public FoodModel() {
    }

    public FoodModel(String idFood, String name, int price, String img) {
        this.idFood= idFood;
        this.name = name;
        this.price = price;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIdFood() {
        return idFood;
    }

    public void setIdFood(String idFood) {
        this.idFood = idFood;
    }
}
