package com.example.expensemanagement.Store;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import com.example.expensemanagement.R;
import com.example.expensemanagement.Store.Adapter.ListStoreEcommerceAdapter;
import com.example.expensemanagement.Store.Adapter.ListStoreEmployeeAdapter;
import com.example.expensemanagement.Store.Adapter.ListStoreProductAdapter;
import com.example.expensemanagement.Store.Adapter.ListStoreSupplierAdapter;
import com.example.expensemanagement.Store.Model.ListStoreEcommerceData;
import com.example.expensemanagement.Store.Model.ListStoreEmployeeData;
import com.example.expensemanagement.Store.Model.ListStoreProductData;
import com.example.expensemanagement.Store.Model.ListStoreSupplierData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.lang.String;

public class StoreInformation extends AppCompatActivity {
    //ecommerce
    List<ListStoreEcommerceData> ecommerceDataList;
    ListStoreEcommerceAdapter ecommerceAdapter;

    // emloyee
    List<ListStoreEmployeeData> employeeDataList;
    ListStoreEmployeeAdapter employeeAdapter;

    // product
    List<ListStoreProductData> productDataList;
    ListStoreProductAdapter productAdapter;

    //supplier
    List<ListStoreSupplierData> supplierDataList;
    ListStoreSupplierAdapter supplierAdapter;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    RecyclerView recyclerView;
    ImageView header_image, icon_az2;
    SearchView searchView;
    boolean isAscending = true;

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

        recyclerView = findViewById(R.id.recyclerView);
        header_image = findViewById(R.id.header_image);
        icon_az2 = findViewById(R.id.icon_az2);
        searchView = findViewById(R.id.search);

