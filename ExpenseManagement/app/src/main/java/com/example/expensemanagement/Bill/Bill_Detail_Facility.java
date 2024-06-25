package com.example.expensemanagement.Bill;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import com.example.expensemanagement.Bill.Model.BillEcommerce;
import com.example.expensemanagement.Bill.Model.BillFacility;
import com.example.expensemanagement.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

        BillFacility billFacility = (BillFacility) getIntent().getSerializableExtra("billFacility");

        EditText input_facitily_detail = findViewById(R.id.input_facitily_detail);
        EditText inpuNameFacility = findViewById(R.id.inpuNameFacility);
        EditText inputDate = findViewById(R.id.inputDate);
        EditText inputNameStore = findViewById(R.id.inputNameStore);
        EditText inputTranMethod = findViewById(R.id.inputTranMethod);
        TextView TotalPayment =  findViewById(R.id.totalPayment);
        LinearLayout remove =  findViewById(R.id.remove);

        input_facitily_detail.setText(String.valueOf(billFacility.getId()));
        inpuNameFacility.setText(billFacility.getNameFacility());
        inputDate.setText(billFacility.getDate());
        inputNameStore.setText(billFacility.getNameManager());
        inputTranMethod.setText(billFacility.getTransactionMethod());
        TotalPayment.setText(String.valueOf(billFacility.getTotalPayment()));

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("billFacilities/"+billFacility.getId());
                myRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Bill_Detail_Facility.this, "Data deleted successfully.", Toast.LENGTH_SHORT).show();
                            onBackPressed();
                            finish();
                        } else {
                            Toast.makeText(Bill_Detail_Facility.this, "Error deleting data.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}