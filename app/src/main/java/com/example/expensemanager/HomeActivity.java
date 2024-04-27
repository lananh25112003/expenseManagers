package com.example.expensemanager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle("Expense Manager");
        setSupportActionBar(toolbar);

        bottomNavigationView = findViewById(R.id.bottomNavigationbar);
        frameLayout = findViewById(R.id.main_frame);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        // Tạo toggle để điều khiển DrawerLayout và ActionBar
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);

        
    }
    // onBack dùng khi người dùng nhấn nút Back, nhưng ở đây lỗi thời vì điện thoại này ko có 3 nút dưới.
    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout =findViewById(R.id.drawer_layout);

        // Đóng DrawerLayout nếu nó đang mở khi nhấn nút Back
        if(drawerLayout.isDrawerOpen(GravityCompat.END)){
            drawerLayout.closeDrawer(GravityCompat.END);
        }else{
            super.onBackPressed();
        }
    }

    // Phương thức này được gọi khi người dùng chọn một mục trong NavigationView
    public void displaySelectedListener(int itemId){

        Fragment fragment = null;

        // Kiểm tra xem người dùng đã chọn mục nào và thực hiện xử lý tương ứng
        if (itemId == R.id.dashboard) {
            // Xử lý khi chọn dashboard
        } else if (itemId == R.id.income) {
            // Xử lý khi chọn income
        } else if (itemId == R.id.expense) {
            // Xử lý khi chọn expense
        }

        // Thay thế fragment hiện tại bằng fragment mới (nếu có)
        if (fragment != null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Gọi phương thức displaySelectedListener() khi người dùng chọn một mục trong NavigationView
        displaySelectedListener(item.getItemId());
        return true;
    }
}