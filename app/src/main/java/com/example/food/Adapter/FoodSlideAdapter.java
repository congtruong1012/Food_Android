package com.example.food.Adapter;

import android.transition.Slide;

import com.example.food.Model.FoodModel;

import java.util.List;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class FoodSlideAdapter extends SliderAdapter {
    List<FoodModel> food;
    public FoodSlideAdapter(List<FoodModel> food) {
        this.food=food;
    }

    @Override
    public int getItemCount() {
        return food.size();
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder imageSlideViewHolder) {
        //FoodModel img = food.get(position);
        imageSlideViewHolder.bindImageSlide(food.get(position).getImg());
    }
}
