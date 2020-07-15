package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText editUser, editPass, editName, editPhone;
    TextView  btnTV;
    Button btnDK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editUser = findViewById(R.id.txtuser);
        editUser = findViewById(R.id.txtuser);
        editUser = findViewById(R.id.txtuser);
        editPass = findViewById(R.id.txtpass);
        editName = findViewById(R.id.txtname);
        editPhone = findViewById(R.id.txtphone);
        btnDK = findViewById(R.id.btnDK);
        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(CheckIn(editUser.getText().toString())==0){
                   editUser.setError("Vui lòng nhập tài khoản");
               }
               else {
                   if(CheckIn(editPass.getText().toString())==0){
                       editPass.setError("Vui lòng nhập mật khẩu");
                   }
                   else {
                       if(CheckIn(editName.getText().toString())==0){
                           editName.setError("Vui lòng nhập họ tên");
                       }
                       else {
                           if(CheckIn(editPhone.getText().toString())==0){
                               editPhone.setError("Vui lòng số điện thoại");
                           }
                           else {
                               if(isNumeric(editPhone.getText().toString())==false){
                                   editPhone.setError("Định dạng không đúng");
                               }
                               else {
                                   register();
                               }
                           }
                       }
                   }
               }
            }
        });
        btnTV = findViewById(R.id.btnTVe);
        btnTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }

    private void register() {
        StringRequest request = new StringRequest(Request.Method.POST, Server.register,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                            try{
                                JSONObject jsonObject = new JSONObject(response);
                                String success = jsonObject.getString("thanhcong");
                                if (success.equals("1")) {
                                    Toast.makeText(getApplicationContext(), "Đăng ký thành công.", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }
                                else {
                                    if (success.equals("-1")) {
                                        //Toast.makeText(getApplicationContext(), "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                                        editUser.setError("Tài khoản đã tồn tại.");
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Lỗi kết nối CSDL", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                            //startActivity(new Intent(getApplicationContext(), MenuItem.class));
                            //Toast.makeText(getApplicationContext(),response, Toast.LENGTH_SHORT);
                        }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("username", editUser.getText().toString());
                params.put("password", editPass.getText().toString());
                params.put("name", editName.getText().toString());
                params.put("phone",editPhone.getText().toString());
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private int CheckIn(String text){
        if(text.equals("")){
            return 0;
        }
        return 1;
    }
    public static boolean isNumeric(String str)
    {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}
