package com.example.expensemanagement.Store;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.expensemanagement.R;
import com.example.expensemanagement.Store.Model.ListStoreEcommerceData;
import com.example.expensemanagement.Store.Model.ListStoreSupplierData;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StoreDetailSupplier extends AppCompatActivity {
    EditText inputNameSupplier;
    Button btn_update, btn_delete;
    private DatabaseReference databaseReference;
    private int supplierId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_store_detail_supplier);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ImageView closeIcon = findViewById(R.id.close_icon);
        closeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoreDetailSupplier.this, StoreInformation.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });
        inputNameSupplier = findViewById(R.id.inputNameSupplier);

        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        supplierId = intent.getIntExtra("supplierId", -1);

        if (supplierId == -1) {
        } else {
            String name = intent.getStringExtra("inputNameSupplier");

            inputNameSupplier.setText(name);
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("suppliers");

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (supplierId != -1) {
                    updateSupplier();
                }
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDeleteSupplier();
            }
        });
    }
    private void updateSupplier() {
        String name = inputNameSupplier.getText().toString().trim();

        if (TextUtils.isEmpty(name) ) {
            Toast.makeText(StoreDetailSupplier.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        ListStoreSupplierData supplierData = new ListStoreSupplierData(supplierId, name);

        databaseReference.child(String.valueOf(supplierId)).setValue(supplierData);

        Toast.makeText(StoreDetailSupplier.this, "Supplier updated successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(StoreDetailSupplier.this, StoreInformation.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    private void confirmDeleteSupplier() {
        new AlertDialog.Builder(this)
                .setTitle("Delete Supplier")
                .setMessage("Are you sure you want to delete this Supplier?")
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
        databaseReference.child(String.valueOf(supplierId)).removeValue();

        Toast.makeText(StoreDetailSupplier.this, "Supplier deleted successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(StoreDetailSupplier.this, StoreInformation.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }
}