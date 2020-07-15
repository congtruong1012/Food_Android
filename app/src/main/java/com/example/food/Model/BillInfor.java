package com.example.food.Model;

public class BillInfor {
    private String idHD;
    private String idFood;
    private int count;
    private int price;

    public BillInfor() {
    }

    public BillInfor(String idHD, String idFood, int count, int price) {
        this.idHD = idHD;
        this.idFood = idFood;
        this.count = count;
        this.price = price;
    }

    public String getIdHD() {
        return idHD;
    }

    public void setIdHD(String idHD) {
        this.idHD = idHD;
    }

    public String getIdFood() {
        return idFood;
    }

    public void setIdFood(String idFood) {
        this.idFood = idFood;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
