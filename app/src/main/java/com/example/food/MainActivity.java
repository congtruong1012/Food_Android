package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.food.Model.UserModel;
import com.example.food.ui.home.HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText user, pass;
    Button login;
    TextView dangky;
    String taikhoan, matkhau;
    ArrayList<UserModel> dsUser= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=findViewById(R.id.username);
        pass = findViewById(R.id.password);
        login = findViewById(R.id.login);
        dangky = findViewById(R.id.btnRegister);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taikhoan = user.getText().toString();
                matkhau  = pass.getText().toString();
                if(!taikhoan.isEmpty()&&!matkhau.isEmpty()) {
                    login(taikhoan, matkhau);
                }
                else {
                    if (taikhoan.isEmpty()) {
                        user.setError("Vui lòng nhập tài khoản ");
                    }
                    if (matkhau.isEmpty()){
                        pass.setError("Vui lòng nhập mật khẩu");
                    }
                }
            }
        });
        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });

    }

    public  void login(final String user, final String pass){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.login
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("login");
                    if (success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String user = object.getString("user");
                            String pass = object.getString("pass");
                            String name = object.getString("name");
                            String phone = object.getString("phone");
                            dsUser.add(new UserModel(user,pass,name,phone));
                        }
                        Toast.makeText(getApplicationContext(),"Đăng nhập thành công",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MenuItem.class);
                        intent.putExtra("dsUser",dsUser);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Sai tài khoản hoặc mật khẩu",
                                Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.toString(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user", taikhoan);
                params.put("pass",matkhau);
                return params;
            }
        };
        RequestQueue requestQueue = new Volley().newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}