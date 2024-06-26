package com.example.expensemanagement.Admin;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.expensemanagement.R;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Admin_Change_password extends AppCompatActivity {

    EditText currentPw, newPw, reNewPw;
    ProgressBar progressBar;
    Button BtnChangePw;
    ImageView left_Admin_Change_password, admin_see_hideCurrPw, admin_see_hideNewPw,admin_see_hideReNewPw;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_change_password);

        left_Admin_Change_password = findViewById(R.id.left_Admin_Change_password);

        currentPw = findViewById(R.id.currenPw);
        newPw = findViewById(R.id.newPw);
        reNewPw = findViewById(R.id.reNewPw);
        progressBar = findViewById(R.id.progressBar);
        BtnChangePw = findViewById(R.id.adminChangePw);
        mAuth = FirebaseAuth.getInstance();

        admin_see_hideCurrPw = findViewById(R.id.admin_see_hideCurrPw);
        admin_see_hideNewPw = findViewById(R.id.admin_see_hideNewPw);
        admin_see_hideReNewPw = findViewById(R.id.admin_see_hideReNewPw);

        admin_see_hideCurrPw.setImageResource(R.drawable.ic_hide_pwd);
        admin_see_hideNewPw.setImageResource(R.drawable.ic_hide_pwd);
        admin_see_hideReNewPw.setImageResource(R.drawable.ic_hide_pwd);

        admin_see_hideCurrPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPw.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                    currentPw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    admin_see_hideCurrPw.setImageResource(R.drawable.ic_show_pwd);
                } else {
                    currentPw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    admin_see_hideCurrPw.setImageResource(R.drawable.ic_hide_pwd);
                }
            }
        });

        admin_see_hideNewPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newPw.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                    newPw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    admin_see_hideNewPw.setImageResource(R.drawable.ic_show_pwd);
                } else {
                    newPw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    admin_see_hideNewPw.setImageResource(R.drawable.ic_hide_pwd);
                }
            }
        });

        admin_see_hideReNewPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reNewPw.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                    reNewPw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    admin_see_hideReNewPw.setImageResource(R.drawable.ic_show_pwd);
                } else {
                    reNewPw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    admin_see_hideReNewPw.setImageResource(R.drawable.ic_hide_pwd);
                }
            }
        });


        currentPw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                currentPw.setError(null);
                admin_see_hideCurrPw.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        newPw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                newPw.setError(null);
                admin_see_hideNewPw.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        reNewPw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                reNewPw.setError(null);
                admin_see_hideReNewPw.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        left_Admin_Change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        BtnChangePw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });
    }
    private boolean isPasswordValid(String password) {
        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasSpecialChar = !password.matches("[A-Za-z0-9 ]*");

        return hasUppercase && hasSpecialChar;
    }
    private void changePassword() {
        String currentPassword = currentPw.getText().toString().trim();
        String newPassword = newPw.getText().toString().trim();
        String reNewPassword = reNewPw.getText().toString().trim();

        if (TextUtils.isEmpty(currentPassword)) {
            currentPw.setError("Current password is required");
            admin_see_hideCurrPw.setVisibility(View.INVISIBLE);
            return;
        }

        if (TextUtils.isEmpty(newPassword)) {
            newPw.setError("New password is required");
            admin_see_hideNewPw.setVisibility(View.INVISIBLE);
            return;
        }

        if (TextUtils.isEmpty(reNewPassword)) {
            reNewPw.setError("Please re-enter new password");
            admin_see_hideReNewPw.setVisibility(View.INVISIBLE);
            return;
        }

        if (!isPasswordValid(newPassword)) {
            newPw.setError("Password must contain at least one uppercase letter and one special character");
            admin_see_hideNewPw.setVisibility(View.INVISIBLE);
            return;
        }

        if (!newPassword.equals(reNewPassword)) {
            reNewPw.setError("Passwords do not match");
            admin_see_hideReNewPw.setVisibility(View.GONE);
            return;
        }


        progressBar.setVisibility(View.VISIBLE);

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null && user.getEmail() != null) {
            AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), currentPassword);
            user.reauthenticate(credential).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    user.updatePassword(newPassword).addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            Toast.makeText(Admin_Change_password.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        } else {
                            Toast.makeText(Admin_Change_password.this, "Error: " + task1.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                } else {
                    Toast.makeText(Admin_Change_password.this, "Re-authentication failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            });
        } else {
            Toast.makeText(Admin_Change_password.this, "User not logged in", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
        }
    }
}
