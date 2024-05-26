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

public class Bill_Detail_Facility extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bill_detail_facility);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        ImageView close_facility_detail = findViewById(R.id.close_facility_detail);

        close_facility_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bill_Detail_Facility.this, BillInformation.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        String nameFacility = getIntent().getStringExtra("nameFacility");
        String date = getIntent().getStringExtra("date");
        Float totalPayment = getIntent().getFloatExtra("totalPayment", 0.0f);

        EditText inputNameFacility = findViewById(R.id.input_facitily_detail);
        EditText inputDate = findViewById(R.id.inputDate);
        TextView TotalPayment =  findViewById(R.id.totalPayment);

        inputNameFacility.setText(nameFacility);
        inputDate.setText(date);
        TotalPayment.setText(String.valueOf(totalPayment));
    }
}