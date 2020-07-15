package com.example.food.ui.home;


import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.food.Adapter.RestaurantAdapter;
import com.example.food.Model.RestaurantModel;
import com.example.food.R;
import com.example.food.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private final String URL = Server.restaurant;
    private RequestQueue queue;
    private RestaurantAdapter mAdapter;
    public static List<RestaurantModel> modelList ;
    private RecyclerView recyclerView;
    private EditText timkiem;




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       View root = inflater.inflate(R.layout.fragment_home, container, false);
        modelList = new ArrayList<>();

        recyclerView = (RecyclerView) root.findViewById(R.id.rv_Res);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new RestaurantAdapter(getContext(), modelList,this);
        queue = Volley.newRequestQueue(getContext());
        httpGet(URL);
        timkiem = root.findViewById(R.id.timkiem);
        timkiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                ArrayList<RestaurantModel> list = new ArrayList<>();
                for(RestaurantModel restaurant : modelList){
                    if(restaurant.getName().trim().toLowerCase().contains(editable.toString().trim().toLowerCase())){
                        list.add(restaurant);
                    }
                }
                mAdapter.UpdateList(list);
            }
        });
        return root;
    }


    public void httpGet(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parsingData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String responseBody = new String(error.networkResponse.data,"utf-8");
                    Toast.makeText(getContext(),"Error"+responseBody, Toast.LENGTH_SHORT).show();
                }catch (UnsupportedEncodingException errorr){
                    Toast.makeText(getContext(),error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        queue.add(stringRequest);
    }
    public  void parsingData(String response){
        try {
            JSONObject obj = new JSONObject(response);
            JSONArray m_jAray = obj.getJSONArray("Restaurant");
            for(int i = 0 ; i<m_jAray.length();i++){
                JSONObject jo_inside = m_jAray.getJSONObject(i);
                String id = jo_inside.getString("id");
                String name = jo_inside.getString("name");
                String address = jo_inside.getString("address");
                String phone = jo_inside.getString("phone");
                String anh = jo_inside.getString("anh");

                RestaurantModel restaurantModel = new RestaurantModel(id,name,address,phone,anh) ;
                modelList.add(restaurantModel);
            }
            recyclerView.setAdapter(mAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}