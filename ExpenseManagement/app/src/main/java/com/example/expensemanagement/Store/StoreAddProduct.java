package com.example.expensemanagement.Store;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.expensemanagement.R;
import com.example.expensemanagement.Store.Model.ListStoreProductData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StoreAddProduct extends AppCompatActivity {
    private EditText inputNameProduct, inputQuantity, inputPrice;
    private Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_add_product);

        inputNameProduct = findViewById(R.id.inputNameProduct);
        inputQuantity = findViewById(R.id.inputQuantity);
        inputPrice = findViewById(R.id.inputPrice);
        btn_save = findViewById(R.id.btn_save);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        ImageView closeIcon = findViewById(R.id.close_icon);
        closeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void saveData() {
        String NameProduct = inputNameProduct.getText().toString();
        String PriceStr = inputPrice.getText().toString();
        String QuantityStr = inputQuantity.getText().toString();

        if (NameProduct.isEmpty() || PriceStr.isEmpty() || QuantityStr.isEmpty()) {
            Toast.makeText(StoreAddProduct.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int Price, Quantity;

        try {
            Price = Integer.parseInt(PriceStr);
            Quantity = Integer.parseInt(QuantityStr);
        } catch (NumberFormatException e) {
            Toast.makeText(StoreAddProduct.this, "Price and Quantity must be numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference productsRef = FirebaseDatabase.getInstance().getReference("products");

        productsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int maxProductId = 0;
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    int currentId = Integer.parseInt(childSnapshot.getKey());
                    if (currentId > maxProductId) {
                        maxProductId = currentId;
                    }
                }
                int newProductId = maxProductId + 1;
                ListStoreProductData productData = new ListStoreProductData(NameProduct, newProductId, Quantity, Price);

                productsRef.child(String.valueOf(newProductId))
                        .setValue(productData).addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(StoreAddProduct.this, "Saved information", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }).addOnFailureListener(e -> {
                            Toast.makeText(StoreAddProduct.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StoreAddProduct.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
