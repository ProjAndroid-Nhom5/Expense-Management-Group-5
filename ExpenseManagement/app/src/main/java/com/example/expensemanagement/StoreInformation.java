package com.example.expensemanagement;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.widget.ImageView;
import android.view.View;
import android.content.Intent;

public class StoreInformation extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_store_information);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        if (position == 1) {
            loadFragment(new StoreInfEcommerce(), true);
        } else if (position == 2) {
            loadFragment(new StoreInfEmployee(), true);
        } else if (position == 3) {
            loadFragment(new StoreInfProduct(), true);
        }else if (position == 4) {
            loadFragment(new StoreInfSupplier(), true);
        }

        ImageView exitButton = findViewById(R.id.exit_icon);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); //
            }
        });
    }
    private void loadFragment(Fragment fragment, boolean isAppInitialized) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (isAppInitialized){
            fragmentTransaction.add(R.id.storeFrame, fragment);
        }else {
            fragmentTransaction.replace(R.id.storeFrame, fragment);
        }
        fragmentTransaction.commit();
    }
}