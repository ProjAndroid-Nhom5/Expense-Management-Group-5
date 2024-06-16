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

import com.example.expensemanagement.Bill.Model.BillStore;
import com.example.expensemanagement.Bill.Model.BillSupply;
import com.example.expensemanagement.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Bill_Detail_Supply extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bill_detail_supply);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        ImageView close_supply_detail = findViewById(R.id.close_supply_detail);

        close_supply_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bill_Detail_Supply.this, BillInformation.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        BillSupply billSupply = (BillSupply) getIntent().getSerializableExtra("billSupply");

        EditText input_supply_detail = findViewById(R.id.input_supply_detail);
        EditText inputNameSupplier = findViewById(R.id.inpuNameSupllier);
        EditText inputDate = findViewById(R.id.inputDate);
        EditText inputNumber = findViewById(R.id.inputNumber);
        EditText inputProductCost = findViewById(R.id.inputProductCost);
        EditText inputShiCost = findViewById(R.id.inputShiCost);
        TextView TotalPayment =  findViewById(R.id.totalPayment);
        LinearLayout remove =  findViewById(R.id.remove);

        input_supply_detail.setText(String.valueOf(billSupply.getId()));
        inputNameSupplier.setText(billSupply.getNameSupplier());
        inputDate.setText(billSupply.getDate());
        inputNumber.setText(String.valueOf(billSupply.getNumber()));
        inputProductCost.setText(String.valueOf(billSupply.getProductCost()));
        inputShiCost.setText(String.valueOf(billSupply.getShipCost()));
        TotalPayment.setText(String.valueOf(billSupply.getTotal()));
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("billSupplies/"+billSupply.getId());
                myRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Bill_Detail_Supply.this, "Data deleted successfully.", Toast.LENGTH_SHORT).show();
                            onBackPressed();
                            finish();
                        } else {
                            Toast.makeText(Bill_Detail_Supply.this, "Error deleting data.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}