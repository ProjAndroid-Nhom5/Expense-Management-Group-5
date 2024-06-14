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

import com.example.expensemanagement.R;
import com.example.expensemanagement.Store.Model.ListStoreEcommerceData;
import com.example.expensemanagement.Store.Model.ListStoreSupplierData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StoreAddSupplier extends AppCompatActivity {
    EditText inputNameSupplier;
    Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_store_add_supplier);
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
        inputNameSupplier = findViewById(R.id.inputNameSupplier);

        btn_save = findViewById(R.id.btn_save);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }
    public void saveData(){
        String NameSupplier = inputNameSupplier.getText().toString();

        if (NameSupplier.isEmpty()) {
            Toast.makeText(StoreAddSupplier.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }


        DatabaseReference suppliersRef = FirebaseDatabase.getInstance().getReference("suppliers");

        suppliersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int maxSupplierId = 0;
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    int currentId = Integer.parseInt(childSnapshot.getKey());
                    if (currentId > maxSupplierId) {
                        maxSupplierId = currentId;
                    }
                }
                int newSupplierId = maxSupplierId + 1;
                ListStoreSupplierData supplierData = new ListStoreSupplierData(newSupplierId, NameSupplier);

                suppliersRef.child(String.valueOf(newSupplierId))
                        .setValue(supplierData).addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(StoreAddSupplier.this, "Saved information", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }).addOnFailureListener(e -> {
                            Toast.makeText(StoreAddSupplier.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StoreAddSupplier.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}