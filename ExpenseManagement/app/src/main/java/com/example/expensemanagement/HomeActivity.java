package com.example.expensemanagement;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {

//    private BottomNavigationView bottomNavigationView;
    private FrameLayout frame_layout;
    private int selectedTab = 1;
    private boolean isExpanded = false;
    private View transparentBg;
    private ConstraintLayout fabConstraint;
    private FloatingActionButton mainFabBtn,
            ecommerceFabBtn,
            employeeFabBtn,
            productFabBtn,
            supplierFabBtn;
    private TextView ecommerceTv,
            employeeTv,
            productTv,
            supplierTv;
    private Animation fromBottomFabAnim,
            toBottomFabAnim,
            rotateClockWiseFabAnim,
            rotateAntiClockWiseFabAnim,
            fromBottomBgAnim,
            toBottomBgAnim;

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
        transparentBg = findViewById(R.id.transparentBg);
        fabConstraint = findViewById(R.id.fabConstraint);

        mainFabBtn = findViewById(R.id.mainFabBtn);
        ecommerceFabBtn = findViewById(R.id.ecommerceFabBtn);
        employeeFabBtn = findViewById(R.id.employeeFabBtn);
        productFabBtn = findViewById(R.id.productFabBtn);
        supplierFabBtn = findViewById(R.id.supplierFabBtn);

        ecommerceTv = findViewById(R.id.ecommerceTv);
        employeeTv = findViewById(R.id.employeeTv);
        productTv = findViewById(R.id.productTv);
        supplierTv = findViewById(R.id.supplierTv);

        fromBottomFabAnim = AnimationUtils.loadAnimation(this, R.anim.from_bottom_fab);
        toBottomFabAnim = AnimationUtils.loadAnimation(this, R.anim.to_bottom_fab);
        rotateClockWiseFabAnim = AnimationUtils.loadAnimation(this, R.anim.rotate_clock_wise);
        rotateAntiClockWiseFabAnim = AnimationUtils.loadAnimation(this, R.anim.rotate_anti_clock_wise);
        fromBottomBgAnim = AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim);
        toBottomBgAnim = AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim);

        mainFabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpanded) {
                    shrinkFab();
                } else {
                    expandFab();
                }
            }
        });

        ecommerceFabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, StoreAddEcommerce.class);
                startActivity(intent);
            }
        });

        employeeFabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, StoreAddEmployee.class);
                startActivity(intent);
            }
        });
        productFabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, StoreAddProduct.class);
                startActivity(intent);
            }
        });

        supplierFabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, StoreAddSupplier.class);
                startActivity(intent);
            }
        });

        ecommerceTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, StoreAddEcommerce.class);
                startActivity(intent);
            }
        });

        employeeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, StoreAddEmployee.class);
                startActivity(intent);
            }
        });
        productTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, StoreAddProduct.class);
                startActivity(intent);
            }
        });

        supplierTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, StoreAddSupplier.class);
                startActivity(intent);
            }
        });

        final LinearLayout homeLayout = findViewById(R.id.homeLayout);
        final LinearLayout billLayout = findViewById(R.id.billLayout);
        final LinearLayout storeLayout = findViewById(R.id.storeLayout);
        final LinearLayout adminLayout = findViewById(R.id.adminLayout);

        final ImageView homeImage = findViewById(R.id.homeImage);
        final ImageView billImage = findViewById(R.id.billImage);
        final ImageView storeImage = findViewById(R.id.storeImage);
        final ImageView adminImage = findViewById(R.id.adminImage);

        final TextView homeTxt = findViewById(R.id.homeTxt);
        final TextView billTxt = findViewById(R.id.billTxt);
        final TextView storeTxt = findViewById(R.id.storeTxt);
        final TextView adminTxt = findViewById(R.id.adminTxt);

        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTab != 1){
                    billTxt.setVisibility(View.GONE);
                    storeTxt.setVisibility(View.GONE);
                    adminTxt.setVisibility(View.GONE);

                    billImage.setImageResource(R.drawable.bill);
                    storeImage.setImageResource(R.drawable.store);
                    adminImage.setImageResource(R.drawable.admin);

                    billLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    storeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    adminLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    homeTxt.setVisibility(View.VISIBLE);
                    homeImage.setImageResource(R.drawable.home_selected);
                    homeLayout.setBackgroundResource(R.drawable.round_back_home_100);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    homeLayout.startAnimation(scaleAnimation);

                    loadFragment(new HomeFragment(),false);
                    mainFabBtn.setVisibility(View.VISIBLE);

                    selectedTab = 1;
                }
            }
        });

        billLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTab != 2){
                    homeTxt.setVisibility(View.GONE);
                    storeTxt.setVisibility(View.GONE);
                    adminTxt.setVisibility(View.GONE);

                    homeImage.setImageResource(R.drawable.home);
                    storeImage.setImageResource(R.drawable.store);
                    adminImage.setImageResource(R.drawable.admin);

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    storeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    adminLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    billTxt.setVisibility(View.VISIBLE);
                    billImage.setImageResource(R.drawable.bill_selected);
                    billLayout.setBackgroundResource(R.drawable.round_back_home_100);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    billLayout.startAnimation(scaleAnimation);

                    loadFragment(new BillFragment(),false);
                    mainFabBtn.setVisibility(View.INVISIBLE);

                    selectedTab = 2;
                }
            }
        });

        storeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTab != 3){
                    billTxt.setVisibility(View.GONE);
                    homeTxt.setVisibility(View.GONE);
                    adminTxt.setVisibility(View.GONE);

                    billImage.setImageResource(R.drawable.bill);
                    homeImage.setImageResource(R.drawable.home);
                    adminImage.setImageResource(R.drawable.admin);

                    billLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    adminLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    storeTxt.setVisibility(View.VISIBLE);
                    storeImage.setImageResource(R.drawable.store_selected);
                    storeLayout.setBackgroundResource(R.drawable.round_back_home_100);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    storeLayout.startAnimation(scaleAnimation);

                    loadFragment(new StoreFragment(),false);
                    mainFabBtn.setVisibility(View.INVISIBLE);

                    selectedTab = 3;
                }
            }
        });

        adminLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTab != 4){
                    billTxt.setVisibility(View.GONE);
                    storeTxt.setVisibility(View.GONE);
                    homeTxt.setVisibility(View.GONE);

                    billImage.setImageResource(R.drawable.bill);
                    storeImage.setImageResource(R.drawable.store);
                    homeImage.setImageResource(R.drawable.home);

                    billLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    storeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    adminTxt.setVisibility(View.VISIBLE);
                    adminImage.setImageResource(R.drawable.admin_selected);
                    adminLayout.setBackgroundResource(R.drawable.round_back_home_100);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    adminLayout.startAnimation(scaleAnimation);

                    loadFragment(new AdminFragment(),false);
                    mainFabBtn.setVisibility(View.INVISIBLE);

                    selectedTab = 4;
                }
            }
        });

        frame_layout = findViewById(R.id.frameLayout);

        loadFragment(new HomeFragment(),true);
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

    private void shrinkFab() {
        transparentBg.startAnimation(toBottomBgAnim);
        mainFabBtn.startAnimation(rotateAntiClockWiseFabAnim);
        ecommerceFabBtn.startAnimation(toBottomFabAnim);
        employeeFabBtn.startAnimation(toBottomFabAnim);
        productFabBtn.startAnimation(toBottomFabAnim);
        supplierFabBtn.startAnimation(toBottomFabAnim);
        ecommerceTv.startAnimation(toBottomFabAnim);
        employeeTv.startAnimation(toBottomFabAnim);
        productTv.startAnimation(toBottomFabAnim);
        supplierTv.startAnimation(toBottomFabAnim);

        isExpanded = !isExpanded;
    }

    private void expandFab() {
        transparentBg.startAnimation(fromBottomBgAnim);
        mainFabBtn.startAnimation(rotateClockWiseFabAnim);
        ecommerceFabBtn.startAnimation(fromBottomFabAnim);
        employeeFabBtn.startAnimation(fromBottomFabAnim);
        productFabBtn.startAnimation(fromBottomFabAnim);
        supplierFabBtn.startAnimation(fromBottomFabAnim);
        ecommerceTv.startAnimation(fromBottomFabAnim);
        employeeTv.startAnimation(fromBottomFabAnim);
        productTv.startAnimation(fromBottomFabAnim);
        supplierTv.startAnimation(fromBottomFabAnim);

        isExpanded = !isExpanded;
    }

    @Override
    public void onBackPressed() {
        if (isExpanded) {
            shrinkFab();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (isExpanded) {
                Rect outRect = new Rect();
                fabConstraint.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) ev.getRawX(), (int) ev.getRawY())) {
                    shrinkFab();
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}
