package com.example.food.ui.tools;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.food.MenuItem;
import com.example.food.Model.RestaurantModel;
import com.example.food.R;
import com.example.food.Register;
import com.example.food.Server;

import java.util.HashMap;
import java.util.Map;

public class ToolsFragment extends Fragment {
    private TextView txttk;
    private EditText name, phone;
    private Button btnsave;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tools, container, false);
        txttk = root.findViewById(R.id.txtTaiKhoan);
        txttk.setText(MenuItem.listUser.get(0).getUser());
        name = root.findViewById(R.id.name);
        name.setText(MenuItem.listUser.get(0).getName());
        phone = root.findViewById(R.id.phone);
        phone.setText(MenuItem.listUser.get(0).getPhone());
        btnsave = root.findViewById(R.id.save);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sdt = phone.getText().toString();
                String ten = name.getText().toString();
                if (sdt.isEmpty() && ten.isEmpty()) {
                    phone.setError("Vui lòng nhập số điện thoại");
                    name.setError("Vui lòng nhập tên");
                } else {
                    if (Register.isNumeric(sdt) == false) {
                        phone.setError("Định dạng không đúng");
                    } else {
                        changeInfo();
                    }
                }
            }
        });
        return root;
    }
    public void  changeInfo(){
        StringRequest request = new StringRequest(Request.Method.POST, Server.changeInfo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("1")) {
                            MenuItem.txt_user.setText(name.getText().toString());
                            MenuItem.txt_phone.setText(phone.getText().toString());
                            Toast.makeText(getContext(), "Thay đổi thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Thay đổi thất bại", Toast.LENGTH_SHORT).show();
                        }
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
                HashMap<String,String> params = new HashMap<>();
                params.put("user", txttk.getText().toString());
                params.put("name", name.getText().toString());
                params.put("phone", phone.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = new Volley().newRequestQueue(getContext());
        requestQueue.add(request);
    }
}