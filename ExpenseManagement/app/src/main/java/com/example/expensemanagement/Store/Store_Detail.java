package com.example.expensemanagement.Store;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.expensemanagement.Home.HomeActivity;
import com.example.expensemanagement.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Store_Detail extends AppCompatActivity {
    private EditText inputManagerName, inputStoreName, inputImpost, inputAddress, inputDescribe, inputEmail, inputPhone;
    private Button btn_update;
    private ImageView close;
    private DatabaseReference databaseReference;
    private int StoreID = 1;
    private String StoreName, ManagerName, Address, Describe, Email, Phone;
    private float Impost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_store_detail);

        inputManagerName = findViewById(R.id.inputManagerName);
        inputStoreName = findViewById(R.id.inputStoreName);
        inputImpost = findViewById(R.id.inputImpost);
        inputAddress = findViewById(R.id.inputAddress);
        inputDescribe = findViewById(R.id.inputDescribe);
        inputEmail = findViewById(R.id.inputEmail);
        inputPhone = findViewById(R.id.inputPhone);
        btn_update = findViewById(R.id.btn_update);
        close = findViewById(R.id.close);

        databaseReference = FirebaseDatabase.getInstance().getReference("store").child(String.valueOf(StoreID));

        loadStoreDetails();

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput()) {
                    updateStoreDetails();
                }
            }
        });
    }

    private void loadStoreDetails() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    StoreName = dataSnapshot.child("StoreName").getValue(String.class);
                    ManagerName = dataSnapshot.child("ManagerName").getValue(String.class);
                    Address = dataSnapshot.child("Address").getValue(String.class);
                    Describe = dataSnapshot.child("Describe").getValue(String.class);
                    Email = dataSnapshot.child("Email").getValue(String.class);
                    Phone = dataSnapshot.child("Phone").getValue(String.class);
                    if (dataSnapshot.child("Impost").getValue() != null) {
                        Impost = dataSnapshot.child("Impost").getValue(Float.class);
                    }

                    inputStoreName.setText(StoreName);
                    inputManagerName.setText(ManagerName);
                    inputAddress.setText(Address);
                    inputDescribe.setText(Describe);
                    inputEmail.setText(Email);
                    inputPhone.setText(Phone);
                    inputImpost.setText(String.valueOf(Impost));
                } else {
                    Toast.makeText(Store_Detail.this, "Store details not found.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Store_Detail.this, "Failed to load store details.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateInput() {
        boolean isValid = true;
        ManagerName = inputManagerName.getText().toString().trim();
        StoreName = inputStoreName.getText().toString().trim();
        Address = inputAddress.getText().toString().trim();
        Describe = inputDescribe.getText().toString().trim();
        Email = inputEmail.getText().toString().trim();
        Phone = inputPhone.getText().toString().trim();

        try {
            Impost = Float.parseFloat(inputImpost.getText().toString().trim());
        } catch (NumberFormatException e) {
            inputImpost.setError("Invalid number");
            isValid = false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            inputEmail.setError("Invalid email address");
            isValid = false;
        }

        return isValid;
    }

    private void updateStoreDetails() {
        databaseReference.child("ManagerName").setValue(ManagerName);
        databaseReference.child("StoreName").setValue(StoreName);
        databaseReference.child("Address").setValue(Address);
        databaseReference.child("Describe").setValue(Describe);
        databaseReference.child("Email").setValue(Email);
        databaseReference.child("Phone").setValue(Phone);
        databaseReference.child("Impost").setValue(Impost);

        Toast.makeText(this, "Information Store updated successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Store_Detail.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