        ImageView exit_icon = findViewById(R.id.exit_icon);
        exit_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (position == 1) {
            header_image.setImageResource(R.drawable.ec2);

            searchView.clearFocus();
            GridLayoutManager gridLayoutManager = new GridLayoutManager(StoreInformation.this, 1);
            recyclerView.setLayoutManager(gridLayoutManager);

            ecommerceDataList = new ArrayList<>();

            ecommerceAdapter = new ListStoreEcommerceAdapter(StoreInformation.this, ecommerceDataList);
            recyclerView.setAdapter(ecommerceAdapter);

            databaseReference = FirebaseDatabase.getInstance().getReference("ecommerces");

            eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    ecommerceDataList.clear();
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        ListStoreEcommerceData employeeData = itemSnapshot.getValue(ListStoreEcommerceData.class);
                        ecommerceDataList.add(employeeData);
                    }
                    ecommerceAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    searchListEcommerce(newText);
                    return true;
                }
            });

            icon_az2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sortEcommerceList();
                }
            });

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

            searchView.clearFocus();
            GridLayoutManager gridLayoutManager = new GridLayoutManager(StoreInformation.this, 1);
            recyclerView.setLayoutManager(gridLayoutManager);

            employeeDataList = new ArrayList<>();
            employeeAdapter = new ListStoreEmployeeAdapter(StoreInformation.this, employeeDataList);
            recyclerView.setAdapter(employeeAdapter);

            databaseReference = FirebaseDatabase.getInstance().getReference("employees");

            eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    employeeDataList.clear();
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        ListStoreEmployeeData employeeData = itemSnapshot.getValue(ListStoreEmployeeData.class);
                        if (employeeData != null) {
                            employeeDataList.add(employeeData);
                        }
                    }
                    employeeAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Xử lý lỗi nếu cần
                }
            });

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    searchListEmployee(newText);
                    return true;
                }
            });

            icon_az2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sortEmployeeList();
                }
            });

            ImageView addButton = findViewById(R.id.add_button);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StoreInformation.this, StoreAddEmployee.class);
                    startActivity(intent);
                }
            });
        }
        else if (position == 3) {
                header_image.setImageResource(R.drawable.product);

                searchView.clearFocus();
                GridLayoutManager gridLayoutManager = new GridLayoutManager(StoreInformation.this, 1);
                recyclerView.setLayoutManager(gridLayoutManager);

                productDataList = new ArrayList<>();

                productAdapter = new ListStoreProductAdapter(StoreInformation.this, productDataList);
                recyclerView.setAdapter(productAdapter);

                databaseReference = FirebaseDatabase.getInstance().getReference("products");

                eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        productDataList.clear();
                        for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                            ListStoreProductData productData = itemSnapshot.getValue(ListStoreProductData.class);
                            productDataList.add(productData);
                        }
                        productAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        searchListProduct(newText);
                        return true;
                    }
                });

                icon_az2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sortProductList();
                    }
                });

                ImageView addButton = findViewById(R.id.add_button);
                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(StoreInformation.this, StoreAddProduct.class);
                        startActivity(intent);
                    }
                });
            } else if (position == 4) {
                header_image.setImageResource(R.drawable.supplier);

                searchView.clearFocus();
                GridLayoutManager gridLayoutManager = new GridLayoutManager(StoreInformation.this, 1);
                recyclerView.setLayoutManager(gridLayoutManager);

                supplierDataList = new ArrayList<>();

                supplierAdapter = new ListStoreSupplierAdapter(StoreInformation.this, supplierDataList);
                recyclerView.setAdapter(supplierAdapter);

                databaseReference = FirebaseDatabase.getInstance().getReference("suppliers");

                eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        supplierDataList.clear();
                        for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                            ListStoreSupplierData supplierData = itemSnapshot.getValue(ListStoreSupplierData.class);
                            supplierDataList.add(supplierData);
                        }
                        supplierAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        searchListSupplier(newText);
                        return true;
                    }
                });
                icon_az2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sortSupplierList();
                    }
                });

                ImageView addButton = findViewById(R.id.add_button);
                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(StoreInformation.this, StoreAddSupplier.class);
                        startActivity(intent);
                    }
                });
            }
    }

    public void searchListEcommerce(String text) {
            ArrayList<ListStoreEcommerceData> searchList = new ArrayList<>();
            for (ListStoreEcommerceData ecommerceData : ecommerceDataList) {
                if (ecommerceData.getEcommerceName().toLowerCase().contains(text.toLowerCase())) {
                    searchList.add(ecommerceData);
                }
            }
            ecommerceAdapter.searchDataList(searchList);
        }

    private void sortEcommerceList() {
            if (isAscending) {
                Collections.sort(ecommerceDataList, new Comparator<ListStoreEcommerceData>() {
                    @Override
                    public int compare(ListStoreEcommerceData p1, ListStoreEcommerceData p2) {
                        return p1.getEcommerceName().compareToIgnoreCase(p2.getEcommerceName());
                    }
                });
            } else {
                Collections.sort(ecommerceDataList, new Comparator<ListStoreEcommerceData>() {
                    @Override
                    public int compare(ListStoreEcommerceData p1, ListStoreEcommerceData p2) {
                        return p2.getEcommerceName().compareToIgnoreCase(p1.getEcommerceName());
                    }
                });
            }
            ecommerceAdapter.notifyDataSetChanged();
            isAscending = !isAscending;
        }

    public void searchListEmployee(String text) {
            ArrayList<ListStoreEmployeeData> searchList = new ArrayList<>();
            for (ListStoreEmployeeData employeeData : employeeDataList) {
                if (employeeData.getEmployeeName().toLowerCase().contains(text.toLowerCase())) {
                    searchList.add(employeeData);
                }
            }
            employeeAdapter.searchDataList(searchList);
        }

    private void sortEmployeeList() {
            if (isAscending) {
                Collections.sort(employeeDataList, new Comparator<ListStoreEmployeeData>() {
                    @Override
                    public int compare(ListStoreEmployeeData p1, ListStoreEmployeeData p2) {
                        return p1.getEmployeeName().compareToIgnoreCase(p2.getEmployeeName());
                    }
                });
            } else {
                Collections.sort(employeeDataList, new Comparator<ListStoreEmployeeData>() {
                    @Override
                    public int compare(ListStoreEmployeeData p1, ListStoreEmployeeData p2) {
                        return p2.getEmployeeName().compareToIgnoreCase(p1.getEmployeeName());
                    }
                });
            }
            employeeAdapter.notifyDataSetChanged();
            isAscending = !isAscending;
        }

    public void searchListProduct(String text) {
        ArrayList<ListStoreProductData> searchList = new ArrayList<>();
        for (ListStoreProductData productData : productDataList) {
            if (productData.getProductName().toLowerCase().contains(text.toLowerCase())) {
                searchList.add(productData);
            }
        }
        productAdapter.searchDataList(searchList);
    }

    private void sortProductList() {
        if (isAscending) {
            Collections.sort(productDataList, new Comparator<ListStoreProductData>() {
                @Override
                public int compare(ListStoreProductData p1, ListStoreProductData p2) {
                    return p1.getProductName().compareToIgnoreCase(p2.getProductName());
                }
            });
        } else {
            Collections.sort(productDataList, new Comparator<ListStoreProductData>() {
                @Override
                public int compare(ListStoreProductData p1, ListStoreProductData p2) {
                    return p2.getProductName().compareToIgnoreCase(p1.getProductName());
                }
            });
        }
        productAdapter.notifyDataSetChanged();
        isAscending = !isAscending;
    }

    public void searchListSupplier(String text) {
            ArrayList<ListStoreSupplierData> searchList = new ArrayList<>();
            for (ListStoreSupplierData supplierData : supplierDataList) {
                if (supplierData.getSupplierName().toLowerCase().contains(text.toLowerCase())) {
                    searchList.add(supplierData);
                }
            }
            supplierAdapter.searchDataList(searchList);
        }

    private void sortSupplierList() {
            if (isAscending) {
                Collections.sort(supplierDataList, new Comparator<ListStoreSupplierData>() {
                    @Override
                    public int compare(ListStoreSupplierData p1, ListStoreSupplierData p2) {
                        return p1.getSupplierName().compareToIgnoreCase(p2.getSupplierName());
                    }
                });
            } else {
                Collections.sort(supplierDataList, new Comparator<ListStoreSupplierData>() {
                    @Override
                    public int compare(ListStoreSupplierData p1, ListStoreSupplierData p2) {
                        return p2.getSupplierName().compareToIgnoreCase(p1.getSupplierName());
                    }
                });
            }
            supplierAdapter.notifyDataSetChanged();
            isAscending = !isAscending;
        }
}
