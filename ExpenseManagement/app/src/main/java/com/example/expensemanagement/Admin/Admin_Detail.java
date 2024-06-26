package com.example.expensemanagement.Admin;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.expensemanagement.Admin.model.User;
import com.example.expensemanagement.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Admin_Detail extends AppCompatActivity {

    private EditText mName, mEmail, mPhone,mBirthday,mGender ;

    Button BtnUpAdmin;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;
    private ImageView left_admin;


    private String[] genders = {"Man", "Woman"};

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_detail);

        progressBar = findViewById(R.id.progressBar);

        mName = findViewById(R.id.info_text);
        mBirthday = findViewById(R.id.birthday);
        mBirthday.setFocusable(false);
        mGender = findViewById(R.id.Gender);
        mGender.setFocusable(false);

        mGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGenderOptions();
            }
        });

        mEmail = findViewById(R.id.admin_email);
        mPhone = findViewById(R.id.admin_phone);
        BtnUpAdmin = findViewById(R.id.update_admin);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser firebaseUser = mAuth.getCurrentUser();

        showProfile(firebaseUser);

        left_admin = findViewById(R.id.left_admin);
        left_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        BtnUpAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });


    }


    private void showGenderOptions() {
        new AlertDialog.Builder(this)
                .setTitle("Select Gender")
                .setItems(genders, (dialog, which) -> {
                    String selectedGender = genders[which];
                    mGender.setText(selectedGender);
                })
                .show();
    }


    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(year, monthOfYear, dayOfMonth);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        mBirthday.setText(sdf.format(calendar.getTime()));
                    }
                },
                year, month, day);

        datePickerDialog.show();
    }

    private void showProfile(FirebaseUser firebaseUser) {
        String userIdofRegistered = firebaseUser.getUid();

        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Users");
        progressBar.setVisibility(View.VISIBLE);

        referenceProfile.child(userIdofRegistered).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user != null) {
                    String name = user.getName();
                    String email = user.getEmail();
                    String phone = user.getPhone();
                    String gender = user.getGender();
                    String birthday = user.getBirthday();

                    mName.setText(name);
                    mEmail.setText(email);
                    mPhone.setText(phone);
                    mGender.setText(gender);
                    mBirthday.setText(birthday);
                } else {
                    Toast.makeText(Admin_Detail.this, "Something went wrong!", Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Admin_Detail.this, "Something went wrong!", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void updateProfile() {
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser != null) {
            String userIdofRegistered = firebaseUser.getUid();
            DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Users").child(userIdofRegistered);


            String newName = mName.getText().toString().trim();
            String newGender = mGender.getText().toString().trim();
            String newBirthday = mBirthday.getText().toString().trim();
            String newPhone = mPhone.getText().toString().trim();


            mName.setError(null);
            mGender.setError(null);
            mBirthday.setError(null);
            mPhone.setError(null);


            if (newName.isEmpty()) {
                mName.setError("Name cannot be empty");
                return;
            }


            if (!newName.matches("[a-zA-Z\\s]+")) {
                mName.setError("Name can only contain letters and spaces");
                return;
            }


            if (!newGender.equals("Man") && !newGender.equals("Woman")) {
                Toast.makeText(Admin_Detail.this, "Please select a valid gender (Man or Woman)", Toast.LENGTH_SHORT).show();
                return;
            }


            if (newBirthday.isEmpty()) {
                mBirthday.setError("Birthday cannot be empty");
                return;
            }


            if (newPhone.isEmpty()) {
                mPhone.setError("Phone number cannot be empty");
                return;
            }


            if (!isValidVietnamesePhoneNumber(newPhone)) {
                mPhone.setError("Invalid Vietnamese phone number format");
                return;
            }


            referenceProfile.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        referenceProfile.child("name").setValue(newName);
                        referenceProfile.child("gender").setValue(newGender);
                        referenceProfile.child("birthday").setValue(newBirthday);
                        referenceProfile.child("phone").setValue(newPhone);

                        Toast.makeText(Admin_Detail.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Admin_Detail.this, "User data not found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Admin_Detail.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private boolean isValidVietnamesePhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^(03|05|07|08|09)\\d{8,9}$");
    }
}
