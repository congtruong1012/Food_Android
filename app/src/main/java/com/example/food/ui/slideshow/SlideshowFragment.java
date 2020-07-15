package com.example.food.ui.slideshow;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.food.Adapter.OrderHistoryAdapter;
import com.example.food.MenuItem;
import com.example.food.Model.OderHistoryModel;
import com.example.food.R;
import com.example.food.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    public static OrderHistoryAdapter adapter;
    private RecyclerView recyclerView;
    public static List<OderHistoryModel> list;
    public static TextView thongbao;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        list = new ArrayList<>();
        //thongbao = root.findViewById(R.id.thongbao);
                    //lấy đụa chỉ id recycleview của ỏderhistory
        recyclerView = root.findViewById(R.id.rv_Order_History);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new OrderHistoryAdapter(getContext(),list,this);
        Show();
        return root;
    }
        // hiển thị danh sách lịch sử đặt hàng
    private void Show() {
        //chuỗi này chạy đến trang orderhistory trên php
        StringRequest request = new StringRequest(Request.Method.POST, Server.orderHistory,
                new Response.Listener<String>() {
                    @Override
                    //trả về 1 json trên php
                    public void onResponse(String response) {
                            parsingData(response);
                            recyclerView.setVisibility(View.VISIBLE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("user", MenuItem.listUser.get(0).getUser());
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);
    }
    public void parsingData(String response){
        try {
            JSONObject obj = new JSONObject(response);
            JSONArray m_jAray = obj.getJSONArray("OrderHistory");
            for(int i = 0 ; i<m_jAray.length();i++){
                JSONObject jo_inside = m_jAray.getJSONObject(i);
                int id = jo_inside.getInt("id");
                String address = jo_inside.getString("address");
                String name = jo_inside.getString("name");
                int Total = jo_inside.getInt("Total");
                //Date date=null;
                try {
                    Date date = new SimpleDateFormat("dd-MM-yyyy").parse(
                            jo_inside.getString("date_receive"));
                    OderHistoryModel oderHistory = new OderHistoryModel(id,Total,name,address,date);
                    list.add(oderHistory);
                }catch (Exception e){
                    e.printStackTrace();
                }


            }
            recyclerView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}