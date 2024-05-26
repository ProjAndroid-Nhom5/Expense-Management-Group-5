package com.example.expensemanagement.Store;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.AdapterView;
import android.widget.ImageView;
import android.view.View;
import android.content.Intent;
import android.widget.ListView;

import com.example.expensemanagement.R;
import com.example.expensemanagement.Store.Adapter.ListStoreEcommerceAdapter;
import com.example.expensemanagement.Store.Adapter.ListStoreEmployeeAdapter;
import com.example.expensemanagement.Store.Adapter.ListStoreProductAdapter;
import com.example.expensemanagement.Store.Adapter.ListStoreSupplierAdapter;
import com.example.expensemanagement.Store.Model.ListStoreEcommerceData;
import com.example.expensemanagement.Store.Model.ListStoreEmployeeData;
import com.example.expensemanagement.Store.Model.ListStoreProductData;
import com.example.expensemanagement.Store.Model.ListStoreSupplierData;

import java.util.ArrayList;

public class StoreInformation extends AppCompatActivity {

    //Ecommerce
    ListStoreEcommerceAdapter listStoreEcommerceAdapter;
    ArrayList<ListStoreEcommerceData> listStoreEcommerceDataArrayList = new ArrayList<>();
    ListStoreEcommerceData listStoreEcommerceData;

    //Employee
    ListStoreEmployeeAdapter listStoreEmployeeAdapter;
    ArrayList<ListStoreEmployeeData> listStoreEmployeeDataArrayList = new ArrayList<>();
    ListStoreEmployeeData listStoreEmployeeData;

    //Product
    ListStoreProductAdapter listStoreProductAdapter;
    ArrayList<ListStoreProductData> listStoreProductDataArrayList = new ArrayList<>();
    ListStoreProductData listStoreProductData;

