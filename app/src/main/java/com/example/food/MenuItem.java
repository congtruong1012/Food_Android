package com.example.food;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.food.Model.RestaurantModel;
import com.example.food.Model.UserModel;
import com.example.food.ui.gallery.GalleryFragment;
import com.example.food.ui.home.HomeFragment;
import com.example.food.ui.share.ShareFragment;
import com.example.food.ui.slideshow.SlideshowFragment;
import com.example.food.ui.tools.ToolsFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MenuItem extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private AppBarConfiguration mAppBarConfiguration;
    NavigationView navigationView = null;
    Toolbar toolbar = null;
    Button logout, cancel;
    public static TextView txt_user;
    public static TextView txt_phone;
    public static  ArrayList<UserModel> listUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);
        Intent intent = getIntent();
        listUser = (ArrayList<UserModel>) intent.getSerializableExtra("dsUser");
        if(listUser == null){
            Intent intent1 = getIntent();
            listUser = (ArrayList<UserModel>) intent1.getSerializableExtra("listUser");
        }
        Toast.makeText(getApplicationContext(),"Hello  , Welcome to Eat It Restaurant",Toast.LENGTH_SHORT).show();
        //Default
        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment,homeFragment);
        fragmentTransaction.commit();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_SHORT).show();
//            }
//        });
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        txt_user = headerView.findViewById(R.id.txtTaiKhoan);
        //for (int j = 0 ; j< listUser.size(); j++){
        txt_user.setText(listUser.get(0).getName());
            //break;
        //}
        txt_phone = headerView.findViewById(R.id.txtSDT);
//        for (int j = 0 ; j< listUser.size(); j++){
        txt_phone.setText("SĐT: ".concat(listUser.get(0).getPhone()));
//            break;
//        }
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(android.view.MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.nav_home) {
             HomeFragment fragment = new HomeFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
            fragmentTransaction.commit();
//        }else if(id == R.id.nav_gallery){
//            GalleryFragment fragment = new GalleryFragment();
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
//            fragmentTransaction.commit();
        }else if(id == R.id.nav_slideshow){
            SlideshowFragment fragment = new SlideshowFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
            fragmentTransaction.commit();
        }else if(id == R.id.nav_tools){
            ToolsFragment fragment = new ToolsFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
            fragmentTransaction.commit();
        }else if(id == R.id.nav_share){
            ShareFragment fragment = new ShareFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
            fragmentTransaction.commit();
        }else {
            if(id == R.id.nav_send){
                //modelList.clear();

                openDialog();
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void openDialog(){
        final Dialog builder = new Dialog(this); // Context, this, etc.
        builder.setContentView(R.layout.dialogdesign);
        builder.setTitle(R.string.dialog_popup);
        builder.show();
        logout = (Button) builder.findViewById(R.id.dialog_ok);
        cancel = (Button) builder.findViewById(R.id.dialog_cancel);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listUser.clear();
                if(ListFoodActivity.cartList != null&& SlideshowFragment.list != null) {
                    ListFoodActivity.cartList.clear();
                    SlideshowFragment.list.clear();
                    DeleteAllCart();
                }
                else{
                    ListFoodActivity.cartList = null;
                    SlideshowFragment.list = null;
                }
                Toast.makeText(getApplicationContext(),"Hi vọng bạn hài lòng với dịch vụ của chúng tôi \n" +
                        "Chúc bạn một ngày tốt lành !!!",Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"So you don't want to, Logout !!!",Toast.LENGTH_SHORT).show();
                builder.dismiss();
            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public void DeleteAllCart(){

        for(RestaurantModel restaurant : HomeFragment.modelList){
            ListCartActivity.DeleteByRestaurant(restaurant.getId());
        }
    }
}
