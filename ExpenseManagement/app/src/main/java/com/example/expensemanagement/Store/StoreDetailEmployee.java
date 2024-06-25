package com.example.expensemanagement.Store;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.expensemanagement.R;
import com.example.expensemanagement.Store.Model.ListStoreEmployeeData;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.GregorianCalendar;

public class StoreDetailEmployee extends AppCompatActivity {
    EditText inputNameEmployee, inputEmail, inputPhone, inputAddress, inputBaseSalary, inputBonusSalary;
    TextView inputDayOfWork;
    Spinner inputPosition;

    Button btn_update, btn_delete;
    private DatabaseReference databaseReference;
    private int employeeId;
    private Date dayOfWorkDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_store_detail_employee);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView closeIcon = findViewById(R.id.close_icon);
        closeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoreDetailEmployee.this, StoreInformation.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        inputNameEmployee = findViewById(R.id.inputNameEmployee);
        inputEmail = findViewById(R.id.inputEmail);
        inputPhone = findViewById(R.id.inputPhone);
        inputAddress = findViewById(R.id.inputAddress);
        inputBaseSalary = findViewById(R.id.inputBaseSalary);
        inputBonusSalary = findViewById(R.id.inputBonusSalary);
        inputDayOfWork = findViewById(R.id.inputDayOfWork);
        inputPosition = findViewById(R.id.inputPosition);

        Spinner inputPosition = findViewById(R.id.inputPosition);

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_item, R.id.spinner_item_text, getResources().getStringArray(R.array.positions_array)) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView textView = view.findViewById(R.id.spinner_item_text);
                textView.setTextColor(getResources().getColor(R.color.darkblue));
                textView.setTextSize(15);
                return view;
            }
        };
        inputPosition.setAdapter(adapter);

        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        employeeId = intent.getIntExtra("employeeId", -1);

        if (employeeId == -1) {
        } else {
            String name = intent.getStringExtra("inputNameEmployee");
            String email = intent.getStringExtra("inputEmail");
            String phone = intent.getStringExtra("inputPhone");
            String address = intent.getStringExtra("inputAddress");
            int baseSalary = intent.getIntExtra("inputBaseSalary", 0);
            int bonusSalary = intent.getIntExtra("inputBonusSalary", 0);
            long dayOfWorkMillis = intent.getLongExtra("inputDayOfWork", 0);
            String position = intent.getStringExtra("inputPosition");

            inputNameEmployee.setText(name);
            inputEmail.setText(email);
            inputPhone.setText(phone);
            inputAddress.setText(address);
            inputBaseSalary.setText(String.valueOf(baseSalary));
            inputBonusSalary.setText(String.valueOf(bonusSalary));
            dayOfWorkDate = new Date(dayOfWorkMillis);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            inputDayOfWork.setText(sdf.format(dayOfWorkDate));

            if (position != null) {
                int spinnerPosition = adapter.getPosition(position);
                inputPosition.setSelection(spinnerPosition);
            }
        }
        inputDayOfWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference("employees");

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (employeeId != -1) {
                    updateEmployee();
                }
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDeleteEmployee();
            }
        });
    }
    public void showDatePickerDialog(View view) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        inputDayOfWork.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        dayOfWorkDate = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();
                    }
                }, year, month, day);
        datePickerDialog.show();
    }
    private void updateEmployee() {
        String name = inputNameEmployee.getText().toString().trim();
        String email = inputEmail.getText().toString().trim();
        String phone = inputPhone.getText().toString().trim();
        String address = inputAddress.getText().toString().trim();
        String baseSalaryStr = inputBaseSalary.getText().toString().trim();
        String bonusSalaryStr = inputBonusSalary.getText().toString().trim();
        String dayOfWorkStr = inputDayOfWork.getText().toString().trim();
        String position = inputPosition.getSelectedItem().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phone) ||
                TextUtils.isEmpty(address) || TextUtils.isEmpty(baseSalaryStr) || TextUtils.isEmpty(bonusSalaryStr) ||
                TextUtils.isEmpty(dayOfWorkStr) || TextUtils.isEmpty(position)) {
            Toast.makeText(StoreDetailEmployee.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(StoreDetailEmployee.this, "Invalid email format", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Patterns.PHONE.matcher(phone).matches()) {
            Toast.makeText(StoreDetailEmployee.this, "Invalid phone number format", Toast.LENGTH_SHORT).show();
            return;
        }
        int baseSalary, bonusSalary;

        try {
            baseSalary = Integer.parseInt(baseSalaryStr);
            bonusSalary = Integer.parseInt(bonusSalaryStr);
        } catch (NumberFormatException e) {
            Toast.makeText(StoreDetailEmployee.this, "BaseSalary and BonusSalary Must be numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        ListStoreEmployeeData employeeData = new ListStoreEmployeeData(employeeId, name, email, phone, address, position, dayOfWorkDate, baseSalary, bonusSalary);

        databaseReference.child(String.valueOf(employeeId)).setValue(employeeData);

        Toast.makeText(StoreDetailEmployee.this, "Employee updated successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(StoreDetailEmployee.this, StoreInformation.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    private void confirmDeleteEmployee() {
        new AlertDialog.Builder(this)
                .setTitle("Delete Employee")
                .setMessage("Are you sure you want to delete this employee?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        deleteEmployee();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void deleteEmployee() {
        databaseReference.child(String.valueOf(employeeId)).removeValue();

        Toast.makeText(StoreDetailEmployee.this, "Employee deleted successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(StoreDetailEmployee.this, StoreInformation.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }
}
