package com.example.food.ui.share;

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
import com.example.food.R;
import com.example.food.Server;

import java.util.HashMap;
import java.util.Map;

public class ShareFragment extends Fragment {

    private ShareViewModel shareViewModel;
    private TextView txttk;
    private EditText pass, repeatpass;
    private Button btnOK;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(ShareViewModel.class);
        View root = inflater.inflate(R.layout.fragment_share, container, false);
        txttk = root.findViewById(R.id.txtTaiKhoan);
        txttk.setText(MenuItem.listUser.get(0).getUser());
        pass = root.findViewById(R.id.pass);
        repeatpass = root.findViewById(R.id.repeatpass);
        btnOK = root.findViewById(R.id.ok);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangPassword();
            }
        });

        return root;
    }

    private void ChangPassword() {
        String mk = pass.getText().toString();
        String nlmk = repeatpass.getText().toString();
        if(mk.isEmpty()&&nlmk.isEmpty()){
            pass.setError("Vui lòng nhập mật khẩu");
            repeatpass.setError("Vui lòng nhập lại mật khẩu");
        }
        else {
            if(mk.equals(nlmk)==false){
                repeatpass.setError("Mật khẩu nhập lại không khớp");
            }
            else{
                StringRequest request = new StringRequest(Request.Method.POST, Server.changPassword,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("1")) {
                                    Toast.makeText(getContext(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getContext(), "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
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
                        params.put("pass", pass.getText().toString());
                        return params;
                    }
                };
                RequestQueue requestQueue = new Volley().newRequestQueue(getContext());
                requestQueue.add(request);
            }
        }
    }
}