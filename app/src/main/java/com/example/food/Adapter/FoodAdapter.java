package com.example.food.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.food.ListCartActivity;
import com.example.food.ListFoodActivity;
import com.example.food.Model.CartModel;
import com.example.food.Model.FoodModel;
import com.example.food.Model.RestaurantModel;
import com.example.food.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.example.food.R.*;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder> {
    private Context context;
    private List<FoodModel> modelList;
    private RestaurantModel restaurant;
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

    public FoodAdapter(Context context, List<FoodModel> modelList, RestaurantModel restaurant) {
        this.context = context;
        this.modelList = modelList;
        this.restaurant = restaurant;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.activity_item_rows_food, parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final FoodModel food = modelList.get(position);
        holder.txtFoodName.setText(modelList.get(position).getName());

        //String price = Integer.toString(modelList.get(position).getPrice());
        holder.txtPrice.setText(decimalFormat.format(food.getPrice()));
        String url = modelList.get(position).getImg();
        Picasso.get().load(url).centerCrop().fit().into(holder.imgFood);
        holder.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(context, modelList.get(position).getName(),Toast.LENGTH_SHORT).show();
                Them(food.getIdFood(), food.getName(), restaurant.getId(),
                food.getPrice(), 1, food.getImg());
                ListFoodActivity.mCount.setNumber(ListFoodActivity.Count(restaurant.getId()));
                Toast.makeText(context,"Đã thêm thành công ",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public List<CartModel> Them(String idFood, String foodName, String idRes, int price, int count
            , String img){
        List<CartModel> dsCart = ListFoodActivity.cartList;
        if(TimidFood(idRes,idFood)==1){
            for (CartModel cart: dsCart){
                if(cart.getIdFood().equals(idFood)){
                    cart.setCount(cart.getCount()+1);
                    cart.setTotal(cart.getCount(),cart.getPrice());
                }
            }
        }
        else
            dsCart.add(new CartModel(idFood, foodName, idRes, price, 1,img));

        return dsCart;
    }
    public int TimidFood(String idRes, String idFood){
        for(CartModel cart: ListFoodActivity.cartList){
            if(cart.getIdFood().equals(idFood) && cart.getIdRes().equals(idRes) ){
                return 1;
            }
        }
        return 0;
    }
    public void UpdateList(List<FoodModel> food){
        modelList = new ArrayList<>();
        modelList.addAll(food);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return modelList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imgFood;
        ImageButton btnOrder;
        TextView txtFoodName, txtPrice;
        public MyViewHolder(View itemView){
            super(itemView);
            //unbinder = ButterKnife.bind(this,itemView);
            imgFood = itemView.findViewById(R.id.imgFood);
            txtFoodName = itemView.findViewById(R.id.txtFoodName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            btnOrder = itemView.findViewById(R.id.btnOder);

        }

    }


}