    //Supplier
    ListStoreSupplierAdapter listStoreSupplierAdapter;
    ArrayList<ListStoreSupplierData> listStoreSupplierDataArrayList = new ArrayList<>();
    ListStoreSupplierData listStoreSupplierData;
    ListView listView;
    ImageView header_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_store_information);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);

        listView = findViewById(R.id.listview);
        header_image = findViewById(R.id.header_image);

        if (position == 1) {
            header_image.setImageResource(R.drawable.ec2);

            Integer[] EcommerceID = {0, 1, 2, 3};
            String[] nameEcommerce = {"Pasta", "Maggi", "Pasta", "Maggi"};
            String[] nameStore = {"Lazada", "Shopee", "Amazon", "Tiki"};
            Integer[] PaymentFee = {0, 0 ,0, 0};
            Integer[] FixedFee = {0, 0 ,0, 0};
            Integer[] ServiceFee = {0, 0 ,0, 0};
            Float[] FreightSurcharge = {0.0f, 0.0f ,0.0f, 0.0f};
            Float[] PersonalIncomeTaxVAT = {0.0f, 0.0f ,0.0f, 0.0f};
            Integer[] Receivables = {0, 0 ,0, 0};

            ArrayList<ListStoreEcommerceData> dataArrayList = new ArrayList<>();
            ListStoreEcommerceData listData;

            for (int i = 0; i < EcommerceID.length; i++) {
                listData = new ListStoreEcommerceData(
                        EcommerceID[i],
                        PaymentFee[i],
                        FixedFee[i],
                        ServiceFee[i],
                        Receivables[i],
                        nameStore[i],
                        nameEcommerce[i],
                        FreightSurcharge[i],
                        PersonalIncomeTaxVAT[i]
                );
                dataArrayList.add(listData);
            }

            listStoreEcommerceAdapter = new ListStoreEcommerceAdapter(StoreInformation.this, dataArrayList);
            listView.setAdapter(listStoreEcommerceAdapter);
            listView.setClickable(true);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ListStoreEcommerceData selectedItem = (ListStoreEcommerceData) parent.getItemAtPosition(position);

                    Intent intent = new Intent(StoreInformation.this, StoreDetailEcommerce.class);
                    intent.putExtra("ecommerceName", selectedItem.getEcommerceName());
                    intent.putExtra("storeName", selectedItem.getNameStore());
                    startActivity(intent);
                }
            });

            // Chuyển đến trang AddEcommerce khi nhấn vào add_button
            ImageView addButton = findViewById(R.id.add_button);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StoreInformation.this, StoreAddEcommerce.class);
                    startActivity(intent);
                }
            });
        } else if (position == 2) {
            header_image.setImageResource(R.drawable.workshift);

            String[] EmployeeID = {"0", "0", "0", "0"};
            String[] EmployeeName = {"Pasta", "Maggi", "Pasta", "Maggi"};
            String[] StoreName = {"Lazada", "Shopee", "Amazon", "Tiki"};
            String[] Email = {"0", "0", "0", "0"};
            String[] Phone = {"0", "0", "0", "0"};
            String[] Address = {"0", "0", "0", "0"};
            String[] Position = {"0", "0", "0", "0"};
            String[] DayOfWork = {"0", "0", "0", "0"};
            Integer[] BaseSalary = {0, 0, 0, 0};
            Integer[] BonusSalary = {0, 0, 0, 0};

            ArrayList<ListStoreEmployeeData> dataArrayList = new ArrayList<>();

            for (int i = 0; i < EmployeeID.length; i++) {
                ListStoreEmployeeData listData = new ListStoreEmployeeData(
                        EmployeeID[i],
                        EmployeeName[i],
                        StoreName[i],
                        Email[i],
                        Phone[i],
                        Address[i],
                        Position[i],
                        DayOfWork[i],
                        BaseSalary[i],
                        BonusSalary[i]
                );
                dataArrayList.add(listData);
            }

            listStoreEmployeeAdapter = new ListStoreEmployeeAdapter(StoreInformation.this, dataArrayList);
            listView.setAdapter(listStoreEmployeeAdapter);
            listView.setClickable(true);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ListStoreEmployeeData selectedItem = (ListStoreEmployeeData) parent.getItemAtPosition(position);

                    Intent intent = new Intent(StoreInformation.this, StoreDetailEmployee.class);
                    intent.putExtra("EmployeeName", selectedItem.getEmployeeName());
                    intent.putExtra("storeName", selectedItem.getStoreName());
                    startActivity(intent);
                }
            });

            // Chuyển đến trang AddEcommerce khi nhấn vào add_button
            ImageView addButton = findViewById(R.id.add_button);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StoreInformation.this, StoreAddEmployee.class);
                    startActivity(intent);
                }
            });
        } else if (position == 3) {
            header_image.setImageResource(R.drawable.product);

            String[] ProductID = {"0", "1", "2", "3"};
            String[] ProductName = {"Pasta", "Maggi", "Pasta", "Maggi"};
            String[] StoreName = {"0", "0", "0", "0"};
            Integer[] Quantity = {0, 0, 0 , 0};
            Integer[] Price = {0, 0, 0 , 0};

            ArrayList<ListStoreProductData> dataArrayList = new ArrayList<>();
            ListStoreProductData listData;

            for (int i = 0; i < ProductID.length; i++) {
                listData = new ListStoreProductData(
                        ProductID[i],
                        ProductName[i],
                        StoreName[i],
                        Quantity[i],
                        Price[i]
                );
                dataArrayList.add(listData);
            }

            listStoreProductAdapter = new ListStoreProductAdapter(StoreInformation.this, dataArrayList);
            listView.setAdapter(listStoreProductAdapter);
            listView.setClickable(true);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ListStoreProductData selectedItem = (ListStoreProductData) parent.getItemAtPosition(position);

                    Intent intent = new Intent(StoreInformation.this, StoreDetailProduct.class);
                    intent.putExtra("productName", selectedItem.getProductName());
                    intent.putExtra("storeName", selectedItem.getStoreName());
                    startActivity(intent);
                }
            });

            // Chuyển đến trang AddEcommerce khi nhấn vào add_button
            ImageView addButton = findViewById(R.id.add_button);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StoreInformation.this, StoreAddProduct.class);
                    startActivity(intent);
                }
            });
        }else if (position == 4) {
            header_image.setImageResource(R.drawable.supplier);

            String[] SupplierID = {"0", "1", "2", "3"};
            String[] SupplierName = {"Pasta", "Maggi", "Pasta", "Maggi"};
            String[] StoreName = {"0", "0", "0", "0"};

            ArrayList<ListStoreSupplierData> dataArrayList = new ArrayList<>();
            ListStoreSupplierData listData;

            for (int i = 0; i < SupplierID.length; i++) {
                listData = new ListStoreSupplierData(
                        SupplierID[i],
                        SupplierName[i],
                        StoreName[i]
                );
                dataArrayList.add(listData);
            }

            listStoreSupplierAdapter = new ListStoreSupplierAdapter(StoreInformation.this, dataArrayList);
            listView.setAdapter(listStoreSupplierAdapter);
            listView.setClickable(true);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ListStoreSupplierData selectedItem = (ListStoreSupplierData) parent.getItemAtPosition(position);

                    Intent intent = new Intent(StoreInformation.this, StoreDetailSupplier.class);
                    intent.putExtra("supplierName", selectedItem.getSupplierName());
                    intent.putExtra("storeName", selectedItem.getStoreName());
                    startActivity(intent);
                }
            });

            // Chuyển đến trang AddEcommerce khi nhấn vào add_button
            ImageView addButton = findViewById(R.id.add_button);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StoreInformation.this, StoreAddSupplier.class);
                    startActivity(intent);
                }
            });
        }

        ImageView exitButton = findViewById(R.id.exit_icon);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed(); //
            }
        });
    }
}