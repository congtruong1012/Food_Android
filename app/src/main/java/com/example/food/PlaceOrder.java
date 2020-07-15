package com.example.food;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.food.Model.BillInfor;
import com.example.food.Model.CartModel;
import com.example.food.Model.RestaurantModel;
import com.example.food.ui.slideshow.SlideshowFragment;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaceOrder extends AppCompatActivity {
    private Toolbar toolbarPlaceOrder;
    private EditText editDate, editAddr;
    private Button btnThanhToan;
    private RadioGroup radioGroup;
    private RestaurantModel restaurant;
    private TextView txtHoTen, txtSDT, txtNhahang, txtTongTien ;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
    private int tong;
    private List<BillInfor> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        editDate = findViewById(R.id.editDate);
        //Lấy họ tên
        txtHoTen = findViewById(R.id.txtHoten);
        txtHoTen.setText(MenuItem.listUser.get(0).getName());
        //Lấy sđt
        txtSDT = findViewById(R.id.txtphonenumber);
        txtSDT.setText(MenuItem.listUser.get(0).getPhone());
        Intent intent = getIntent();
        restaurant = (RestaurantModel) intent.getSerializableExtra("nhahang");
        tong = (Integer) intent.getSerializableExtra("TotalPrice");
        //Lấy tên nhà hàng
        txtNhahang = findViewById(R.id.txtNhahang);
        txtNhahang.setText(restaurant.getName());
        //Lấy tông tiền
        txtTongTien = findViewById(R.id.txtTongTien);
        txtTongTien.setText(decimalFormat.format(tong));
        toolbarPlaceOrder = findViewById(R.id.toolPlaceOrder);
        setSupportActionBar(toolbarPlaceOrder);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //Lấy ngày
        editDate.setText(simpleDateFormat.format(new Date()).toString());
        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChonNgay();
            }
        });
        //Lấy địa chỉ
        editAddr = findViewById(R.id.txtDiaChi);
        if(editAddr.getText().toString().isEmpty()){
            editAddr.setError("Vui lòng nhập địa chỉ");
        }
        //Phương thức thanh toán
        radioGroup = findViewById(R.id.checkThanhToan);
        //Nút thanh toán
        btnThanhToan = findViewById(R.id.btnThanhToan);
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addr = editAddr.getText().toString();
                if(checkRadioClick()==0){
                    Toast.makeText(getApplicationContext(),
                            "Vui lòng chọn phương thức thanh toán",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(addr.isEmpty()) {
                        Toast.makeText(getApplicationContext(),
                                "Vui lòng nhập địa chỉ",Toast.LENGTH_SHORT).show();

                    }
                    else {
                        EventThanhToan();
                    }
                }
            }
        });
    }
    public void DeleteCart(){

        ListCartActivity.txtThongBao.setVisibility(View.VISIBLE);
        ListFoodActivity.cartList = (ArrayList<CartModel>)
                ListCartActivity.DeleteByRestaurant(restaurant.getId());
        /*ListCartActivity.adapter.UpdateList(ListCartActivity.ShowByRestaurant(
                restaurant.getId()
        ));*/
        /*ListCartActivity.txtTotal.setText(decimalFormat.format(
                ListCartActivity.adapter.Tong()));
        ListFoodActivity.mCount.setNumber(ListFoodActivity.Count(restaurant.getId()));*/

        Toast.makeText(getApplicationContext(),
                "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
    }
    private void EventThanhToan() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.order,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String idDH) {
                        if(Integer.parseInt(idDH)>0){
                           InsertOrderDetail(idDH);
                        }
                        else
                            Toast.makeText(getApplicationContext(),
                                    "Kiểm tra lại dữ liệu Order", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),
                                error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("idKH",MenuItem.listUser.get(0).getUser());
                params.put("address",editAddr.getText().toString());
                params.put("idRes",restaurant.getId());
                params.put("total", Integer.toString(tong));
                params.put("date_receive", editDate.getText().toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void InsertOrderDetail(final String idDH){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        final StringRequest request = new StringRequest(Request.Method.POST,
                Server.orderDetail, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("1")){
                    DeleteCart();
                    Intent intent = new Intent(getApplicationContext(),MenuItem.class);
                    intent.putExtra("listUser",MenuItem.listUser);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(getApplicationContext(),
                            "Kiểm tra lại dữ liệu Detail", Toast.LENGTH_SHORT).show();
                    Log.e("lỗi",":"+ response);

                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),
                                error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
               /* try {
                    JSONArray jsonArray = new JSONArray();
                    JSONObject jsonObject = new JSONObject();
                    for (CartModel cart : ListCartActivity.ShowByRestaurant(restaurant.getId())) {


                        jsonObject.put("idFood", cart.getIdFood());
                        jsonObject.put("idDH", idDH);
                        jsonObject.put("count", cart.getCount());
                        jsonObject.put("price", cart.getPrice());
                        jsonArray.put(jsonObject);


                    }

                    params.put("json", jsonArray.toString());
                    Log.i("Place order", "getParams: " + params);
                    //Toast.makeText(getApplicationContext(),jsonArray.toString(),Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
                list = new ArrayList<>();
                for(int i = 0 ; i< ListFoodActivity.cartList.size();i++){
                    CartModel cart = ListFoodActivity.cartList.get(i);
                    if(cart.getIdRes().trim().toLowerCase().equals(restaurant.getId().toLowerCase().trim())) {
                        String idFood = cart.getIdFood();
                        int count = cart.getCount();
                        int price = cart.getPrice();
                        BillInfor billInfor = new BillInfor(idDH, idFood, count, price);
                        list.add(billInfor);
                    }

                }

                 Gson gson = new Gson();
                String json = gson.toJson(list);
                params.put("json", json);
                Log.i("PlaceOrder"," "+params);

                return params;
            }
        };
        queue.add(request);
    }
    public int checkRadioClick(){
        if (radioGroup.getCheckedRadioButtonId() == -1)
            return 0;
        else
            return 1;
    }
    private void ChonNgay() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        final Date dateStart = new Date();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i, i1 ,i2);
                if(checkDate(calendar.getTime(),dateStart)==0) {
                    editDate.setText(simpleDateFormat.format(calendar.getTime()));
                    editDate.setError(null);
                }
                else
                    editDate.setError("Vui lòng chọn lại ngày");
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }
    public int checkDate(Date start, Date end){
        long diff = end.getTime() - start.getTime();
        long diffDays = diff/ (24 * 60 * 60 * 1000);
        if(diffDays >= 0)
            return 1;//Đúng
        else
            return 0;//Sai
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
