package com.example.expensemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.expensemanagement.Admin.SignInActivity;
import com.example.expensemanagement.Bill.Adapter.ListBillEcommerceAdapter;
import com.example.expensemanagement.Bill.Adapter.ListBillFacitilyAdapter;
import com.example.expensemanagement.Bill.Adapter.ListBillProductAdapter;
import com.example.expensemanagement.Bill.Adapter.ListBillStoreAdapter;
import com.example.expensemanagement.Bill.Adapter.ListBillSupplyAdapter;
import com.example.expensemanagement.Bill.Adapter.ListBillWorkshiftAdapter;
import com.example.expensemanagement.Bill.BillInformation;
import com.example.expensemanagement.Bill.Model.BillEcommerce;
import com.example.expensemanagement.Bill.Model.BillFacility;
import com.example.expensemanagement.Bill.Model.BillProduct;
import com.example.expensemanagement.Bill.Model.BillStore;
import com.example.expensemanagement.Bill.Model.BillSupply;
import com.example.expensemanagement.Bill.Model.BillWorkshift;
import com.example.expensemanagement.Bill.Respository.BillEcmmerceRespository;
import com.example.expensemanagement.Bill.Respository.BillFacilityRespository;
import com.example.expensemanagement.Bill.Respository.BillProductRespository;
import com.example.expensemanagement.Bill.Respository.BillStoreRespository;
import com.example.expensemanagement.Bill.Respository.BillSupplyRespository;
import com.example.expensemanagement.Bill.Respository.BillWorkshiftRespository;
import com.example.expensemanagement.Home.HomeActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WaitingActivity extends AppCompatActivity {
    Handler handler;
    private BillEcmmerceRespository billEcmmerceRespository;
    private BillFacilityRespository billFacilityRespository;
    private BillProductRespository billProductRespository;
    private BillStoreRespository billStoreRespository;
    private BillSupplyRespository billSupplyRespository;
    private BillWorkshiftRespository billWorkshiftRespository;
    private DatabaseReference billRef;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static final int REQUEST_CODE_BILL_INFORMATION = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_waiting);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                billEcmmerceRespository = new BillEcmmerceRespository();
                billRef = database.getReference("billEcommerces");
                fetchBill(1);

                billFacilityRespository = new BillFacilityRespository();
                billRef = database.getReference("billFacilities");
                fetchBill(2);

                billProductRespository = new BillProductRespository();
                billRef = database.getReference("billProducts");
                fetchBill(3);

                billStoreRespository = new BillStoreRespository();
                billRef = database.getReference("billStores");
                fetchBill(4);

                billSupplyRespository = new BillSupplyRespository();
                billRef = database.getReference("billSupplies");
                fetchBill(5);

                billWorkshiftRespository = new BillWorkshiftRespository();
                billRef = database.getReference("billWorkshifts");
                fetchBill(6);

                startActivity(new Intent(WaitingActivity.this, HomeActivity.class));
                finish();
            }
        },3000);
    }
    private void fetchBill(int postion) {
        billRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (postion == 1){
                    billEcmmerceRespository.clearListBillEcommerce(WaitingActivity.this);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        BillEcommerce billEcommerce = snapshot.getValue(BillEcommerce.class);
                        if (billEcommerce != null) {
                            billEcmmerceRespository.addBillEcommerce(billEcommerce,WaitingActivity.this);
                        }
                    }
                } else if (postion == 2) {
                    billFacilityRespository.clearListBillFacility(WaitingActivity.this);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        BillFacility billFacility = snapshot.getValue(BillFacility.class);
                        if (billFacility != null) {
                            billFacilityRespository.addBillFacility(billFacility,WaitingActivity.this);
                        }
                    }
                } else if (postion == 3) {
                    billProductRespository.clearListBillProduct(WaitingActivity.this);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        BillProduct billProduct = snapshot.getValue(BillProduct.class);
                        if (billProduct != null) {
                            billProductRespository.addBillProduct(billProduct,WaitingActivity.this);
                        }
                    }
                } else if (postion == 4) {
                    billStoreRespository.clearListBillStore(WaitingActivity.this);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        BillStore billStore = snapshot.getValue(BillStore.class);
                        if (billStore != null) {
                            billStoreRespository.addBillStore(billStore,WaitingActivity.this);
                        }
                    }
                } else if (postion == 5) {
                    billSupplyRespository.clearListBillSupply(WaitingActivity.this);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        BillSupply billSupply = snapshot.getValue(BillSupply.class);
                        if (billSupply != null) {
                            billSupplyRespository.addBillSupply(billSupply,WaitingActivity.this);
                        }
                    }
                }else if (postion == 6) {
                    billWorkshiftRespository.clearListBillWorkshift(WaitingActivity.this);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        BillWorkshift billWorkshift = snapshot.getValue(BillWorkshift.class);
                        if (billWorkshift != null) {
                            billWorkshiftRespository.addBillWorkshift(billWorkshift,WaitingActivity.this);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors.

            }
        });
    }
}