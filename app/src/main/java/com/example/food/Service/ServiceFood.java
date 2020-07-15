package com.example.food.Service;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import ss.com.bannerslider.ImageLoadingService;

public class ServiceFood implements ImageLoadingService {
    @Override
    public void loadImage(String url, ImageView imageView) {
        Picasso.get().load(url).into(imageView);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    @Override
    public void loadImage(int resource, ImageView imageView) {
        Picasso.get().load(resource).into(imageView);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    @Override
    public void loadImage(String url, int placeHolder, int errorDrawable, ImageView imageView) {
        Picasso.get().load(url).error(errorDrawable).placeholder(placeHolder).into(imageView);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
    }
}
