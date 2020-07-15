package com.example.food.Model;

import java.util.Date;

public class OderHistoryModel {
    private int id, total;
    private String restaurant, place;
    private Date date;

    public OderHistoryModel(int id, int total, String restaurant, String place, Date date) {
        this.id = id;
        this.total = total;
        this.restaurant = restaurant;
        this.place = place;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
