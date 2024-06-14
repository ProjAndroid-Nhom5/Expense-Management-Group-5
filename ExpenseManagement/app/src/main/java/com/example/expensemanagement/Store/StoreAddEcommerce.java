package com.example.expensemanagement.Store;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.util.Log;

import com.example.expensemanagement.R;
import com.example.expensemanagement.Store.Model.ListStoreEcommerceData;
import com.example.expensemanagement.Store.Model.ListStoreProductData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StoreAddEcommerce extends AppCompatActivity {

    EditText inputNameEcommerce,inputPaymentFee, inputFixedFee, inputServiceFee, inputFreightSurcharge, inputReceivable, inputVAT;
    Button btn_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_store_add_ecommerce);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView closeIcon = findViewById(R.id.close_icon);
        closeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        inputNameEcommerce = findViewById(R.id.inputNameEcommerce);
        inputPaymentFee = findViewById(R.id.inputPaymentFee);
        inputFixedFee = findViewById(R.id.inputFixedFee);
        inputServiceFee  = findViewById(R.id.inputServiceFee);
        inputFreightSurcharge = findViewById(R.id.inputFreightSurcharge);
        inputReceivable = findViewById(R.id.inputReceivable);
        inputVAT = findViewById(R.id.inputVAT);

        btn_save = findViewById(R.id.btn_save);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }
    public void saveData() {
        String NameEcommerce = inputNameEcommerce.getText().toString().trim();
        String PaymentFeeStr = inputPaymentFee.getText().toString().trim();
        String FixedFeeStr = inputFixedFee.getText().toString().trim();
        String ServiceFeeStr = inputServiceFee.getText().toString().trim();
        String FreightSurchargeStr = inputFreightSurcharge.getText().toString().trim();
        String ReceivableStr = inputReceivable.getText().toString().trim();
        String VATStr = inputVAT.getText().toString().trim();

        if (NameEcommerce.isEmpty() || PaymentFeeStr.isEmpty() || FixedFeeStr.isEmpty() ||
                ServiceFeeStr.isEmpty() || FreightSurchargeStr.isEmpty() || ReceivableStr.isEmpty() ||
                VATStr.isEmpty()) {
            Toast.makeText(StoreAddEcommerce.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int PaymentFee, FixedFee, ServiceFee, Receivable;
        float FreightSurcharge, VAT;

        try {
            PaymentFee = Integer.parseInt(PaymentFeeStr);
            FixedFee = Integer.parseInt(FixedFeeStr);
            ServiceFee = Integer.parseInt(ServiceFeeStr);
            Receivable = Integer.parseInt(ReceivableStr);
            FreightSurcharge = Float.parseFloat(FreightSurchargeStr);
            VAT = Float.parseFloat(VATStr);

        } catch (NumberFormatException e) {
            Toast.makeText(StoreAddEcommerce.this, "PaymentFee, FixedFee, ServiceFee, Receivable, FreightSurcharge, and VAT must be numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference ecommercesRef = FirebaseDatabase.getInstance().getReference("ecommerces");

        ecommercesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int maxEcommerceId = 0;
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    int currentId = Integer.parseInt(childSnapshot.getKey());
                    if (currentId > maxEcommerceId) {
                        maxEcommerceId = currentId;
                    }
                }
                int newEcommerceId = maxEcommerceId + 1;
                ListStoreEcommerceData ecommerceData = new ListStoreEcommerceData(NameEcommerce, newEcommerceId, FixedFee, ServiceFee, PaymentFee, Receivable, FreightSurcharge, VAT);

                ecommercesRef.child(String.valueOf(newEcommerceId))
                        .setValue(ecommerceData).addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(StoreAddEcommerce.this, "Saved information", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }).addOnFailureListener(e -> {
                            Toast.makeText(StoreAddEcommerce.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StoreAddEcommerce.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}