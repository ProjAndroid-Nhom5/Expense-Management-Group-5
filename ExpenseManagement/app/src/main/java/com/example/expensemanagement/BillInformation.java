package com.example.expensemanagement;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class BillInformation extends AppCompatActivity {
    //
    ListBillEcommerceAdapter listBillEcommerceAdapter;
    ArrayList<ListBillEcommerceData> listBillEcommerceDataArrayList = new ArrayList<>();
    ListBillEcommerceData listBillEcommerceData;
    //
    ListBillFacitilyAdapter listBillFacitilyAdapter;
    ArrayList<ListBillFacilityData> listBillFacilityDataArrayList = new ArrayList<>();
    ListBillFacilityData listBillFacilityData;
    //
    ListBillProductAdapter listBillProductAdapter;
    ArrayList<ListBillProductData> listBillProductDataArrayList = new ArrayList<>();
    ListBillProductData listBillProductData;
    //
    ListBillStoreData listBillStoreData;
    ArrayList<ListBillStoreData> listBillStoreDataArrayList = new ArrayList<>();
    ListBillStoreAdapter listBillStoreAdapter;
    //
    ListBillSupplyAdapter listBillSupplyAdapter;
    ArrayList<ListBillSupplyData> listBillSupplyDataArrayList = new ArrayList<>();
    ListBillSupplyData listBillSupplyData;
    //
    ListBillWorkshiftAdapter listBillWorkshiftAdapter;
    ArrayList<ListBillWorkshiftData> listBillWorkshiftDataArrayList = new ArrayList<>();
    ListBillWorkshiftData listBillWorkshiftData;
    ImageView exit_icon;
    ListView listView;
    BarChart barChart;

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

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.invalidate();

        exit_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        int position = intent.getIntExtra("potision",0);
        if (position == 1){
            BarDataSet barDataSet1 = new BarDataSet(barEntries1(),"Ecommerce");
            barDataSet1.setColors(Color.argb(188,119,193,202));

            BarData barData1 = new BarData();
            barData1.addDataSet(barDataSet1);
            barChart.setData(barData1);

            String[] id = {"Pasta", "Maggi"};
            String[] nameEcommerce = {"Pasta", "Maggi"};
            String[] date = {"30 mins", "2 mins"};
            String[] nameCustomer = {"30 mins", "2 mins"};
            String[] phone = {"Pasta", "Maggi"};
            String[] address = {"30 mins", "2 mins"};
            String[] paymentTime = {"Pasta", "Maggi"};
            Float[] productCost = {2323.3f,2323.3f};
            Float[] shipCost = {2323.3f,2323.3f};
            Float[] totalPayment = {2323.3f,2323.3f};
            for (int i = 0; i < id.length; i++){
                listBillEcommerceData = new ListBillEcommerceData(id[i],
                        nameEcommerce[i],
                        date[i],
                        nameCustomer[i],
                        phone[i],
                        address[i],
                        paymentTime[i],
                        productCost[i],
                        shipCost[i],
                        totalPayment[i]);
                listBillEcommerceDataArrayList.add(listBillEcommerceData);
            }
            listBillEcommerceAdapter = new ListBillEcommerceAdapter(BillInformation.this, listBillEcommerceDataArrayList);
            listView.setAdapter(listBillEcommerceAdapter);
            listView.setClickable(true);
        }
        else if (position == 2){
            BarDataSet barDataSet1 = new BarDataSet(barEntries1(),"Facility");
            barDataSet1.setColors(Color.argb(188,119,193,202));

            BarData barData1 = new BarData();
            barData1.addDataSet(barDataSet1);
            barChart.setData(barData1);

            String[] id = {"Pasta", "Maggi"};
            String[] nameFacility = {"Pasta", "Maggi"};
            String[] date = {"30 mins", "2 mins"};
            String[] nameStore = {"30 mins", "2 mins"};
            String[] transactionMethod = {"Pasta", "Maggi"};
            Float[] totalPayment = {2323.3f,2323.3f};
            for (int i = 0; i < id.length; i++){
                listBillFacilityData = new ListBillFacilityData(id[i],
                        nameFacility[i],
                        date[i],
                        nameStore[i],
                        transactionMethod[i],
                        totalPayment[i]);
                listBillFacilityDataArrayList.add(listBillFacilityData);
            }
            listBillFacitilyAdapter = new ListBillFacitilyAdapter(BillInformation.this, listBillFacilityDataArrayList);
            listView.setAdapter(listBillFacitilyAdapter);
            listView.setClickable(true);
        }else if (position == 3){
            BarDataSet barDataSet1 = new BarDataSet(barEntries1(),"Product");
            barDataSet1.setColors(Color.argb(188,119,193,202));

            BarData barData1 = new BarData();
            barData1.addDataSet(barDataSet1);
            barChart.setData(barData1);

            String[] bill_ProductID = {"Pasta", "Maggi"};
            String[] nameProduct = {"Pasta", "Maggi"};
            String[] billID = {"30 mins", "2 mins"};
            Float[] Quantity = {2323.3f,2323.3f};
            for (int i = 0; i < bill_ProductID.length; i++){
                listBillProductData = new ListBillProductData(bill_ProductID[i],
                        nameProduct[i],
                        billID[i],
                        Quantity[i]);
                listBillProductDataArrayList.add(listBillProductData);
            }
            listBillProductAdapter = new ListBillProductAdapter(BillInformation.this, listBillProductDataArrayList);
            listView.setAdapter(listBillProductAdapter);
            listView.setClickable(true);
        }else if (position == 4){
            BarDataSet barDataSet1 = new BarDataSet(barEntries1(),"Store");
            barDataSet1.setColors(Color.argb(188,119,193,202));

            BarData barData1 = new BarData();
            barData1.addDataSet(barDataSet1);
            barChart.setData(barData1);

            String[] id = {"Pasta", "Maggi"};
            String[] idEmploye = {"Pasta", "Maggi"};
            String[] date = {"30 mins", "2 mins"};
            String[] nameProduct = {"30 mins", "2 mins"};
            String[] nameEmploye = {"Pasta", "Maggi"};
            Float[] productCost = {2323.3f,2323.3f};
            Float[] total = {2323.3f,2323.3f};

            for (int i = 0; i < id.length; i++){
                listBillStoreData = new ListBillStoreData(id[i],
                        idEmploye[i],
                        date[i],
                        nameProduct[i],
                        nameEmploye[i],
                        productCost[i],
                        total[i]);
                listBillStoreDataArrayList.add(listBillStoreData);
            }
            listBillStoreAdapter = new ListBillStoreAdapter(BillInformation.this, listBillStoreDataArrayList);
            listView.setAdapter(listBillStoreAdapter);
            listView.setClickable(true);
        }else if (position == 5){
            BarDataSet barDataSet1 = new BarDataSet(barEntries1(),"Supply");
            barDataSet1.setColors(Color.argb(188,119,193,202));

            BarData barData1 = new BarData();
            barData1.addDataSet(barDataSet1);
            barChart.setData(barData1);

            String[] id = {"Pasta", "Maggi"};
            String[] nameSupplier = {"Pasta", "Maggi"};
            String[] date = {"30 mins", "2 mins"};
            String[] number = {"30 mins", "2 mins"};
            Float[] productCost = {2323.3f,2323.3f};
            Float[] shipCost = {2323.3f,2323.3f};
            Float[] total = {2323.3f,2323.3f};
            for (int i = 0; i < id.length; i++){
                listBillSupplyData = new ListBillSupplyData(id[i],
                        nameSupplier[i],
                        date[i],
                        number[i],
                        productCost[i],
                        shipCost[i],
                        total[i]);
                listBillSupplyDataArrayList.add(listBillSupplyData);
            }
            listBillSupplyAdapter = new ListBillSupplyAdapter(BillInformation.this, listBillSupplyDataArrayList);
            listView.setAdapter(listBillSupplyAdapter);
            listView.setClickable(true);
        }else if (position == 6){
            BarDataSet barDataSet1 = new BarDataSet(barEntries1(),"Workshift");
            barDataSet1.setColors(Color.argb(188,119,193,202));

            BarData barData1 = new BarData();
            barData1.addDataSet(barDataSet1);
            barChart.setData(barData1);

            String[] id = {"Pasta", "Maggi"};
            String[] nameEmploye = {"Pasta", "Maggi"};
            String[] date = {"30 mins", "2 mins"};
            Float[] totalWorked = {2323.3f,2323.3f};
            Float[] salary = {2323.3f,2323.3f};
            Float[] pTV = {2323.3f,2323.3f};
            Float[] total = {2323.3f,2323.3f};
            for (int i = 0; i < id.length; i++){
                listBillWorkshiftData = new ListBillWorkshiftData(id[i],
                        nameEmploye[i],
                        date[i],
                        totalWorked[i],
                        salary[i],
                        pTV[i],
                        total[i]);
                listBillWorkshiftDataArrayList.add(listBillWorkshiftData);
            }
            listBillWorkshiftAdapter = new ListBillWorkshiftAdapter(BillInformation.this, listBillWorkshiftDataArrayList);
            listView.setAdapter(listBillWorkshiftAdapter);
            listView.setClickable(true);
        }
    }
    private ArrayList<BarEntry> barEntries1(){
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1,2000));
        barEntries.add(new BarEntry(2,791));
        barEntries.add(new BarEntry(3,630));
        barEntries.add(new BarEntry(4,450));
        barEntries.add(new BarEntry(5,2724));
        barEntries.add(new BarEntry(6,500));
        barEntries.add(new BarEntry(7,2173));
        return barEntries;
    }
}