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

import com.example.expensemanagement.Bill.Model.BillSupply;
import com.example.expensemanagement.Bill.Model.BillWorkshift;
import com.example.expensemanagement.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Bill_Detail_Workshift extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bill_detail_workshift);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        ImageView close_workshift_detail = findViewById(R.id.close_workshift_detail);

        close_workshift_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bill_Detail_Workshift.this, BillInformation.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        BillWorkshift billWorkshift = (BillWorkshift) getIntent().getSerializableExtra("billWorkshift");

        EditText input_workshift_detail = findViewById(R.id.input_workshift_detail);
        EditText inputNameEmployee = findViewById(R.id.inputNameEmployee);
        EditText inputDate = findViewById(R.id.inputDate);
        EditText inputTotalWorked = findViewById(R.id.inputTotalWorked);
        EditText inputSalary = findViewById(R.id.inputSalary);
        EditText inputPerTaxVat = findViewById(R.id.inputPerTaxVat);
        TextView TotalPayment =  findViewById(R.id.totalPayment);
        LinearLayout remove =  findViewById(R.id.remove);

        input_workshift_detail.setText(String.valueOf(billWorkshift.getId()));
        inputNameEmployee.setText(billWorkshift.getNameEmploye());
        inputDate.setText(billWorkshift.getDate());
        inputTotalWorked.setText(String.valueOf(billWorkshift.getTotalWorked()));
        inputSalary.setText(String.valueOf(billWorkshift.getSalary()));
        inputPerTaxVat.setText(String.valueOf(billWorkshift.getPITV()));
        TotalPayment.setText(String.valueOf(billWorkshift.getTotal()));
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("billWorkshifts/"+billWorkshift.getId());
                myRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Bill_Detail_Workshift.this, "Data deleted successfully.", Toast.LENGTH_SHORT).show();
                            onBackPressed();
                            finish();
                        } else {
                            Toast.makeText(Bill_Detail_Workshift.this, "Error deleting data.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}