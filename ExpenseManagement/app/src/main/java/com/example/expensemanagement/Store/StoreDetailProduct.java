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
import com.example.expensemanagement.Store.Model.ListStoreProductData;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StoreDetailProduct extends AppCompatActivity {
    private EditText inputNameProduct, inputQuantity, inputPrice;
    private Button btn_update, btn_delete;
    private DatabaseReference databaseReference;
    private int productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_store_detail_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inputNameProduct = findViewById(R.id.inputNameProduct);
        inputQuantity = findViewById(R.id.inputQuantity);
        inputPrice = findViewById(R.id.inputPrice);

        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        productId = intent.getIntExtra("productId", -1);

        if (productId == -1) {
        } else {
            String name = intent.getStringExtra("inputNameProduct");
            int quantity = intent.getIntExtra("inputQuantity", 0);
            int price = intent.getIntExtra("inputPrice", 0);
            inputNameProduct.setText(name);
            inputQuantity.setText(String.valueOf(quantity));
            inputPrice.setText(String.valueOf(price));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("products");

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (productId != -1) {
                    updateProduct();
                }
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDeleteProduct();
            }
        });

        ImageView closeIcon = findViewById(R.id.close_icon);
        closeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoreDetailProduct.this, StoreInformation.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    private void updateProduct() {
        String name = inputNameProduct.getText().toString().trim();
        String quantityStr = inputQuantity.getText().toString().trim();
        String priceStr = inputPrice.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(quantityStr) || TextUtils.isEmpty(priceStr)) {
            Toast.makeText(StoreDetailProduct.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int quantity;
        int price;
        try {
            quantity = Integer.parseInt(quantityStr);
            price = Integer.parseInt(priceStr);
        } catch (NumberFormatException e) {
            Toast.makeText(StoreDetailProduct.this, "Price and Quantity must be numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        ListStoreProductData productData = new ListStoreProductData(name, productId, quantity, price);

        databaseReference.child(String.valueOf(productId)).setValue(productData);

        Toast.makeText(StoreDetailProduct.this, "Product updated successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(StoreDetailProduct.this, StoreInformation.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    private void confirmDeleteProduct() {
        new AlertDialog.Builder(this)
                .setTitle("Delete Product")
                .setMessage("Are you sure you want to delete this Product?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        deleteProduct();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void deleteProduct() {
        databaseReference.child(String.valueOf(productId)).removeValue();

        Toast.makeText(StoreDetailProduct.this, "Product deleted successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(StoreDetailProduct.this, StoreInformation.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

}
