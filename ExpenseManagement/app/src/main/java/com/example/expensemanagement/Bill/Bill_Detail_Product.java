package com.example.expensemanagement.Bill;

import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.expensemanagement.Bill.Model.BillEcommerce;
import com.example.expensemanagement.Bill.Model.BillFacility;
import com.example.expensemanagement.Bill.Model.BillProduct;
import com.example.expensemanagement.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Bill_Detail_Product extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bill_detail_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        ImageView close_product_detail = findViewById(R.id.close_product_detail);

        close_product_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bill_Detail_Product.this, BillInformation.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        BillProduct billProduct = (BillProduct) getIntent().getSerializableExtra("billProduct");

        EditText input_product_detail = findViewById(R.id.input_product_detail);
        EditText inputNameProduct = findViewById(R.id.inpuNameProduct);
        EditText billId = findViewById(R.id.inputBillID);
        TextView inputQuantity =  findViewById(R.id.inputQuantity);
        LinearLayout remove =  findViewById(R.id.remove);

        input_product_detail.setText(String.valueOf(billProduct.getBill_ProductID()));
        inputNameProduct.setText(billProduct.getNameProduct());
        billId.setText(String.valueOf(billProduct.getBillID()));
        inputQuantity.setText(String.valueOf(billProduct.getQuantity()));

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDeleteEcommerce(billProduct);
            }
        });
    }

    private void confirmDeleteEcommerce(BillProduct billProduct) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Bill Product")
                .setMessage("Are you sure you want to delete this Bill Product?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("billProducts/"+billProduct.getBill_ProductID());
                        myRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Bill_Detail_Product.this, "Data deleted successfully.", Toast.LENGTH_SHORT).show();
                                    onBackPressed();
                                    finish();
                                } else {
                                    Toast.makeText(Bill_Detail_Product.this, "Error deleting data.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}