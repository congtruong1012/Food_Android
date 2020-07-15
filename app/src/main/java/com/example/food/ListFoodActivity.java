package com.example.food;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.food.Adapter.FoodAdapter;
import com.example.food.Adapter.FoodSlideAdapter;
import com.example.food.Adapter.RestaurantAdapter;
import com.example.food.Model.CartModel;
import com.example.food.Model.FoodModel;
import com.example.food.Model.RestaurantModel;
import com.example.food.Service.ServiceFood;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nex3z.notificationbadge.NotificationBadge;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ss.com.bannerslider.Slider;

public class ListFoodActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private final String URL = Server.food;
    private RequestQueue queue;
    private FoodAdapter mAdapter;
    private List<FoodModel> modelList;
    private RestaurantModel restaurant;
    private RecyclerView recyclerView;
    private Toolbar tolFood;
    private FloatingActionButton toolbarCart;
    private Slider banner_slider;
    public static ArrayList<CartModel> cartList;
    public static NotificationBadge mCount;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_food);
        Intent intent = getIntent();
        restaurant = (RestaurantModel) intent.getSerializableExtra("dataRestaurant");
        tolFood = findViewById(R.id.toolbarFood);
        tolFood.setTitle(restaurant.getName());
        mCount = findViewById(R.id.badge);
        mCount.setNumber(Count(restaurant.getId()));
        setSupportActionBar(tolFood);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbarCart = findViewById(R.id.btnCart);
        toolbarCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ListCartActivity.class);
                intent.putExtra("restaurant",restaurant);
                startActivity(intent);
            }
        });
        if(cartList != null){

        }
        else { cartList = new ArrayList<>();}
        banner_slider = findViewById(R.id.banner_food);
        Slider.init(new ServiceFood());
        //Toast.makeText(this,restaurant.getAddress(),Toast.LENGTH_SHORT).show();
        modelList = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_Food);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new FoodAdapter(this,modelList,restaurant);
        queue = Volley.newRequestQueue(this);
        httpPOST(URL);

        //else
    }
    public static int Count(String key){
        int i=0;
        if (cartList == null){
            return 0;
        }
        else {
            for(CartModel cart: ListFoodActivity.cartList){
                if(key.equals(cart.getIdRes()))
                    i++;
            }
            return i;
        }
    }

    private void httpPOST(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("0")){
                    Toast.makeText(getApplicationContext(),"Lỗi"+response,Toast.LENGTH_SHORT);
                    finish();
                }
                else{
                    displayBanner(modelList);
                    parsingData(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String responseBody = new String(error.networkResponse.data,"utf-8");
                    Toast.makeText(getApplicationContext(),"Error"+responseBody, Toast.LENGTH_SHORT).show();
                }catch (UnsupportedEncodingException errorr){
                    //Log.d(Tag,errorr.toString());
                    Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("name", restaurant.getId());
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void parsingData(String response) {
        try {
            JSONObject obj = new JSONObject(response);
            JSONArray m_jAray = obj.getJSONArray("Food");
            for(int i = 0 ; i<m_jAray.length();i++){
                JSONObject jo_inside = m_jAray.getJSONObject(i);
                String idFood = jo_inside.getString("idFood");
                String name = jo_inside.getString("name");
                int price = jo_inside.getInt("price");
                String img = jo_inside.getString("img");

               FoodModel food = new FoodModel(idFood,name,price,img);
               modelList.add(food);
            }
            recyclerView.setAdapter(mAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void displayBanner(List<FoodModel> food){
        banner_slider.setAdapter(new FoodSlideAdapter(food));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.food_item,menu);
        MenuItem menuItem = menu.findItem(R.id.search_food);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);

        return  true;
    }



    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        String foodname = s.trim().toLowerCase();
        List<FoodModel> foods = new ArrayList<>();
        for(FoodModel food : modelList){
            if (food.getName().trim().toLowerCase().contains(foodname)){
                foods.add(food);
            }
        }
        mAdapter.UpdateList(foods);
        return true;
    }


}
