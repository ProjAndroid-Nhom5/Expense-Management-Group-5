package com.example.expensemanagement.Admin;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.expensemanagement.Admin.model.User;
import com.example.expensemanagement.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    EditText signupName, signupEmail, signupPassword, signupConfirmPassword, signup_phone, signupBirthdate;;

    // Khai báo các thành phần trong XML
    TextView snGender;
    RadioGroup radioGroupGender;
    RadioButton radioMan, radioWomen;
    ImageView showPw, showCfPw;
    LinearLayout loginRedirectText;
    ProgressBar progressBar;
    Button signupButton;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    @SuppressLint("MissingInflatedId")
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
        signup_phone = findViewById(R.id.signup_phone);
        signupBirthdate = findViewById(R.id.signup_birthdate);
        snGender = findViewById(R.id.snGender);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioMan = findViewById(R.id.radioMan);
        radioWomen = findViewById(R.id.radioWomen);
        loginRedirectText = findViewById(R.id.loginRedirectText);

        progressBar = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");


        showPw = findViewById(R.id.sign_up_seePw);
        showCfPw = findViewById(R.id.sign_up_seeCfPw);

        showPw.setImageResource(R.drawable.ic_hide_pwd);
        showCfPw.setImageResource(R.drawable.ic_hide_pwd);
        showPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (signupPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                    signupPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showPw.setImageResource(R.drawable.ic_show_pwd);
                } else {
                    signupPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showPw.setImageResource(R.drawable.ic_hide_pwd);
                }
            }
        });

        showCfPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (signupConfirmPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                    signupConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showCfPw.setImageResource(R.drawable.ic_show_pwd);
                } else {
                    signupConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showCfPw.setImageResource(R.drawable.ic_hide_pwd);
                }
            }
        });
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                finish();
            }
        });

        signupBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

            // Thiết lập sự kiện khi người dùng chọn nút radio
            radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    // Kiểm tra xem nút radio nào được chọn
                    switch (checkedId) {
                        case R.id.radioMan:
                            // Xử lý khi chọn nút Man
                            String selectedMan = radioMan.getText().toString();
                            // TODO: Đoạn xử lý khi chọn Man
                            break;
                        case R.id.radioWomen:
                            // Xử lý khi chọn nút Women
                            String selectedWomen = radioWomen.getText().toString();
                            // TODO: Đoạn xử lý khi chọn Women
                            break;
                        default:
                            break;
                    }
                }
            });


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDataEntered()) {
                    final String name = signupName.getText().toString().trim();
                    final String email = signupEmail.getText().toString().trim();
                    final String phone = signup_phone.getText().toString().trim();
                    final String password = signupPassword.getText().toString().trim();
                    final String gender; // Khởi tạo role ban đầu
                    final String birthday; // Khởi tạo birthday ban đầu

                    // Lấy giá trị từ radio button
                    int selectedRoleId = radioGroupGender.getCheckedRadioButtonId();
                    if (selectedRoleId == R.id.radioMan) {
                        gender = "Man";
                    } else if (selectedRoleId == R.id.radioWomen) {
                        gender = "Women";
                    } else {
                        gender = ""; // Handle default case if needed
                    }

                    // Lấy giá trị từ EditText birthday
                    birthday = signupBirthdate.getText().toString().trim();

                    progressBar.setVisibility(View.VISIBLE);
                    signupButton.setVisibility(View.GONE);

                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            signupButton.setVisibility(View.VISIBLE);

                            if (task.isSuccessful()) {
                                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                if (firebaseUser != null) {
                                    User user = new User(name, email, phone, gender, birthday);

                                    databaseReference.child(firebaseUser.getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(SignUpActivity.this, "User Created.", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                                                finish();
                                            } else {
                                                Toast.makeText(SignUpActivity.this, "Database Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(SignUpActivity.this, "Failed to get current user.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(SignUpActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });





        // Thêm TextWatcher cho signupPassword và signupConfirmPassword
        signupPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                signupPassword.setError(null); // Xóa thông báo lỗi khi người dùng bắt đầu gõ
                showPw.setVisibility(View.VISIBLE); // Hiển thị lại biểu tượng mắt khi người dùng gõ vào trường mật khẩu
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        signupConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                signupConfirmPassword.setError(null); // Xóa thông báo lỗi khi người dùng bắt đầu gõ
                showCfPw.setVisibility(View.VISIBLE); // Hiển thị lại biểu tượng mắt khi người dùng gõ vào trường xác nhận mật khẩu
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Set selected date to EditText
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        signupBirthdate.setText(selectedDate);
                    }
                },
                year, month, dayOfMonth);

        datePickerDialog.show();
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        // Kiểm tra địa chỉ email không rỗng, hợp lệ, có đuôi @gmail.com và phần trước @gmail.com chứa ít nhất một chữ cái
        return (!TextUtils.isEmpty(email) &&
                Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                email.toString().matches("^[A-Za-z].*@gmail\\.com$"));
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean isPasswordValid(String password) {
        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasSpecialChar = !password.matches("[A-Za-z0-9 ]*");

        return hasUppercase && hasSpecialChar;
    }

    boolean isValidPhoneNumber(EditText text) {
        CharSequence phone = text.getText().toString().trim();
        // Sử dụng biểu thức chính quy để kiểm tra số điện thoại Việt Nam
        String regex = "^(03|05|07|08|09)\\d{8}$";
        return !TextUtils.isEmpty(phone) && Pattern.compile(regex).matcher(phone).matches();
    }

    boolean checkDataEntered() {
        boolean check = true;
        if (isEmpty(signupName)) {
            signupName.setError("Name is Empty!");
            check = false;
        }

        if (!isEmail(signupEmail)) {
            signupEmail.setError("Enter valid Email!");
            check = false;
        }

        String password = signupPassword.getText().toString();

        if (isEmpty(signupPassword)) {
            signupPassword.setError("Password is Empty!");
            showPw.setVisibility(View.INVISIBLE);
            check = false;
        } else if (!isPasswordValid(password)) {
            signupPassword.setError("Password must have at least one uppercase letter and one special character.");
            showPw.setVisibility(View.INVISIBLE);
            check = false;
        }

        if (isEmpty(signupConfirmPassword)) {
            signupConfirmPassword.setError("Enter Password Again!");
            showCfPw.setVisibility(View.INVISIBLE);
            check = false;
        } else if (!signupPassword.getText().toString().equals(signupConfirmPassword.getText().toString())) {
            signupConfirmPassword.setError("Password must be same!");
            showCfPw.setVisibility(View.INVISIBLE);
            check = false;
        }

        if (!isValidPhoneNumber(signup_phone)) {
            signup_phone.setError("Enter valid Vietnam phone number!");
            check = false;
        }

        // Kiểm tra xem role đã được chọn hay chưa
        int selectedRoleId = radioGroupGender.getCheckedRadioButtonId();
        if (selectedRoleId == -1) { // Không có nút radio nào được chọn
            Toast.makeText(SignUpActivity.this, "Please select a Role!", Toast.LENGTH_SHORT).show();
            check = false;
        }

        // Kiểm tra xem birthday đã được chọn hay chưa
        if (TextUtils.isEmpty(signupBirthdate.getText().toString().trim())) {
            signupBirthdate.setError("Select Birthdate!");
            check = false;
        }

        return check;
    }
}
