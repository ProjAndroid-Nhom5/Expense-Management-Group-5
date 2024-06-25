package com.example.expensemanagement.Home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;


public class HomeFragment extends Fragment {

    BarChart barChart1, barChart2;
    private TextView revenueTotal,
            totalRevenueTotal,
            receivablesTotal,
            payablesTotal;

    private BillEcmmerceRespository billEcmmerceRespository;
    private BillFacilityRespository billFacilityRespository;
    private BillProductRespository billProductRespository;
    private BillStoreRespository billStoreRespository;
    private BillSupplyRespository billSupplyRespository;
    private BillWorkshiftRespository billWorkshiftRespository;
    private DatabaseReference billRef;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        revenueTotal = view.findViewById(R.id.revenueTotal);
        totalRevenueTotal = view.findViewById(R.id.totalRevenueTotal);
        receivablesTotal = view.findViewById(R.id.receivablesTotal);
        payablesTotal = view.findViewById(R.id.payablesTotal);

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

        barChart1 = view.findViewById(R.id.barchart1);

        barChart2 = view.findViewById(R.id.barchart2);
    }

    private void fetchBill(int postion) {
        billRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (postion == 1){
                    billEcmmerceRespository.clearListBillEcommerce(requireContext());
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        BillEcommerce billEcommerce = snapshot.getValue(BillEcommerce.class);
                        if (billEcommerce != null) {
                            billEcmmerceRespository.addBillEcommerce(billEcommerce,requireContext());
                        }
                    }
                } else if (postion == 2) {
                    billFacilityRespository.clearListBillFacility(requireContext());
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        BillFacility billFacility = snapshot.getValue(BillFacility.class);
                        if (billFacility != null) {
                            billFacilityRespository.addBillFacility(billFacility,requireContext());
                        }
                    }
                } else if (postion == 3) {
                    billProductRespository.clearListBillProduct(requireContext());
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        BillProduct billProduct = snapshot.getValue(BillProduct.class);
                        if (billProduct != null) {
                            billProductRespository.addBillProduct(billProduct,requireContext());
                        }
                    }
                } else if (postion == 4) {
                    billStoreRespository.clearListBillStore(requireContext());
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        BillStore billStore = snapshot.getValue(BillStore.class);
                        if (billStore != null) {
                            billStoreRespository.addBillStore(billStore,requireContext());
                        }
                    }
                } else if (postion == 5) {
                    billSupplyRespository.clearListBillSupply(requireContext());
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        BillSupply billSupply = snapshot.getValue(BillSupply.class);
                        if (billSupply != null) {
                            billSupplyRespository.addBillSupply(billSupply,requireContext());
                        }
                    }
                }else if (postion == 6) {
                    billWorkshiftRespository.clearListBillWorkshift(requireContext());
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        BillWorkshift billWorkshift = snapshot.getValue(BillWorkshift.class);
                        if (billWorkshift != null) {
                            billWorkshiftRespository.addBillWorkshift(billWorkshift,requireContext());
                        }
                    }
                }

                updateTotalPayment();
                ArrayList<BillEcommerce> billEcommerces = billEcmmerceRespository.getListBillEcommerce(requireContext());
                ArrayList<BarEntry> BillEcommerceBarList = billEcmmerceRespository.displayBill(billEcommerces);

                ArrayList<BillFacility> billFacilities = billFacilityRespository.getListBillFacility(requireContext());
                ArrayList<BarEntry> BillFacilityBarList = billFacilityRespository.displayBill(billFacilities);

                ArrayList<BillStore> billStores = billStoreRespository.getListBillStore(requireContext());
                ArrayList<BarEntry> BillStoreBarList = billStoreRespository.displayBill(billStores);

                ArrayList<BillSupply> billSupplies = billSupplyRespository.getListBillSupply(requireContext());
                ArrayList<BarEntry> BillSupplyBarList = billSupplyRespository.displayBill(billSupplies);

                ArrayList<BillWorkshift> billWorkshifts = billWorkshiftRespository.getListBillWorkshift(requireContext());
                ArrayList<BarEntry> BillWorkshiftBarList = billWorkshiftRespository.displayBill(billWorkshifts);

                ArrayList<BarEntry> Revenue = new  ArrayList<>();
                ArrayList<BarEntry> Expenses = new  ArrayList<>();
                for (int i = 0; i < 12; i++) {
                    BarEntry BillEcommerceEntry = BillEcommerceBarList.get(i);
                    BarEntry BillFacilityEntry = BillFacilityBarList.get(i);
                    BarEntry BillStoreEntry = BillStoreBarList.get(i);
                    BarEntry BillSupplyEntry = BillSupplyBarList.get(i);
                    BarEntry BillWorkshiftEntry = BillWorkshiftBarList.get(i);
                    Revenue.add(new BarEntry(i + 1, BillStoreEntry.getY()+
                            BillEcommerceEntry.getY()-
                            BillFacilityEntry.getY()-
                            BillSupplyEntry.getY()-
                            BillWorkshiftEntry.getY()));
                    Expenses.add(new BarEntry(i + 1, BillFacilityEntry.getY()+
                            BillSupplyEntry.getY()+
                            BillWorkshiftEntry.getY()));
                }
                CreateBar1(Revenue,Expenses);
                CreateBar2(BillEcommerceBarList,BillSupplyBarList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors.

            }
        });
    }

    private void updateTotalPayment() {
        // Lấy tổng số tiền thanh toán trong một luồng nền (ví dụ, sử dụng AsyncTask, Executor, hoặc ViewModel)
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Float ecommerceTotal = billEcmmerceRespository.getTotalPaymentForCurrentMonth(requireContext());
                final Float facilityTotal = billFacilityRespository.getTotalPaymentForCurrentMonth(requireContext());
                final Float storeTotal = billStoreRespository.getTotalPaymentForCurrentMonth(requireContext());
                final Float supplyTotal = billSupplyRespository.getTotalPaymentForCurrentMonth(requireContext());
                final Float workshiftTotal = billWorkshiftRespository.getTotalPaymentForCurrentMonth(requireContext());

                float ecommerce,facility, store,supply ,workshift;
                if (ecommerceTotal == null) {
                    ecommerce = 0f;
                }else {
                    ecommerce = ecommerceTotal;
                }
                if (facilityTotal == null) {
                    facility = 0f;
                }else {
                    facility = facilityTotal;
                }
                if (storeTotal == null) {
                    store = 0f;
                }else {
                    store = storeTotal;
                }
                if (supplyTotal == null) {
                    supply = 0f;
                }else {
                    supply = supplyTotal;
                }
                if (workshiftTotal == null) {
                    workshift = 0f;
                }else {
                    workshift = workshiftTotal;
                }
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        float totalRevenueTotalForCurrentMonth = ecommerce+store;
                        String formattedPayment = String.format(Locale.getDefault(), "%.2f", totalRevenueTotalForCurrentMonth);
                        totalRevenueTotal.setText(formattedPayment+ " $");
                        float RevenueTotalForCurrentMonth = ecommerce+store-facility-supply-workshift;
                        formattedPayment = String.format(Locale.getDefault(), "%.2f", RevenueTotalForCurrentMonth);
                        revenueTotal.setText(formattedPayment+ " $");
                        float receivablesTotalForCurrentMonth = ecommerce;
                        formattedPayment = String.format(Locale.getDefault(), "%.2f", receivablesTotalForCurrentMonth);
                        receivablesTotal.setText(formattedPayment+ " $");
                        float payablesTotalForCurrentMonth = supply;
                        formattedPayment = String.format(Locale.getDefault(), "%.2f", payablesTotalForCurrentMonth);
                        payablesTotal.setText(formattedPayment+ " $");
                    }
                });
            }
        }).start();
    }
    private void CreateBar1(ArrayList<BarEntry> barEntries1,ArrayList<BarEntry> barEntries2){
        BarDataSet barDataSet1 = new BarDataSet(barEntries1,"Revenue");
        barDataSet1.setColors(Color.RED);

        BarDataSet barDataSet2 = new BarDataSet(barEntries2,"Expenses");
        barDataSet2.setColor(Color.BLUE);

        BarData barData1 = new BarData(barDataSet1,barDataSet2);
        barChart1.setData(barData1);

        String[] days = new String[]{"January","February","March","April",
                "May","June","July","August","September","October","November","December"};
        XAxis xAxis = barChart1.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(days));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);

        barChart1.setDragEnabled(true);
        barChart1.setVisibleXRangeMaximum(3);

        float barSpace = 0.1f;
        float groupSpace = 0.5f;
        barData1.setBarWidth(0.15f);

        barChart1.getXAxis().setAxisMinimum(0);
        barChart1.groupBars(0,groupSpace,barSpace);

        barChart1.invalidate();
    }

    private void CreateBar2(ArrayList<BarEntry> barEntries3,ArrayList<BarEntry> barEntries4) {
        BarDataSet barDataSet3 = new BarDataSet(barEntries3, "Debt Collection");
        barDataSet3.setColor(Color.RED);

        BarDataSet barDataSet4 = new BarDataSet(barEntries4, "Debt Paid");
        barDataSet4.setColor(Color.BLUE);

        BarData barData2 = new BarData(barDataSet3, barDataSet4);
        barChart2.setData(barData2);

        String[] days = new String[]{"January", "February", "March", "April",
                "May", "June", "July", "August", "September", "October", "November", "December"};
        XAxis xAxis2 = barChart2.getXAxis();
        xAxis2.setValueFormatter(new IndexAxisValueFormatter(days));
        xAxis2.setCenterAxisLabels(true);
        xAxis2.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis2.setGranularity(1);
        xAxis2.setGranularityEnabled(true);

        barChart2.setDragEnabled(true);
        barChart2.setVisibleXRangeMaximum(3);

        float barSpace2 = 0.1f;
        float groupSpace2 = 0.5f;
        barData2.setBarWidth(0.15f);

        barChart2.getXAxis().setAxisMinimum(0);
        barChart2.groupBars(0, groupSpace2, barSpace2);

        barChart2.invalidate();
    }
}