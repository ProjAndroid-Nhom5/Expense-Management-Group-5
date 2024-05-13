package com.example.expensemanagement;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frame_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        frame_layout = findViewById(R.id.frameLayout);

//        // Măc định cho ban đầu là home
//        switchFragment(new HomeFragment());
//        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId) {
//                    case R.id.rbHome:
//                        switchFragment(new HomeFragment());
//                        break;
//                    case R.id.rbBill:
//                        switchFragment(new BillFragment());
//                        break;
//                    case R.id.rbStore:
//                        switchFragment(new StoreFragment());
//                        break;
//                    case R.id.rbAdmin:
//                        switchFragment(new AdminFragment());
//                        break;
//                }
//            }
//        });

        loadFragment(new HomeFragment(),true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemID = menuItem.getItemId();

                switch (itemID) {
                    case R.id.navHome:
                        loadFragment(new HomeFragment(),false);
                        break;
                    case R.id.navBill:
                        loadFragment(new BillFragment(),false);
                        break;
                    case R.id.navStore:
                        loadFragment(new StoreFragment(),false);
                        break;
                    case R.id.navAdmin:
                        loadFragment(new AdminFragment(),false);
                        break;
                }

                return true;
            }
        });
    }

    private void loadFragment(Fragment fragment, boolean isAppInitialized) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (isAppInitialized){
            fragmentTransaction.add(R.id.frameLayout, fragment);
        }else {
            fragmentTransaction.replace(R.id.frameLayout, fragment);
        }
        fragmentTransaction.commit();
    }
}