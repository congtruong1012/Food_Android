package com.example.food.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.ItemLongClickListener;
import com.example.food.ListCartActivity;
import com.example.food.ListFoodActivity;
import com.example.food.Model.CartModel;
import com.example.food.Model.RestaurantModel;
import com.example.food.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    private Context context;
    private List<CartModel> cartlList;
    private RestaurantModel restaurantModel;
    private DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

    public CartAdapter(Context context, List<CartModel> cartlList, RestaurantModel restaurant) {
        this.context = context;
        this.cartlList = cartlList;
        this.restaurantModel = restaurant;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.activity_item_rows_cart, parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final CartModel cart = cartlList.get(position);
        holder.txtnameFood.setText(cart.getFoodName());
        //String gia = Integer.toString(cart.getPrice());
        holder.txtGia.setText(decimalFormat.format(cart.getPrice())+" đ");
        holder.txtCount.setText(Integer.toString(cart.getCount()));
        //final String tong = Integer.toString(cart.getTotal());
        holder.txtTong.setText(decimalFormat.format(cart.getTotal())+" đ");
        String url = cart.getImg();
        Picasso.get().load(url).centerCrop().fit().into(holder.imgFood);
        holder.btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 /*ListCartActivity.ShowByRestaurant(restaurantModel.getId()).get(position).setCount(
                         ListCartActivity.ShowByRestaurant(restaurantModel.getId())
                                 .get(position).getCount() + 1
                );
                ListCartActivity.ShowByRestaurant(restaurantModel.getId())
                        .get(position).setTotal(
                        ListFoodActivity.cartList.get(position).getCount(),
                        ListFoodActivity.cartList.get(position).getPrice()
                );
                notifyDataSetChanged();
                holder.txtCount.setText(ListCartActivity.ShowByRestaurant(restaurantModel.getId())
                        .get(position).getCount()+ " ");
                holder.txtTong.setText(decimalFormat.format(ListCartActivity.
                        ShowByRestaurant(restaurantModel.getId()).get(position).getTotal())+ " đ");
                ListCartActivity.txtTotal.setText(decimalFormat.format(Tong())+" đ");
                //Toast.makeText(context,"+1",Toast.LENGTH_SHORT).show();*/
            /* CartModel cartModel = ListCartActivity.ShowByRestaurant(restaurantModel.getId()).get(position);
             cartModel.setCount(cartModel.getCount()+1);*/
            for( int j = 0 ; j< ListFoodActivity.cartList.size();j++){
                CartModel Cart = ListFoodActivity.cartList.get(j);
                if(Cart.getIdRes().equals(cart.getIdRes()) && Cart.getIdFood().equals(cart.getIdFood())) {
                    Cart.setCount(Cart.getCount() + 1);
                    Cart.setTotal(Cart.getCount(),Cart.getPrice());
                }
            }
             holder.txtCount.setText(cart.getCount()+"");
            holder.txtTong.setText(decimalFormat.format(cart.getTotal())+"đ");
            ListCartActivity.txtTotal.setText(decimalFormat.format(Tong())+"đ");
            }
        });
        holder.btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*ListCartActivity.ShowByRestaurant(restaurantModel.getId()).get(position).setCount(
                        ListFoodActivity.cartList.get(position).getCount() - 1 );
                notifyDataSetChanged();
                if(ListCartActivity.ShowByRestaurant(restaurantModel.getId())
                        .get(position).getCount()<=0){
                    ListCartActivity.ShowByRestaurant(restaurantModel.getId())
                            .get(position).setCount(0);
                    notifyDataSetChanged();
                }
                ListCartActivity.ShowByRestaurant(restaurantModel.getId()).get(position).setTotal(
                        ListCartActivity.ShowByRestaurant(restaurantModel.getId()).get(position).getCount(),
                        ListCartActivity.ShowByRestaurant(restaurantModel.getId()).get(position).getPrice()

                );
                notifyDataSetChanged();
                holder.txtCount.setText(ListCartActivity.ShowByRestaurant(restaurantModel.getId())
                        .get(position).getCount()+ " ");
                holder.txtTong.setText(decimalFormat.format(ListCartActivity
                        .ShowByRestaurant(restaurantModel.getId()).get(position).getTotal())+ " đ");
                ListCartActivity.txtTotal.setText(decimalFormat.format(Tong())+" đ");*/
                for( int j = 0 ; j< ListFoodActivity.cartList.size();j++){
                    CartModel Cart = ListFoodActivity.cartList.get(j);
                    if(Cart.getIdRes().equals(cart.getIdRes()) && Cart.getIdFood().equals(cart.getIdFood())) {
                        Cart.setCount(Cart.getCount() - 1);
                        if(Cart.getCount()<=0) {
                            Cart.setCount(0);
                        }
                        Cart.setTotal(Cart.getCount(),Cart.getPrice());
                    }
                }
                holder.txtCount.setText(cart.getCount()+"");
                holder.txtTong.setText(decimalFormat.format(cart.getTotal())+"đ");
                ListCartActivity.txtTotal.setText(decimalFormat.format(Tong())+"đ");
            }

        });
        holder.setItemLongClickListener(new ItemLongClickListener() {
            @Override
            public void onItemLongClick(View v, final int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa giỏ hàng");
                builder.setMessage("Bạn chắc chắn muốn xóa món này?");
                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        notifyDataSetChanged();
                        Tong();
                        ListCartActivity.txtTotal.setText(Tong()+" đ");
                    }
                });
                builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ListCartActivity.ShowByRestaurant(restaurantModel.getId()).remove(position);

                        //ListFoodActivity.cartList.remove(position);
                        for( int j = 0 ; j< ListFoodActivity.cartList.size();j++){
                            CartModel cartModel = ListFoodActivity.cartList.get(j);
                            if(cartModel.getIdRes().equals(cart.getIdRes()) && cartModel.getIdFood().equals(cart.getIdFood()))
                                ListFoodActivity.cartList.remove(j);
                        }
                        //Tong();
                        ListFoodActivity.mCount.setNumber(ListFoodActivity.Count(restaurantModel.getId()));
                        ListCartActivity.txtTotal.setText(decimalFormat.format(Tong())+" đ");
                        UpdateList(ListCartActivity.ShowByRestaurant(restaurantModel.getId()));

                        notifyDataSetChanged();
                        if(ListCartActivity.ShowByRestaurant(restaurantModel.getId()).size() <=0){
                            ListCartActivity.txtThongBao.setVisibility(View.VISIBLE);
                            ListFoodActivity.mCount.setNumber(0);
                        }else {
                            ListCartActivity.txtThongBao.setVisibility(View.INVISIBLE);

                            //Tong();
                            ListFoodActivity.mCount.setNumber(ListFoodActivity.Count(restaurantModel.getId()));
                            ListCartActivity.txtTotal.setText(decimalFormat.format(Tong())+" đ");
                            UpdateList(ListCartActivity.ShowByRestaurant(restaurantModel.getId()));
                            notifyDataSetChanged();
                        }

                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartlList.size();
    }


    public int Tong(){
        int s=0;
        for(CartModel cart : ListCartActivity.ShowByRestaurant(restaurantModel.getId())){
            s+=cart.getTotal();
        }
        return s;
    }
    public void UpdateList(List<CartModel> cart){
        cartlList = new ArrayList<>();
        cartlList.addAll(cart);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{
        ImageView imgFood;
        ImageButton btnCong, btnTru;
        TextView txtnameFood, txtCount, txtGia, txtTong;
        ItemLongClickListener itemLongClickListener;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.imgFood);
            btnCong = itemView.findViewById(R.id.btnCong);
            btnTru = itemView.findViewById(R.id.btnTrư);
            txtnameFood = itemView.findViewById(R.id.txtFoodName);
            txtCount = itemView.findViewById(R.id.txtSL);
            txtGia = itemView.findViewById(R.id.txtGia);
            txtTong = itemView.findViewById(R.id.txtTong);
            itemView.setOnLongClickListener(this);
        }
        public void setItemLongClickListener(ItemLongClickListener ic){
            this.itemLongClickListener=ic;
        }

        @Override
        public boolean onLongClick(View view) {
            this.itemLongClickListener.onItemLongClick(view,getLayoutPosition());
            return false;
        }
    }
}
