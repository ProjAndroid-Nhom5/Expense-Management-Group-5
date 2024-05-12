package com.example.nhom5;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.security.Key;
import java.util.concurrent.TimeUnit;

import kotlin.random.Random;

public class OTPVerify extends AppCompatActivity {
    final String TAG = SignupActivity.class.getName();
    EditText otpEt1,otpEt2,otpEt3,otpEt4,otpEt5,otpEt6;
    TextView resentBtn, otpPhone;
    ProgressBar progressBar;
    Button verifyBtn;
    private boolean resendEnable = false;
    private int resentTime = 60;
    private int selectETPosition = 0;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String getPhone, getPass, getCode;
    PhoneAuthProvider.ForceResendingToken mforceResendingToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_otp_verify);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        otpEt1 = findViewById(R.id.otpET1);
        otpEt2 = findViewById(R.id.otpET2);
        otpEt3 = findViewById(R.id.otpET3);
        otpEt4 = findViewById(R.id.otpET4);
        otpEt5 = findViewById(R.id.otpET5);
        otpEt6 = findViewById(R.id.otpET6);

        progressBar = findViewById(R.id.progressBar);
        resentBtn = findViewById(R.id.resendBtn);
        verifyBtn = findViewById(R.id.verify_button);
        otpPhone = findViewById(R.id.otpPhone);

        getPhone = getIntent().getStringExtra("phone");
        getPass = getIntent().getStringExtra("pass");
        getCode = getIntent().getStringExtra("code");

        otpPhone.setText("+84 - " + getPhone);

        otpEt1.addTextChangedListener(textWatcher);
        otpEt2.addTextChangedListener(textWatcher);
        otpEt3.addTextChangedListener(textWatcher);
        otpEt4.addTextChangedListener(textWatcher);
        otpEt5.addTextChangedListener(textWatcher);
        otpEt6.addTextChangedListener(textWatcher);

        showKeyboard(otpEt1);

        startCoutDownTimer();

        resentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (resendEnable){
                    startCoutDownTimer();
                    PhoneAuthOptions options =
                            PhoneAuthOptions.newBuilder(mAuth)
                                    .setPhoneNumber("+84"+ getPhone)       // Phone number to verify
                                    .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                                    .setActivity(OTPVerify.this)                 // (optional) Activity for callback binding
                                    // If no activity is passed, reCAPTCHA verification can not be used.
                                    .setForceResendingToken(mforceResendingToken)
                                    .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                        @Override
                                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                            progressBar.setVisibility(View.GONE);
                                            verifyBtn.setVisibility(View.VISIBLE);
                                            Log.d(TAG, "onVerificationCompleted:" + phoneAuthCredential);
                                            signInWithPhoneAuthCredential(phoneAuthCredential);
                                        }

                                        @Override
                                        public void onVerificationFailed(@NonNull FirebaseException e) {
                                            progressBar.setVisibility(View.GONE);
                                            verifyBtn.setVisibility(View.VISIBLE);
                                            Toast.makeText(OTPVerify.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                            super.onCodeSent(verificationId,forceResendingToken);
                                            progressBar.setVisibility(View.GONE);
                                            verifyBtn.setVisibility(View.VISIBLE);
                                            getCode = verificationId;
                                            mforceResendingToken = forceResendingToken;
                                        }
                                    })          // OnVerificationStateChangedCallbacks
                                    .build();
                    PhoneAuthProvider.verifyPhoneNumber(options);
                }
            }
        });

        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                verifyBtn.setVisibility(View.GONE);

                final String generateOtp = otpEt1.getText().toString()
                        +otpEt2.getText().toString()
                        +otpEt3.getText().toString()
                        +otpEt4.getText().toString()
                        +otpEt5.getText().toString()
                        +otpEt6.getText().toString();
                if (generateOtp.length() == 6){
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(getCode,generateOtp);
                    signInWithPhoneAuthCredential(credential);
                }else {
                    Toast.makeText(OTPVerify.this,"Enter OTP",Toast.LENGTH_SHORT);
                }
            }
        });
    }

    private void showKeyboard(EditText otpEt){
        otpEt.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(otpEt,InputMethodManager.SHOW_IMPLICIT);
    }

    private void startCoutDownTimer(){
        resendEnable = false;
        resentBtn.setTextColor(Color.parseColor("#99000000"));

        new CountDownTimer(resentTime * 1000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                resentBtn.setText("Resend Code ("+(millisUntilFinished/1000)+")");
            }

            @Override
            public void onFinish() {
                resendEnable = true;
                resentBtn.setText("Resend Code");
                resentBtn.setTextColor(getResources().getColor(R.color.lavender));
            }
        }.start();
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length()>0){
                if (selectETPosition == 0){
                    selectETPosition = 1;
                    showKeyboard(otpEt2);
                } else if (selectETPosition == 1) {
                    selectETPosition = 2;
                    showKeyboard(otpEt3);
                } else if (selectETPosition == 2) {
                    selectETPosition = 3;
                    showKeyboard(otpEt4);
                } else if (selectETPosition == 3) {
                    selectETPosition = 4;
                    showKeyboard(otpEt5);
                } else if (selectETPosition == 4) {
                    selectETPosition = 5;
                    showKeyboard(otpEt6);
                }
            }
        }
    };

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_DEL){
            if (selectETPosition == 5){
                selectETPosition = 4;
                showKeyboard(otpEt5);
            } else if (selectETPosition == 4){
                selectETPosition = 3;
                showKeyboard(otpEt4);
            } else if (selectETPosition == 3){
                selectETPosition = 2;
                showKeyboard(otpEt3);
            } else if (selectETPosition == 2) {
                selectETPosition = 1;
                showKeyboard(otpEt2);
            } else if (selectETPosition == 1) {
                selectETPosition = 0;
                showKeyboard(otpEt1);
            }
            return true;
        }else {
            return super.onKeyUp(keyCode, event);
        }
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // Update UI
                            Intent intent = new Intent(OTPVerify.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                progressBar.setVisibility(View.GONE);
                                verifyBtn.setVisibility(View.VISIBLE);
                                Toast.makeText(OTPVerify.this,
                                        "The verification code entered was invalid", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
}