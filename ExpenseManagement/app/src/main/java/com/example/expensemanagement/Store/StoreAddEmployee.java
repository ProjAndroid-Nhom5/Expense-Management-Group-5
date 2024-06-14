package com.example.expensemanagement.Store;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.expensemanagement.R;
import com.example.expensemanagement.Store.Model.ListStoreEmployeeData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class StoreAddEmployee extends AppCompatActivity {

    EditText inputNameEmployee, inputEmail, inputPhone, inputAddress, inputBaseSalary, inputBonusSalary;
    TextView inputDayOfWork;
    Spinner inputPosition;
    Button btn_save;
    private Date dayOfWorkDate;
    private String selectedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_add_employee);

        ImageView closeIcon = findViewById(R.id.close_icon);
        closeIcon.setOnClickListener(v -> onBackPressed());

        inputNameEmployee = findViewById(R.id.inputNameEmployee);
        inputEmail = findViewById(R.id.inputEmail);
        inputPhone = findViewById(R.id.inputPhone);
        inputAddress = findViewById(R.id.inputAddress);
        inputBaseSalary = findViewById(R.id.inputBaseSalary);
        inputBonusSalary = findViewById(R.id.inputBonusSalary);
        inputDayOfWork = findViewById(R.id.inputDayOfWork);
        inputPosition = findViewById(R.id.inputPosition);
        btn_save = findViewById(R.id.btn_save);

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


        inputDayOfWork.setOnClickListener(v -> showDatePickerDialog());

        btn_save.setOnClickListener(v -> saveData());
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                StoreAddEmployee.this,
                (view, year1, monthOfYear, dayOfMonth) -> {
                    Calendar selectedDate = Calendar.getInstance();
                    selectedDate.set(year1, monthOfYear, dayOfMonth);
                    dayOfWorkDate = selectedDate.getTime();

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    inputDayOfWork.setText(sdf.format(dayOfWorkDate));
                },
                year, month, day);
        datePickerDialog.show();
    }

    public void saveData() {
        String selectedPosition = inputPosition.getSelectedItem().toString();
        String NameEmployee = inputNameEmployee.getText().toString();
        String Email = inputEmail.getText().toString();
        String Phone = inputPhone.getText().toString();
        String Address = inputAddress.getText().toString();
        String BaseSalarytr = inputBaseSalary.getText().toString();
        String BonusSalarytr = inputBonusSalary.getText().toString();

        if (NameEmployee.isEmpty() || Email.isEmpty() || Phone.isEmpty() || Address.isEmpty() ||
                BaseSalarytr.isEmpty() || BonusSalarytr.isEmpty() || dayOfWorkDate == null || selectedPosition.isEmpty()) {
            Toast.makeText(StoreAddEmployee.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            Toast.makeText(StoreAddEmployee.this, "Invalid email format", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Patterns.PHONE.matcher(Phone).matches()) {
            Toast.makeText(StoreAddEmployee.this, "Invalid phone number format", Toast.LENGTH_SHORT).show();
            return;
        }
        int BaseSalary, BonusSalary;

        try {
            BaseSalary = Integer.parseInt(BaseSalarytr);
            BonusSalary = Integer.parseInt(BonusSalarytr);

        } catch (NumberFormatException e) {
            Toast.makeText(StoreAddEmployee.this, "BaseSalary and BonusSalary Must be numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference employeesRef = FirebaseDatabase.getInstance().getReference("employees");

        employeesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int maxEmployeeId = 0;
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    int currentId = Integer.parseInt(childSnapshot.getKey());
                    if (currentId > maxEmployeeId) {
                        maxEmployeeId = currentId;
                    }
                }
                int newEmployeeId = maxEmployeeId + 1;
                ListStoreEmployeeData employeeData = new ListStoreEmployeeData(newEmployeeId, NameEmployee, Email, Phone, Address, selectedPosition, dayOfWorkDate, BaseSalary, BonusSalary);

                employeesRef.child(String.valueOf(newEmployeeId))
                        .setValue(employeeData).addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(StoreAddEmployee.this, "Saved information", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }).addOnFailureListener(e -> {
                            Toast.makeText(StoreAddEmployee.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StoreAddEmployee.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
