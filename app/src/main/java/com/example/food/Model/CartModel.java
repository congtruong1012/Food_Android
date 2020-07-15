package com.example.food.Model;

import java.io.Serializable;

public class CartModel implements Serializable {
    private String idFood;
    private String foodName;
    private String img;
    private String idRes;
    private int price;
    private int count;
    private int Total;

    public CartModel(String idFood, String foodName, String idRes, int price, int count
    , String img) {
        this.idFood = idFood;
        this.foodName = foodName;
        this.idRes = idRes;
        this.price = price;
        this.count = count;
        this.Total = count*price;
        this.img = img;
    }

    public CartModel() {
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

    public String getIdRes() {
        return idRes;
    }

    public void setIdRes(String idRes) {
        this.idRes = idRes;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int count, int price) {
        Total = count*price;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
}
