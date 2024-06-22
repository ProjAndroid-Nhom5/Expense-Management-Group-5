package com.example.expensemanagement.Admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.expensemanagement.R;
import com.example.expensemanagement.Home.WaitingActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {

    EditText mEmailOrPhone, mPassword;
    Button mSignInBtn;
    TextView mForgotPassword;
    LinearLayout changeToSignUp;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    ImageView showPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mSignInBtn = findViewById(R.id.signin_button);
        mEmailOrPhone = findViewById(R.id.signin_username);
        mPassword = findViewById(R.id.signin_password);
        mForgotPassword = findViewById(R.id.forgot_password);
        changeToSignUp = findViewById(R.id.changeToSignUp);
        progressBar = findViewById(R.id.signin_progressBar);
        mAuth = FirebaseAuth.getInstance();
        showPw = findViewById(R.id.sign_in_seePw);
        showPw.setImageResource(R.drawable.ic_hide_pwd);

        showPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                    mPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showPw.setImageResource(R.drawable.ic_show_pwd);
                } else {
                    mPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showPw.setImageResource(R.drawable.ic_hide_pwd);
                }
            }
        });

        mSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser();
            }
        });

        mPassword.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                signInUser();
                return true;
            }
            return false;
        });

        mForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, ForgotPassword.class));
            }
        });

        changeToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
                finish();
            }
        });

        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPassword.setError(null); // Xóa thông báo lỗi khi người dùng bắt đầu gõ
                showPw.setVisibility(View.VISIBLE); // Hiển thị lại biểu tượng mắt khi người dùng gõ vào trường mật khẩu
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        mEmailOrPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mEmailOrPhone.setError(null); // Xóa thông báo lỗi khi người dùng bắt đầu gõ
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void signInUser() {
        if (checkDataSignIn()) {
            String emailOrPhone = mEmailOrPhone.getText().toString().trim();
            String password = mPassword.getText().toString().trim();

            progressBar.setVisibility(View.VISIBLE);
            mSignInBtn.setVisibility(View.GONE);

            // Xử lý đăng nhập tài khoản
            mAuth.signInWithEmailAndPassword(emailOrPhone, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            mSignInBtn.setVisibility(View.VISIBLE);

                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();

                                if (user != null && user.isEmailVerified()) {
                                    // Email đã được xác minh
                                    Toast.makeText(SignInActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SignInActivity.this, WaitingActivity.class));
                                    finish();
                                } else {
                                    // Email chưa được xác minh
                                    if (user != null) {
                                        user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(SignInActivity.this, "Please verify your email", Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(SignInActivity.this, "Failed to send verification email", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                }
                            } else {
                                // Đăng nhập thất bại
                                if (task.getException() != null) {
                                    Toast.makeText(SignInActivity.this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(SignInActivity.this, "Authentication failed: Unknown error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
        }
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString().trim();
        // Kiểm tra địa chỉ email không rỗng, hợp lệ, có đuôi @gmail.com và phần trước @gmail.com chứa ít nhất một chữ cái
        return (!TextUtils.isEmpty(email) &&
                Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                email.toString().matches("^[A-Za-z].*@gmail\\.com$"));
    }

    boolean isPhone(String input) {
        // Kiểm tra xem input có đúng định dạng số điện thoại không
        // Đúng định dạng số điện thoại là số Việt Nam có 10 hoặc 11 chữ số và bắt đầu bằng +84 hoặc số bắt đầu bằng 0
        return input.matches("^\\+84[0-9]{9,10}$") || input.matches("^(0)[0-9]{9}$");
    }

    boolean isPasswordValid(String password) {
        // Chỉ kiểm tra xem mật khẩu có rỗng hay không
        return !TextUtils.isEmpty(password);
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString().trim();
        return TextUtils.isEmpty(str);
    }

    boolean checkDataSignIn() {
        boolean isValid = true;

        if (isEmpty(mEmailOrPhone)) {
            mEmailOrPhone.setError("Email or phone number is required.");
            isValid = false;
        } else if (!isEmail(mEmailOrPhone) && !isPhone(mEmailOrPhone.getText().toString().trim())) {
            mEmailOrPhone.setError("Enter a valid email or phone number.");
            isValid = false;
        }

        if (isEmpty(mPassword)) {
            mPassword.setError("Password is required.");
            showPw.setVisibility(View.INVISIBLE); // Ẩn biểu tượng mắt
            isValid = false;
        } else if (!isPasswordValid(mPassword.getText().toString().trim())) {
            mPassword.setError("Password must not be empty.");
            showPw.setVisibility(View.INVISIBLE); // Ẩn biểu tượng mắt
            isValid = false;
        }

        return isValid;
    }
}
