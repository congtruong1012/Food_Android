package com.example.food.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.food.ListFoodActivity;
import com.example.food.R;
import com.example.food.ui.*;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.Model.RestaurantModel;
import com.example.food.ui.home.HomeFragment;
import com.example.food.ui.tools.ToolsFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ResViewHolder> {
    private  List<RestaurantModel> modelList;
    private  Context mContext;
    HomeFragment homeFragment;

    public RestaurantAdapter(Context mContext, List<RestaurantModel> recyclerViewItems, HomeFragment homeFragment) {
        this.mContext = mContext;
        this.modelList= recyclerViewItems;
        this.homeFragment = homeFragment;
    }



    @Override

    public ResViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_rows_restaurant, null);
        return new ResViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(final ResViewHolder holder, final int position) {
        final RestaurantModel restaurant = modelList.get(position);
        holder.txtRes.setText(modelList.get(position).getName());
        holder.txtAddRes.setText(modelList.get(position).getAddress());
        holder.txtPhoneRes.setText(modelList.get(position).getPhone());
        String url = modelList.get(position).getImage();
        Picasso.get().load(url).centerCrop().fit().into(holder.imgRes);
        holder.liRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, modelList.get(position).getName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, ListFoodActivity.class);
                intent.putExtra("dataRestaurant", restaurant);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
    public void UpdateList(List<RestaurantModel> restaurantModel){
        modelList = new ArrayList<>();
        modelList.addAll(restaurantModel);
        notifyDataSetChanged();
    }

    public class ResViewHolder extends RecyclerView.ViewHolder{
        TextView txtRes, txtAddRes, txtPhoneRes;
        ImageView imgRes;
        LinearLayout liRes;

        public ResViewHolder( View itemView) {
            super(itemView);
            txtRes= itemView.findViewById(R.id.txtRes);
            txtAddRes = itemView.findViewById(R.id.txtAddrRes);
            txtPhoneRes = itemView.findViewById(R.id.nbRes);
            imgRes = itemView.findViewById(R.id.imgRes);
            liRes = itemView.findViewById(R.id.btnRes);
        }
    }
}
