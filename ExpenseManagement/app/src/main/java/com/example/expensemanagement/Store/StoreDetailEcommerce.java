package com.example.expensemanagement.Store;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expensemanagement.R;
import com.example.expensemanagement.Store.Model.ListStoreEcommerceData;
import com.example.expensemanagement.Store.Model.ListStoreProductData;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StoreDetailEcommerce extends AppCompatActivity {
    EditText inputNameEcommerce,inputPaymentFee, inputFixedFee,
    inputServiceFee, inputFreightSurcharge, inputReceivable, inputVAT;
    Button btn_update, btn_delete;
    private DatabaseReference databaseReference;
    private int ecommerceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_store_detail_ecommerce);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        inputNameEcommerce = findViewById(R.id.inputNameEcommerce);
        inputPaymentFee = findViewById(R.id.inputPaymentFee);
        inputFixedFee = findViewById(R.id.inputFixedFee);
        inputServiceFee = findViewById(R.id.inputServiceFee);
        inputFreightSurcharge = findViewById(R.id.inputFreightSurcharge);
        inputReceivable = findViewById(R.id.inputReceivable);
        inputVAT = findViewById(R.id.inputVAT);

        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);

        ImageView closeIcon = findViewById(R.id.close_icon);
        closeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoreDetailEcommerce.this, StoreInformation.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        Intent intent = getIntent();
        ecommerceId = intent.getIntExtra("ecommerceId", -1);

        if (ecommerceId == -1) {
        } else {
            String name = intent.getStringExtra("inputNameEcommerce");
            int paymentFee = intent.getIntExtra("inputPaymentFee", 0);
            int fixedFee = intent.getIntExtra("inputFixedFee", 0);
            int serviceFee = intent.getIntExtra("inputServiceFee", 0);
            float freightSurcharge = intent.getFloatExtra("inputFreightSurcharge", 0);
            int receivable = intent.getIntExtra("inputReceivable", 0);
            float vat = intent.getFloatExtra("inputVAT", 0);

            inputNameEcommerce.setText(name);
            inputPaymentFee.setText(String.valueOf(paymentFee));
            inputFixedFee.setText(String.valueOf(fixedFee));
            inputServiceFee.setText(String.valueOf(serviceFee));
            inputFreightSurcharge.setText(String.valueOf(freightSurcharge));
            inputReceivable.setText(String.valueOf(receivable));
            inputVAT.setText(String.valueOf(vat));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("ecommerces");

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ecommerceId != -1) {
                    updateEcommerce();
                }
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDeleteEcommerce();
            }
        });
    }
    private void updateEcommerce() {
        String name = inputNameEcommerce.getText().toString().trim();
        String paymentFeeStr = inputPaymentFee.getText().toString().trim();
        String fixedFeeStr = inputFixedFee.getText().toString().trim();
        String serviceFeeStr = inputServiceFee.getText().toString().trim();
        String freightSurchargeStr = inputFreightSurcharge.getText().toString().trim();
        String receivableStr = inputReceivable.getText().toString().trim();
        String vatStr = inputVAT.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(paymentFeeStr) || TextUtils.isEmpty(fixedFeeStr) || TextUtils.isEmpty(serviceFeeStr) || TextUtils.isEmpty(freightSurchargeStr) || TextUtils.isEmpty(receivableStr) || TextUtils.isEmpty(vatStr)) {
            Toast.makeText(StoreDetailEcommerce.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int PaymentFee, FixedFee, ServiceFee, Receivable;
        float FreightSurcharge, VAT;

        try {
            PaymentFee = Integer.parseInt(paymentFeeStr);
            FixedFee = Integer.parseInt(fixedFeeStr);
            ServiceFee = Integer.parseInt(serviceFeeStr);
            Receivable = Integer.parseInt(receivableStr);
            FreightSurcharge = Float.parseFloat(freightSurchargeStr);
            VAT = Float.parseFloat(vatStr);

        } catch (NumberFormatException e) {
            Toast.makeText(StoreDetailEcommerce.this, "PaymentFee, FixedFee, ServiceFee, Receivable, FreightSurcharge and VAT Must be numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        ListStoreEcommerceData ecommerceData = new ListStoreEcommerceData(name, ecommerceId, PaymentFee, FixedFee, ServiceFee, Receivable, FreightSurcharge, VAT);

        databaseReference.child(String.valueOf(ecommerceId)).setValue(ecommerceData);

        Toast.makeText(StoreDetailEcommerce.this, "Ecommerce updated successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(StoreDetailEcommerce.this, StoreInformation.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    private void confirmDeleteEcommerce() {
        new AlertDialog.Builder(this)
                .setTitle("Delete Ecommerce")
                .setMessage("Are you sure you want to delete this Ecommerce?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        deleteEcommerce();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void deleteEcommerce() {
        databaseReference.child(String.valueOf(ecommerceId)).removeValue();

        Toast.makeText(StoreDetailEcommerce.this, "Ecommerce deleted successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(StoreDetailEcommerce.this, StoreInformation.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }
}