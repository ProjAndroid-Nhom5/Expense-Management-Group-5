package com.example.expensemanagement.Home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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
import com.example.expensemanagement.Home.Adapter.DateAdapter;
import com.example.expensemanagement.Home.Inventory.Data;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;


public class HomeFragment extends Fragment {

    private BarChart barChart1, barChart2;
    private TextView revenueTotal,
            totalRevenueTotal,
            receivablesTotal,
            payablesTotal;
    private Spinner timeTxt;
    private DateAdapter dateAdapter;

    private BillEcmmerceRespository billEcmmerceRespository;
    private BillFacilityRespository billFacilityRespository;
    private BillProductRespository billProductRespository;
    private BillStoreRespository billStoreRespository;
    private BillSupplyRespository billSupplyRespository;
    private BillWorkshiftRespository billWorkshiftRespository;
    private DatabaseReference billRef;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private int potionSpiner = 0;

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

        timeTxt = view.findViewById(R.id.timeTxt);

        dateAdapter = new DateAdapter(requireContext(), Data.getDateList());

        timeTxt.setAdapter(dateAdapter);

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

        timeTxt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != potionSpiner) {
                    potionSpiner = position;
                    updateTotalPayment();
                    updateChart();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Xử lý khi không có mục nào được chọn (tùy chọn không cần thiết)
            }
        });

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
                updateChart();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors.

            }
        });
    }

    private void updateTotalPayment() {
        // Lấy tổng số tiền thanh toán trong một luồng nền (ví dụ, sử dụng AsyncTask, Executor, hoặc ViewModel)
        Float ecommerceTotal = 0f;
        Float facilityTotal = 0f;
        Float storeTotal = 0f;
        Float supplyTotal = 0f;
        Float workshiftTotal = 0f;

        if (potionSpiner == 0){
            ecommerceTotal = billEcmmerceRespository.getTotalPaymentForCurrentYear(requireContext());
            facilityTotal = billFacilityRespository.getTotalPaymentForCurrentYear(requireContext());
            storeTotal = billStoreRespository.getTotalPaymentForCurrentYear(requireContext());
            supplyTotal = billSupplyRespository.getTotalPaymentForCurrentYear(requireContext());
            workshiftTotal = billWorkshiftRespository.getTotalPaymentForCurrentYear(requireContext());
        }else if (potionSpiner == 1){
            ecommerceTotal = billEcmmerceRespository.getTotalPaymentForCurrentMonth(requireContext());
            facilityTotal = billFacilityRespository.getTotalPaymentForCurrentMonth(requireContext());
            storeTotal = billStoreRespository.getTotalPaymentForCurrentMonth(requireContext());
            supplyTotal = billSupplyRespository.getTotalPaymentForCurrentMonth(requireContext());
            workshiftTotal = billWorkshiftRespository.getTotalPaymentForCurrentMonth(requireContext());
        }else if (potionSpiner == 2){
            ecommerceTotal = billEcmmerceRespository.getTotalPaymentForCurrentWeek(requireContext());
            facilityTotal = billFacilityRespository.getTotalPaymentForCurrentWeek(requireContext());
            storeTotal = billStoreRespository.getTotalPaymentForCurrentWeek(requireContext());
            supplyTotal = billSupplyRespository.getTotalPaymentForCurrentWeek(requireContext());
            workshiftTotal = billWorkshiftRespository.getTotalPaymentForCurrentWeek(requireContext());
        }

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
    private void updateChart() {
        // Lấy tổng số tiền thanh toán trong một luồng nền (ví dụ, sử dụng AsyncTask, Executor, hoặc ViewModel)
        List<BillEcommerce> billEcommerces = new CopyOnWriteArrayList<>(billEcmmerceRespository.getListBillEcommerce(requireContext()));
        List<BillFacility> billFacilities = new CopyOnWriteArrayList<>(billFacilityRespository.getListBillFacility(requireContext()));
        List<BillStore> billStores = new CopyOnWriteArrayList<>(billStoreRespository.getListBillStore(requireContext()));
        List<BillSupply> billSupplies = new CopyOnWriteArrayList<>(billSupplyRespository.getListBillSupply(requireContext()));
        List<BillWorkshift> billWorkshifts = new CopyOnWriteArrayList<>(billWorkshiftRespository.getListBillWorkshift(requireContext()));

        final ArrayList<BarEntry> Revenue = new ArrayList<>();
        final ArrayList<BarEntry> Expenses = new ArrayList<>();

        if (potionSpiner == 0) {
            final ArrayList<BarEntry> BillEcommerceBarList = billEcmmerceRespository.displayBillForCurrentYear(new ArrayList<>(billEcommerces));
            final ArrayList<BarEntry> BillFacilityBarList = billFacilityRespository.displayBillForCurrentYear(new ArrayList<>(billFacilities));
            final ArrayList<BarEntry> BillStoreBarList = billStoreRespository.displayBillForCurrentYear(new ArrayList<>(billStores));
            final ArrayList<BarEntry> BillSupplyBarList = billSupplyRespository.displayBillForCurrentYear(new ArrayList<>(billSupplies));
            final ArrayList<BarEntry> BillWorkshiftBarList = billWorkshiftRespository.displayBillForCurrentYear(new ArrayList<>(billWorkshifts));

            for (int i = 0; i < 12; i++) {
                BarEntry BillEcommerceEntry = BillEcommerceBarList.get(i);
                BarEntry BillFacilityEntry = BillFacilityBarList.get(i);
                BarEntry BillStoreEntry = BillStoreBarList.get(i);
                BarEntry BillSupplyEntry = BillSupplyBarList.get(i);
                BarEntry BillWorkshiftEntry = BillWorkshiftBarList.get(i);
                Revenue.add(new BarEntry(i + 1, BillStoreEntry.getY() +
                        BillEcommerceEntry.getY() -
                        BillFacilityEntry.getY() -
                        BillSupplyEntry.getY() -
                        BillWorkshiftEntry.getY()));
                Expenses.add(new BarEntry(i + 1, BillFacilityEntry.getY() +
                        BillSupplyEntry.getY() +
                        BillWorkshiftEntry.getY()));
            }

            CreateBar1(Revenue, Expenses);
            CreateBar2(BillEcommerceBarList, BillSupplyBarList);
        }else if (potionSpiner == 1) {
            final ArrayList<BarEntry> BillEcommerceBarList = billEcmmerceRespository.displayBillForCurrentMonth(new ArrayList<>(billEcommerces));
            final ArrayList<BarEntry> BillFacilityBarList = billFacilityRespository.displayBillForCurrentMonth(new ArrayList<>(billFacilities));
            final ArrayList<BarEntry> BillStoreBarList = billStoreRespository.displayBillForCurrentMonth(new ArrayList<>(billStores));
            final ArrayList<BarEntry> BillSupplyBarList = billSupplyRespository.displayBillForCurrentMonth(new ArrayList<>(billSupplies));
            final ArrayList<BarEntry> BillWorkshiftBarList = billWorkshiftRespository.displayBillForCurrentMonth(new ArrayList<>(billWorkshifts));

            Calendar calendar = Calendar.getInstance();
            int lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // Ngày cuối cùng của tháng

            for (int i = 0; i < lastDayOfMonth; i++) {
                BarEntry BillEcommerceEntry = BillEcommerceBarList.get(i);
                BarEntry BillFacilityEntry = BillFacilityBarList.get(i);
                BarEntry BillStoreEntry = BillStoreBarList.get(i);
                BarEntry BillSupplyEntry = BillSupplyBarList.get(i);
                BarEntry BillWorkshiftEntry = BillWorkshiftBarList.get(i);
                Revenue.add(new BarEntry(i + 1, BillStoreEntry.getY() +
                        BillEcommerceEntry.getY() -
                        BillFacilityEntry.getY() -
                        BillSupplyEntry.getY() -
                        BillWorkshiftEntry.getY()));
                Expenses.add(new BarEntry(i + 1, BillFacilityEntry.getY() +
                        BillSupplyEntry.getY() +
                        BillWorkshiftEntry.getY()));
            }

            CreateBar1(Revenue, Expenses);
            CreateBar2(BillEcommerceBarList, BillSupplyBarList);
        }else if (potionSpiner == 2) {
            final ArrayList<BarEntry> BillEcommerceBarList = billEcmmerceRespository.displayBillForCurrentWeek(new ArrayList<>(billEcommerces));
            final ArrayList<BarEntry> BillFacilityBarList = billFacilityRespository.displayBillForCurrentWeek(new ArrayList<>(billFacilities));
            final ArrayList<BarEntry> BillStoreBarList = billStoreRespository.displayBillForCurrentWeek(new ArrayList<>(billStores));
            final ArrayList<BarEntry> BillSupplyBarList = billSupplyRespository.displayBillForCurrentWeek(new ArrayList<>(billSupplies));
            final ArrayList<BarEntry> BillWorkshiftBarList = billWorkshiftRespository.displayBillForCurrentWeek(new ArrayList<>(billWorkshifts));

            for (int i = 0; i < 7; i++) {
                BarEntry BillEcommerceEntry = BillEcommerceBarList.get(i);
                BarEntry BillFacilityEntry = BillFacilityBarList.get(i);
                BarEntry BillStoreEntry = BillStoreBarList.get(i);
                BarEntry BillSupplyEntry = BillSupplyBarList.get(i);
                BarEntry BillWorkshiftEntry = BillWorkshiftBarList.get(i);
                Revenue.add(new BarEntry(i + 1, BillStoreEntry.getY() +
                        BillEcommerceEntry.getY() -
                        BillFacilityEntry.getY() -
                        BillSupplyEntry.getY() -
                        BillWorkshiftEntry.getY()));
                Expenses.add(new BarEntry(i + 1, BillFacilityEntry.getY() +
                        BillSupplyEntry.getY() +
                        BillWorkshiftEntry.getY()));
            }

            CreateBar1(Revenue, Expenses);
            CreateBar2(BillEcommerceBarList, BillSupplyBarList);
        }
    }

    private void CreateBar1(ArrayList<BarEntry> barEntries1,ArrayList<BarEntry> barEntries2){
        BarDataSet barDataSet1 = new BarDataSet(barEntries1,"Revenue");
        barDataSet1.setColors(Color.RED);

        BarDataSet barDataSet2 = new BarDataSet(barEntries2,"Expenses");
        barDataSet2.setColor(Color.BLUE);

        BarData barData1 = new BarData(barDataSet1,barDataSet2);
        barChart1.setData(barData1);

        XAxis xAxis = barChart1.getXAxis();
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

        if(potionSpiner == 0){
            String[] days = new String[]{"January","February","March","April",
                    "May","June","July","August","September","October","November","December"};
            xAxis.setValueFormatter(new IndexAxisValueFormatter(days));
        }else if(potionSpiner == 1){
            Calendar calendar = Calendar.getInstance();
            int lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // Ngày cuối cùng của tháng
            int currentMonth = calendar.get(Calendar.MONTH) + 1; // Tháng hiện tại
            List<String> daysList = new ArrayList<>();
            for (int i = 1; i <= lastDayOfMonth;i++){
                daysList.add(i+"/"+currentMonth);
            }
            String[] days = daysList.toArray(new String[0]);
            xAxis.setValueFormatter(new IndexAxisValueFormatter(days));
        }else if(potionSpiner == 2){
            String[] days = new String[]{"Sunday","Monday","Tuesday","Wednesday","Thursday",
                    "Friday","Saturday"};
            xAxis.setValueFormatter(new IndexAxisValueFormatter(days));
        }

        barChart1.invalidate();
    }

    private void CreateBar2(ArrayList<BarEntry> barEntries3,ArrayList<BarEntry> barEntries4) {
        BarDataSet barDataSet3 = new BarDataSet(barEntries3, "Debt Collection");
        barDataSet3.setColor(Color.RED);

        BarDataSet barDataSet4 = new BarDataSet(barEntries4, "Debt Paid");
        barDataSet4.setColor(Color.BLUE);

        BarData barData2 = new BarData(barDataSet3, barDataSet4);
        barChart2.setData(barData2);

        XAxis xAxis2 = barChart2.getXAxis();
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

        if(potionSpiner == 0){
            String[] days = new String[]{"January","February","March","April",
                    "May","June","July","August","September","October","November","December"};
            xAxis2.setValueFormatter(new IndexAxisValueFormatter(days));
        }else if(potionSpiner == 1){
            Calendar calendar = Calendar.getInstance();
            int lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // Ngày cuối cùng của tháng
            int currentMonth = calendar.get(Calendar.MONTH) + 1; // Tháng hiện tại
            List<String> daysList = new ArrayList<>();
            for (int i = 1; i <= lastDayOfMonth;i++){
                daysList.add(i+"/"+currentMonth);
            }
            String[] days = daysList.toArray(new String[0]);
            xAxis2.setValueFormatter(new IndexAxisValueFormatter(days));
        }else if(potionSpiner == 2){
            String[] days = new String[]{"Sunday","Monday","Tuesday","Wednesday","Thursday",
                    "Friday","Saturday"};
            xAxis2.setValueFormatter(new IndexAxisValueFormatter(days));
        }

        barChart2.invalidate();
    }
}