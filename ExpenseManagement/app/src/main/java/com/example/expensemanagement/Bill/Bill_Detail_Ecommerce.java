package com.example.expensemanagement.Bill;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.expensemanagement.Admin.SendOTPActivity;
import com.example.expensemanagement.Bill.Model.BillEcommerce;
import com.example.expensemanagement.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Bill_Detail_Ecommerce extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bill_detail_ecommerce);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        ImageView close_ecommerce_detail = findViewById(R.id.close_ecommerce_detail);

        close_ecommerce_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bill_Detail_Ecommerce.this, BillInformation.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        BillEcommerce billEcommerce = (BillEcommerce) getIntent().getSerializableExtra("billEcommerce");

        EditText input_ecommerce_detail = findViewById(R.id.input_ecommerce_detail);
        EditText inputNameEcommerce = findViewById(R.id.inputNameEcommerce);
        EditText inputDate = findViewById(R.id.inputDate);
        EditText inputNameCustomer = findViewById(R.id.inputNameCustomer);
        EditText inputPhone = findViewById(R.id.inputPhone);
        EditText inputAddress = findViewById(R.id.inputAddress);
        EditText inputProductCost = findViewById(R.id.inputProductCost);
        EditText inputShipCost = findViewById(R.id.inputShipCost);
        EditText inputPaymentTime = findViewById(R.id.inputPaymentTime);
        TextView TotalPayment =  findViewById(R.id.totalPayment);
        LinearLayout remove =  findViewById(R.id.remove);

        input_ecommerce_detail.setText(String.valueOf(billEcommerce.getId()));
        inputNameEcommerce.setText(billEcommerce.getNameEcommerce());
        inputDate.setText(billEcommerce.getDate());
        inputNameCustomer.setText(billEcommerce.getNameCustomer());
        inputPhone.setText(billEcommerce.getPhone());
        inputAddress.setText(billEcommerce.getAddress());
        inputProductCost.setText(String.valueOf(billEcommerce.getProductCost()));
        inputShipCost.setText(String.valueOf(billEcommerce.getShipCost()));
        inputPaymentTime.setText(billEcommerce.getPaymentTime());
        TotalPayment.setText(String.valueOf(billEcommerce.getTotalPayment()));

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("billEcommerces/"+billEcommerce.getId());
                myRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Bill_Detail_Ecommerce.this, "Data deleted successfully.", Toast.LENGTH_SHORT).show();
                            onBackPressed();
                            finish();
                        } else {
                            Toast.makeText(Bill_Detail_Ecommerce.this, "Error deleting data.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}