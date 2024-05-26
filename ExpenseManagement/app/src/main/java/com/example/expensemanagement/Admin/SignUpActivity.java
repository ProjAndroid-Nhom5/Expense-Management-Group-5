package com.example.expensemanagement.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.expensemanagement.R;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    final String TAG = SignUpActivity.class.getName();
    EditText signupName, signupPhone, signupEmail, signupPassword, signupConfirmPassword;
    LinearLayout loginRedirectText;
    ProgressBar progressBar;
    Button signupButton;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupConfirmPassword = findViewById(R.id.signup_confirm_password);
        signupButton = findViewById(R.id.signup_button);
        progressBar = findViewById(R.id.progressBar);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDataEntered()){
                    String name = signupName.getText().toString();
                    String email = signupEmail.getText().toString();
                    String password = signupPassword.getText().toString();

                    progressBar.setVisibility(View.VISIBLE);
                    signupButton.setVisibility(View.GONE);

                    // code lưu tài khoản lên firebase ở đây

                    Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean checkDataEntered() {
        boolean check = true;
        if (isEmpty(signupName)) {
            signupName.setError("Name is Empty!");
            check = false;
        }

        if (isEmpty(signupPassword)) {
            signupPassword.setError("Password is Empty!");
            check = false;
        }

        if (isEmail(signupEmail) == false) {
            signupEmail.setError("Enter valid Email!");
            check = false;
        }

        if (isEmpty(signupConfirmPassword)){
            signupConfirmPassword.setError("Enter Password Again!");
            check = false;
        } else if (!signupPassword.getText().toString().equals(signupConfirmPassword.getText().toString())){
            signupConfirmPassword.setError("Password must be same!");
            check = false;
        }

        return check;
    }
}