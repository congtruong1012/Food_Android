package com.example.food.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.ListCartActivity;
import com.example.food.Model.OderHistoryModel;
import com.example.food.R;
import com.example.food.ui.slideshow.SlideshowFragment;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.MyViewHolder> {
    private Context context;
    private List<OderHistoryModel> list;
    SlideshowFragment slideshowFragment;
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");

    public OrderHistoryAdapter(Context context, List<OderHistoryModel> list, SlideshowFragment slideshowFragment) {
        this.context = context;
        this.list = list;
        this.slideshowFragment = slideshowFragment;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_rows_order_history, null);
        return new MyViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            OderHistoryModel oderHistory = list.get(position);
            holder.id.setText("#".concat(Integer.toString(oderHistory.getId())));
            holder.restaurant.setText(oderHistory.getRestaurant());
            holder.place.setText(oderHistory.getPlace());

            holder.total.setText(decimalFormat.format(oderHistory.getTotal()).concat(" đ"));
            holder.date.setText(date.format(oderHistory.getDate()));
    }
    public void UpdateList(List<OderHistoryModel> oderHistory){
        list = new ArrayList<>();
        list.addAll(oderHistory);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView id, restaurant, place, total, date;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.BillNumber);
            restaurant = itemView.findViewById(R.id.restaurant);
            place = itemView.findViewById(R.id.place);
            total = itemView.findViewById(R.id.total);
            date = itemView.findViewById(R.id.date);
        }
    }
}
