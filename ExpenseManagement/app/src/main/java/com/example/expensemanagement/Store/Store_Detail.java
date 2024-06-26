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

import com.example.expensemanagement.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Store_Detail extends AppCompatActivity {
    private EditText inputManagerName, inputStoreName, inputImpost, inputAddress, inputDescribe, inputEmail, inputPhone;
    private Button btn_update;
    private ImageView close;
    private DatabaseReference storeReference;
    private DatabaseReference userReference;
    private String userId;
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

        inputManagerName.setEnabled(false);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            userId = user.getUid();
            userReference = FirebaseDatabase.getInstance().getReference("Users").child(userId);
            storeReference = FirebaseDatabase.getInstance().getReference("store").child(userId);
            loadUserProfile();
            loadStoreDetails();
        } else {
            Toast.makeText(this, "User not logged in.", Toast.LENGTH_SHORT).show();
        }

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

    private void loadUserProfile() {
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String name = snapshot.child("name").getValue(String.class);
                    inputManagerName.setText(name);
                } else {
                    Toast.makeText(Store_Detail.this, "User profile not found.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(Store_Detail.this, "Error loading user information", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadStoreDetails() {
        storeReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    StoreName = dataSnapshot.child("StoreName").getValue(String.class);
                    Address = dataSnapshot.child("Address").getValue(String.class);
                    Describe = dataSnapshot.child("Describe").getValue(String.class);
                    Email = dataSnapshot.child("Email").getValue(String.class);
                    Phone = dataSnapshot.child("Phone").getValue(String.class);
                    if (dataSnapshot.child("Impost").getValue() != null) {
                        Impost = dataSnapshot.child("Impost").getValue(Float.class);
                    }

                    inputStoreName.setText(StoreName);
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
        storeReference.child("StoreName").setValue(StoreName);
        storeReference.child("Address").setValue(Address);
        storeReference.child("Describe").setValue(Describe);
        storeReference.child("Email").setValue(Email);
        storeReference.child("Phone").setValue(Phone);
        storeReference.child("Impost").setValue(Impost);

        Toast.makeText(this, "Information Store updated successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Store_Detail.this, Store_Detail.class);
        startActivity(intent);
        finish();
    }
}
