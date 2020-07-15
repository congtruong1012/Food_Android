package com.example.food;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.food.Adapter.CartAdapter;
import com.example.food.Model.CartModel;
import com.example.food.Model.FoodModel;
import com.example.food.Model.RestaurantModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListCartActivity extends AppCompatActivity {
    private JsonArrayRequest request;
    private RequestQueue queue;
    public static CartAdapter adapter;
    private RestaurantModel restaurant;
    private FoodModel food;
    public static RecyclerView recyclerView;
    private Toolbar toolbarCart;
    public static TextView txtTotal, txtThongBao;
    private Button btnBuy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cart);
        toolbarCart = findViewById(R.id.toolCart);
        Intent intent = getIntent();
        restaurant = (RestaurantModel)   intent.getSerializableExtra("restaurant");
        //food = (FoodModel) intent.getSerializableExtra("dataFood");
        recyclerView = findViewById(R.id.rv_Cart);
        btnBuy = findViewById(R.id.btnBuy);
        txtTotal = findViewById(R.id.txtTotal);
        txtThongBao = findViewById(R.id.txtThongBao);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CartAdapter(this, ShowByRestaurant(restaurant.getId()), restaurant);
        recyclerView.setAdapter(adapter);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        //String totalPrice = Integer.toString(adapter.Tong());
        txtTotal.setText(decimalFormat.format(adapter.Tong())+" đ");
//        adapter = new CartAdapter(this,ListFoodActivity.cartList,restaurant);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        RecyclerView.LayoutManager rvLiLayoutManager = layoutManager;
//        recyclerView.setLayoutManager(rvLiLayoutManager);
//        recyclerView.setAdapter(adapter);
        setSupportActionBar(toolbarCart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getApplicationContext(),restaurant.getName(),Toast.LENGTH_SHORT).show();
                if(ListCartActivity.ShowByRestaurant(restaurant.getId()).size()<=0){
                    Toast.makeText(getApplicationContext(),"Vui lòng mua hàng để thanh toán"
                    ,Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), PlaceOrder.class);
                    intent.putExtra("TotalPrice", adapter.Tong() );
                    intent.putExtra("nhahang",restaurant);
                    startActivity(intent);
                }

            }
        });
        CheckListCart();
    }

    private void CheckListCart() {
        if(ListCartActivity.ShowByRestaurant(restaurant.getId()).size() <= 0){
            txtThongBao.setVisibility(View.VISIBLE);
        }
        else {
            txtThongBao.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    public static List<CartModel> ShowByRestaurant(String key){
        List<CartModel> cartByRes = new ArrayList<>();
        for(CartModel cart : ListFoodActivity.cartList){
            if(key.equals(cart.getIdRes())){
                cartByRes.add(cart);
            }
        }
        return cartByRes;
    }
    public static List<CartModel> DeleteByRestaurant(String key){
        List<CartModel> ds = new ArrayList<>();
        for(CartModel cart : ListFoodActivity.cartList){
            if(!key.equals(cart.getIdRes())){
                ds.add(cart);
            }
        }
        return ds;
    }

}
