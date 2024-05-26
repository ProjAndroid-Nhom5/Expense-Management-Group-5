package com.example.expensemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Bill_Detail_Store extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bill_detail_store);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        ImageView close_store_detail = findViewById(R.id.close_store_detail);

        close_store_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bill_Detail_Store.this, BillInformation.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        String nameEmployee = getIntent().getStringExtra("nameEmployee");
        String date = getIntent().getStringExtra("date");
        Float totalPayment = getIntent().getFloatExtra("totalPayment", 0.0f);

        EditText inputNameEmployee = findViewById(R.id.inputNameEmployee);
        EditText inputDate = findViewById(R.id.inputDate);
        TextView TotalPayment =  findViewById(R.id.totalPayment);

        inputNameEmployee.setText(nameEmployee);
        inputDate.setText(date);
        TotalPayment.setText(String.valueOf(totalPayment));
    }
}