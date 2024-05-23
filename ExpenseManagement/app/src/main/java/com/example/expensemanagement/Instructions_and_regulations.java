package com.example.expensemanagement;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Instructions_and_regulations extends AppCompatActivity {

    private ImageView left_instructions_and_regulations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_instructions_and_regulations);
        left_instructions_and_regulations = findViewById(R.id.left_instructions_and_regulations);

        left_instructions_and_regulations.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
    }
}