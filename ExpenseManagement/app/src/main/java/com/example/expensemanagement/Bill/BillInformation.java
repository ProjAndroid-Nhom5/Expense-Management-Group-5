package com.example.expensemanagement.Bill;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.expensemanagement.Bill.Adapter.ListBillEcommerceAdapter;
import com.example.expensemanagement.Bill.Adapter.ListBillFacitilyAdapter;
import com.example.expensemanagement.Bill.Adapter.ListBillProductAdapter;
import com.example.expensemanagement.Bill.Adapter.ListBillStoreAdapter;
import com.example.expensemanagement.Bill.Adapter.ListBillSupplyAdapter;
import com.example.expensemanagement.Bill.Adapter.ListBillWorkshiftAdapter;
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
import com.example.expensemanagement.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BillInformation extends AppCompatActivity {
    //
    private ListBillEcommerceAdapter listBillEcommerceAdapter;
    private ArrayList<BillEcommerce> billEcommerceList = new ArrayList<>();
    private BillEcmmerceRespository billEcmmerceRespository;
    //
    private ListBillFacitilyAdapter listBillFacitilyAdapter;
    private ArrayList<BillFacility> billFacilityList = new ArrayList<>();
    private BillFacilityRespository billFacilityRespository;
    //
    private ListBillProductAdapter listBillProductAdapter;
    private ArrayList<BillProduct> billProductList = new ArrayList<>();
    private BillProductRespository billProductRespository;
    //
    private BillStoreRespository billStoreRespository;
    private ArrayList<BillStore> billStoreList = new ArrayList<>();
    private ListBillStoreAdapter listBillStoreAdapter;
    //
    private ListBillSupplyAdapter listBillSupplyAdapter;
    private ArrayList<BillSupply> billSupplyList = new ArrayList<>();
    private BillSupplyRespository billSupplyRespository;
    //
    private ListBillWorkshiftAdapter listBillWorkshiftAdapter;
    private ArrayList<BillWorkshift> billWorkshiftList = new ArrayList<>();
    private BillWorkshiftRespository billWorkshiftRespository;
    private ImageView exit_icon;
    private ListView listView;
    private BarChart barChart;
    private SearchView searchView;
    private TextView atoz_text,timeTxt,totalTxt;
    private ConstraintLayout layout_atoz, layout_time, layout_totalpayment;
    private ImageView atoz_image,timeImages,totalImage;
    private DatabaseReference billRef;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private String selectedFilter = "all";
    private String searchText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bill_information);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        exit_icon = findViewById(R.id.exit_icon);
        barChart = findViewById(R.id.barchart);
        listView = findViewById(R.id.listview);
        searchView = findViewById(R.id.search);
        layout_atoz = findViewById(R.id.layout_atoz);
        layout_time = findViewById(R.id.layout_time);
        layout_totalpayment = findViewById(R.id.layout_totalpayment);
        atoz_text = findViewById(R.id.atoz_text);
        timeTxt = findViewById(R.id.timeTxt);
        totalTxt = findViewById(R.id.totalTxt);
        atoz_image = findViewById(R.id.atoz_image);
        timeImages = findViewById(R.id.timeImages);
        totalImage = findViewById(R.id.totalImage);

        exit_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        int position = intent.getIntExtra("potision",0);

        layout_atoz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedFilter.equals("atoz")){
                    selectedFilter = "ztoa";
                    atoz_text.setText("Z - A ");
                    atoz_image.setImageResource(R.drawable.baseline_arrow_downward_24);
                    if (position == 1){
                        billEcommerceList = billEcmmerceRespository.getListFromZToA(BillInformation.this);
                    }else if (position == 2){
                        billFacilityList = billFacilityRespository.getListFromZToA(BillInformation.this);
                    }else if (position == 3){
                        billProductList = billProductRespository.getListFromZToA(BillInformation.this);
                    }else if (position == 4){
                        billStoreList = billStoreRespository.getListFromZToA(BillInformation.this);
                    }else if (position == 5){
                        billSupplyList = billSupplyRespository.getListFromZToA(BillInformation.this);
                    }else if (position == 6){
                        billWorkshiftList = billWorkshiftRespository.getListFromZToA(BillInformation.this);
                    }
                    searchChange(position);
                }else if(selectedFilter.equals("ztoa")){
                    int colorResourceId = R.color.blue;
                    int color = ContextCompat.getColor(BillInformation.this, colorResourceId);
                    layout_atoz.setBackgroundColor(color);
                    selectedFilter = "all";
                    atoz_text.setText("A - Z ");
                    atoz_image.setImageResource(R.drawable.transfer_4);
                    Nomarl(position);
                    searchChange(position);
                } else {
                    layout_time_nomral();
                    layout_totalpayment_nomral();
                    int colorResourceId = R.color.white;
                    int color = ContextCompat.getColor(BillInformation.this, colorResourceId);
                    layout_atoz.setBackgroundColor(color);
                    selectedFilter = "atoz";
                    atoz_text.setText("A - Z ");
                    atoz_image.setImageResource(R.drawable.baseline_arrow_upward_24);
                    if (position == 1){
                        billEcommerceList = billEcmmerceRespository.getListFromAToZ(BillInformation.this);
                    }else if (position == 2){
                        billFacilityList = billFacilityRespository.getListFromAToZ(BillInformation.this);
                    }else if (position == 3){
                        billProductList = billProductRespository.getListFromAToZ(BillInformation.this);
                    }else if (position == 4){
                        billStoreList = billStoreRespository.getListFromAToZ(BillInformation.this);
                    }else if (position == 5){
                        billSupplyList = billSupplyRespository.getListFromAToZ(BillInformation.this);
                    }else if (position == 6){
                        billWorkshiftList = billWorkshiftRespository.getListFromAToZ(BillInformation.this);
                    }
                    searchChange(position);
                }
            }
        });

        layout_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedFilter.equals("timeASC")){
                    selectedFilter = "timeDESC";
                    timeImages.setImageResource(R.drawable.baseline_arrow_downward_24);
                    listView.setAdapter(listBillEcommerceAdapter);
                    if (position == 1){
                        billEcommerceList = billEcmmerceRespository.getListFromTimeDESC(BillInformation.this);
                    }else if (position == 2){
                        billFacilityList = billFacilityRespository.getListFromTimeDESC(BillInformation.this);
                    }else if (position == 4){
                        billStoreList = billStoreRespository.getListFromTimeDESC(BillInformation.this);
                    }else if (position == 5){
                        billSupplyList = billSupplyRespository.getListFromTimeDESC(BillInformation.this);
                    }else if (position == 6){
                        billWorkshiftList = billWorkshiftRespository.getListFromTimeDESC(BillInformation.this);
                    }
                    searchChange(position);
                }else if(selectedFilter.equals("timeDESC")){
                    int colorResourceId = R.color.blue;
                    int color = ContextCompat.getColor(BillInformation.this, colorResourceId);
                    layout_time.setBackgroundColor(color);
                    selectedFilter = "all";
                    timeImages.setImageResource(R.drawable.transfer_4);
                    Nomarl(position);
                    searchChange(position);
                } else {
                    layout_atoz_nomral();
                    layout_totalpayment_nomral();
                    int colorResourceId = R.color.white;
                    int color = ContextCompat.getColor(BillInformation.this, colorResourceId);
                    layout_time.setBackgroundColor(color);
                    selectedFilter = "timeASC";
                    timeImages.setImageResource(R.drawable.baseline_arrow_upward_24);
                    if (position == 1){
                        billEcommerceList = billEcmmerceRespository.getListFromTimeASC(BillInformation.this);
                    }else if (position == 2){
                        billFacilityList = billFacilityRespository.getListFromTimeASC(BillInformation.this);
                    }else if (position == 4){
                        billStoreList = billStoreRespository.getListFromTimeASC(BillInformation.this);
                    }else if (position == 5){
                        billSupplyList = billSupplyRespository.getListFromTimeASC(BillInformation.this);
                    }else if (position == 6){
                        billWorkshiftList = billWorkshiftRespository.getListFromTimeASC(BillInformation.this);
                    }
                    searchChange(position);
                }
            }
        });

        layout_totalpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedFilter.equals("totalpaymentASC")){
                    selectedFilter = "totalpaymentDESC";
                    totalImage.setImageResource(R.drawable.baseline_arrow_downward_24);
                    if (position == 1){
                        billEcommerceList = billEcmmerceRespository.getListFromTotalPaymentDESC(BillInformation.this);
                    }else if (position == 2){
                        billFacilityList = billFacilityRespository.getListFromTotalPaymentDESC(BillInformation.this);
                    }else if (position == 3){
                        billProductList = billProductRespository.getListFromQuantityDESC(BillInformation.this);
                    }else if (position == 4){
                        billStoreList = billStoreRespository.getListFromTotalPaymentDESC(BillInformation.this);
                    }else if (position == 5){
                        billSupplyList = billSupplyRespository.getListFromTotalPaymentDESC(BillInformation.this);
                    }else if (position == 6){
                        billWorkshiftList = billWorkshiftRespository.getListFromTotalPaymentDESC(BillInformation.this);
                    }
                    searchChange(position);
                }else if(selectedFilter.equals("totalpaymentDESC")){
                    int colorResourceId = R.color.blue;
                    int color = ContextCompat.getColor(BillInformation.this, colorResourceId);
                    layout_totalpayment.setBackgroundColor(color);
                    selectedFilter = "all";
                    totalImage.setImageResource(R.drawable.transfer_4);
                    Nomarl(position);
                    searchChange(position);
                } else {
                    layout_atoz_nomral();
                    layout_time_nomral();
                    int colorResourceId = R.color.white;
                    int color = ContextCompat.getColor(BillInformation.this, colorResourceId);
                    layout_totalpayment.setBackgroundColor(color);
                    selectedFilter = "totalpaymentASC";
                    totalImage.setImageResource(R.drawable.baseline_arrow_upward_24);
                    if (position == 1){
                        billEcommerceList = billEcmmerceRespository.getListFromTotalPaymentASC(BillInformation.this);
                    }else if (position == 2){
                        billFacilityList = billFacilityRespository.getListFromTotalPaymentASC(BillInformation.this);
                    }else if (position == 3){
                        billProductList = billProductRespository.getListFromQuantityASC(BillInformation.this);
                    }else if (position == 4){
                        billStoreList = billStoreRespository.getListFromTotalPaymentASC(BillInformation.this);
                    }else if (position == 5){
                        billSupplyList = billSupplyRespository.getListFromTotalPaymentASC(BillInformation.this);
                    }else if (position ==  6){
                        billWorkshiftList = billWorkshiftRespository.getListFromTotalPaymentASC(BillInformation.this);
                    }
                    searchChange(position);
                }
            }
        });

        initSearch(position);

        if (position == 1){
            billEcmmerceRespository = new BillEcmmerceRespository();
            billRef = database.getReference("billEcommerces");

            fetchBill(position);

            listView.setClickable(true);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    BillEcommerce selectedItem = (BillEcommerce) parent.getItemAtPosition(position);
                    Intent intent = new Intent(BillInformation.this, Bill_Detail_Ecommerce.class);
                    intent.putExtra("billEcommerce", selectedItem);
                    startActivity(intent);
                }
            });
        }
        else if (position == 2){
            billFacilityRespository = new BillFacilityRespository();
            billRef = database.getReference("billFacilities");

            fetchBill(position);

            listView.setClickable(true);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    BillFacility selectedItem = (BillFacility) parent.getItemAtPosition(position);
                    Intent intent = new Intent(BillInformation.this, Bill_Detail_Facility.class);
                    intent.putExtra("billFacility", selectedItem);
                    startActivity(intent);
                }
            });
        }else if (position == 3) {
            barChart.setVisibility(View.GONE);
            layout_time.setVisibility(View.GONE);
            totalTxt.setText("Quantity ");

            billProductRespository = new BillProductRespository();
            billRef = database.getReference("billProducts");

            fetchBill(position);

            listView.setClickable(true);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    BillProduct selectedItem = (BillProduct) parent.getItemAtPosition(position);
                    Intent intent = new Intent(BillInformation.this, Bill_Detail_Product.class);
                    intent.putExtra("billProduct", selectedItem);
                    startActivity(intent);
                }
            });
        }else if (position == 4){
            billStoreRespository = new BillStoreRespository();
            billRef = database.getReference("billStores");

            fetchBill(position);

            listView.setClickable(true);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    BillStore selectedItem = (BillStore) parent.getItemAtPosition(position);
                    Intent intent = new Intent(BillInformation.this, Bill_Detail_Store.class);
                    intent.putExtra("billStore", selectedItem);
                    startActivity(intent);
                }
            });

        }else if (position == 5){
            billSupplyRespository = new BillSupplyRespository();
            billRef = database.getReference("billSupplies");

            fetchBill(position);

            listView.setClickable(true);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    BillSupply selectedItem = (BillSupply) parent.getItemAtPosition(position);
                    Intent intent = new Intent(BillInformation.this, Bill_Detail_Supply.class);
                    intent.putExtra("billSupply", selectedItem);
                    startActivity(intent);
                }
            });
        }else if (position == 6){
            billWorkshiftRespository = new BillWorkshiftRespository();
            billRef = database.getReference("billWorkshifts");

            fetchBill(position);

            listView.setClickable(true);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    BillWorkshift selectedItem = (BillWorkshift) parent.getItemAtPosition(position);
                    Intent intent = new Intent(BillInformation.this, Bill_Detail_Workshift.class);
                    intent.putExtra("billWorkshift", selectedItem);
                    startActivity(intent);
                }
            });
        }
    }

    private void initSearch(int potion){
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchText = newText;
                searchChange(potion);
                return true;
            }
        });
    }

    private void fetchBill(int postion) {
        billRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (postion == 1){
                    billEcmmerceRespository.clearListBillEcommerce(BillInformation.this);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        BillEcommerce billEcommerce = snapshot.getValue(BillEcommerce.class);
                        if (billEcommerce != null) {
                            billEcmmerceRespository.addBillEcommerce(billEcommerce,BillInformation.this);
                        }
                    }
                    billEcommerceList = billEcmmerceRespository.getListBillEcommerce(BillInformation.this);
                } else if (postion == 2) {
                    billFacilityRespository.clearListBillFacility(BillInformation.this);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        BillFacility billFacility = snapshot.getValue(BillFacility.class);
                        if (billFacility != null) {
                            billFacilityRespository.addBillFacility(billFacility,BillInformation.this);
                        }
                    }
                    billFacilityList = billFacilityRespository.getListBillFacility(BillInformation.this);
                } else if (postion == 3) {
                    billProductRespository.clearListBillProduct(BillInformation.this);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        BillProduct billProduct = snapshot.getValue(BillProduct.class);
                        if (billProduct != null) {
                            billProductRespository.addBillProduct(billProduct,BillInformation.this);
                        }
                    }
                    billProductList = billProductRespository.getListBillProduct(BillInformation.this);
                } else if (postion == 4) {
                    billStoreRespository.clearListBillStore(BillInformation.this);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        BillStore billStore = snapshot.getValue(BillStore.class);
                        if (billStore != null) {
                            billStoreRespository.addBillStore(billStore,BillInformation.this);
                        }
                    }
                    billStoreList = billStoreRespository.getListBillStore(BillInformation.this);
                } else if (postion == 5) {
                    billSupplyRespository.clearListBillSupply(BillInformation.this);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        BillSupply billSupply = snapshot.getValue(BillSupply.class);
                        if (billSupply != null) {
                            billSupplyRespository.addBillSupply(billSupply,BillInformation.this);
                        }
                    }
                    billSupplyList = billSupplyRespository.getListBillSupply(BillInformation.this);
                }else if (postion == 6) {
                    billWorkshiftRespository.clearListBillWorkshift(BillInformation.this);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        BillWorkshift billWorkshift = snapshot.getValue(BillWorkshift.class);
                        if (billWorkshift != null) {
                            billWorkshiftRespository.addBillWorkshift(billWorkshift,BillInformation.this);
                        }
                    }
                    billWorkshiftList = billWorkshiftRespository.getListBillWorkshift(BillInformation.this);
                }

                searchChange(postion);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors.

            }
        });
    }

    private void CreateBar(ArrayList<BarEntry> arrayList,String name){
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.invalidate();

        BarDataSet barDataSet1 = new BarDataSet(arrayList,name);
        barDataSet1.setColors(Color.argb(188,119,193,202));

        BarData barData = new BarData();
        barData.addDataSet(barDataSet1);
        barChart.setData(barData);
    }

    private void searchChange(int position){
        if(position == 1){
            if (searchText.isEmpty() || searchText.equals("")){
                listBillEcommerceAdapter = new ListBillEcommerceAdapter(BillInformation.this, billEcommerceList);
                listView.setAdapter(listBillEcommerceAdapter);

                CreateBar(billEcmmerceRespository.displayBill(billEcommerceList),"Ecommerce");
            }else {
                ArrayList<BillEcommerce> list = new ArrayList<>();

                for (BillEcommerce b:billEcommerceList){
                    if (b.getNameCustomer().toLowerCase().contains(searchText.toLowerCase())){
                        list.add(b);
                    }
                }

                listBillEcommerceAdapter = new ListBillEcommerceAdapter(BillInformation.this, list);
                listView.setAdapter(listBillEcommerceAdapter);

                CreateBar(billEcmmerceRespository.displayBill(list),"Ecommerce");
            }
        }else if(position == 2){
            if (searchText.isEmpty() || searchText.equals("")){
                listBillFacitilyAdapter = new ListBillFacitilyAdapter(BillInformation.this, billFacilityList);
                listView.setAdapter(listBillFacitilyAdapter);

                CreateBar(billFacilityRespository.displayBill(billFacilityList),"Facility");
            }else {
                ArrayList<BillFacility> list = new ArrayList<>();

                for (BillFacility b:billFacilityList){
                    if (b.getNameFacility().toLowerCase().contains(searchText.toLowerCase())){
                        list.add(b);
                    }
                }

                listBillFacitilyAdapter = new ListBillFacitilyAdapter(BillInformation.this, list);
                listView.setAdapter(listBillFacitilyAdapter);

                CreateBar(billFacilityRespository.displayBill(list),"Facility");
            }
        }else if(position == 3){
            if (searchText.isEmpty() || searchText.equals("")){
                listBillProductAdapter = new ListBillProductAdapter(BillInformation.this, billProductList);
                listView.setAdapter(listBillProductAdapter);
            }else {
                ArrayList<BillProduct> list = new ArrayList<>();

                for (BillProduct b:billProductList){
                    if (b.getNameProduct().toLowerCase().contains(searchText.toLowerCase())){
                        list.add(b);
                    }
                }

                listBillProductAdapter = new ListBillProductAdapter(BillInformation.this, list);
                listView.setAdapter(listBillProductAdapter);
            }
        }else if(position == 4){
            if (searchText.isEmpty() || searchText.equals("")){
                listBillStoreAdapter = new ListBillStoreAdapter(BillInformation.this, billStoreList);
                listView.setAdapter(listBillStoreAdapter);

                CreateBar(billStoreRespository.displayBill(billStoreList),"Store");
            }else {
                ArrayList<BillStore> list = new ArrayList<>();

                for (BillStore b:billStoreList){
                    if (b.getNameEmploye().toLowerCase().contains(searchText.toLowerCase())){
                        list.add(b);
                    }
                }

                listBillStoreAdapter = new ListBillStoreAdapter(BillInformation.this, list);
                listView.setAdapter(listBillStoreAdapter);

                CreateBar(billStoreRespository.displayBill(list),"Store");
            }
        }else if(position == 5){
            if (searchText.isEmpty() || searchText.equals("")){
                listBillSupplyAdapter = new ListBillSupplyAdapter(BillInformation.this, billSupplyList);
                listView.setAdapter(listBillSupplyAdapter);

                CreateBar(billSupplyRespository.displayBill(billSupplyList),"Supply");
            }else {
                ArrayList<BillSupply> list = new ArrayList<>();

                for (BillSupply b:billSupplyList){
                    if (b.getNameSupplier().toLowerCase().contains(searchText.toLowerCase())){
                        list.add(b);
                    }
                }

                listBillSupplyAdapter = new ListBillSupplyAdapter(BillInformation.this, list);
                listView.setAdapter(listBillSupplyAdapter);

                CreateBar(billSupplyRespository.displayBill(list),"Supply");
            }
        }else if(position == 6){
            if (searchText.isEmpty() || searchText.equals("")){
                listBillWorkshiftAdapter = new ListBillWorkshiftAdapter(BillInformation.this, billWorkshiftList);
                listView.setAdapter(listBillWorkshiftAdapter);

                CreateBar(billWorkshiftRespository.displayBill(billWorkshiftList),"Workshift");
            }else {
                ArrayList<BillWorkshift> list = new ArrayList<>();

                for (BillWorkshift b:billWorkshiftList){
                    if (b.getNameEmploye().toLowerCase().contains(searchText.toLowerCase())){
                        list.add(b);
                    }
                }

                listBillWorkshiftAdapter = new ListBillWorkshiftAdapter(BillInformation.this, list);
                listView.setAdapter(listBillWorkshiftAdapter);

                CreateBar(billWorkshiftRespository.displayBill(list),"Workshift");
            }
        }
    }

    private void layout_atoz_nomral(){
        int colorResourceId = R.color.blue;
        int color = ContextCompat.getColor(BillInformation.this, colorResourceId);
        layout_atoz.setBackgroundColor(color);
        atoz_text.setText("A - Z ");
        atoz_image.setImageResource(R.drawable.transfer_4);
    }

    private void layout_time_nomral(){
        int colorResourceId = R.color.blue;
        int color = ContextCompat.getColor(BillInformation.this, colorResourceId);
        layout_time.setBackgroundColor(color);
        timeImages.setImageResource(R.drawable.transfer_4);
    }

    private void layout_totalpayment_nomral(){
        int colorResourceId = R.color.blue;
        int color = ContextCompat.getColor(BillInformation.this, colorResourceId);
        layout_totalpayment.setBackgroundColor(color);
        timeImages.setImageResource(R.drawable.transfer_4);
    }

    private void Nomarl(int position){
        if (position == 1){
            billEcommerceList = billEcmmerceRespository.getListBillEcommerce(BillInformation.this);
        }else if (position == 2){
            billFacilityList = billFacilityRespository.getListBillFacility(BillInformation.this);
        }else if (position == 3){
            billProductList = billProductRespository.getListBillProduct(BillInformation.this);
        }else if (position == 4){
            billStoreList = billStoreRespository.getListBillStore(BillInformation.this);
        }else if (position == 5){
            billSupplyList = billSupplyRespository.getListBillSupply(BillInformation.this);
        }else if (position == 6){
            billWorkshiftList = billWorkshiftRespository.getListBillWorkshift(BillInformation.this);
        }
    }
}