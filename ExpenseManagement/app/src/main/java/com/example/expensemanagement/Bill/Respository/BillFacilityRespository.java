package com.example.expensemanagement.Bill.Respository;


import android.content.Context;

import com.example.expensemanagement.Bill.Database.BillEcommerceDatabase;
import com.example.expensemanagement.Bill.Database.BillFacitilyDatabase;
import com.example.expensemanagement.Bill.Model.BarBill;
import com.example.expensemanagement.Bill.Model.BillEcommerce;
import com.example.expensemanagement.Bill.Model.BillFacility;
import com.github.mikephil.charting.data.BarEntry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class BillFacilityRespository {
    private static ArrayList<BillFacility> listBillFacility  = new ArrayList<>();

    public static ArrayList<BillFacility> getListBillFacility(Context context) {
        ArrayList<BillFacility> list = (ArrayList<BillFacility>) BillFacitilyDatabase
                .getInstance(context)
                .billFacilityDAO()
                .getListBillFacility();
        setListBillFacility(list);
        return list;
    }

    public static void setListBillFacility(ArrayList<BillFacility> listBill) {
        BillFacilityRespository.listBillFacility = listBill;
    }

    public static ArrayList<BillFacility> getListFromAToZ(Context context) {
        ArrayList<BillFacility> list = (ArrayList<BillFacility>) BillFacitilyDatabase
                .getInstance(context)
                .billFacilityDAO()
                .getListSortedByNameASC();
        setListBillFacility(list);
        return list;
    }

    public static ArrayList<BillFacility> getListFromZToA(Context context) {
        ArrayList<BillFacility> list = (ArrayList<BillFacility>) BillFacitilyDatabase
                .getInstance(context)
                .billFacilityDAO()
                .getListSortedByTotalPaymentDesc();
        setListBillFacility(list);
        return list;
    }

    public static ArrayList<BillFacility> getListFromTotalPaymentASC(Context context) {
        ArrayList<BillFacility> list = (ArrayList<BillFacility>) BillFacitilyDatabase
                .getInstance(context)
                .billFacilityDAO()
                .getListSortedByTotalPaymentASC();
        setListBillFacility(list);
        return list;
    }

    public static ArrayList<BillFacility> getListFromTotalPaymentDESC(Context context) {
        ArrayList<BillFacility> list = (ArrayList<BillFacility>) BillFacitilyDatabase
                .getInstance(context)
                .billFacilityDAO()
                .getListSortedByTotalPaymentDesc();
        setListBillFacility(list);
        return list;
    }

    public static ArrayList<BillFacility> getListFromTimeASC(Context context) {
        ArrayList<BillFacility> list = (ArrayList<BillFacility>) BillFacitilyDatabase
                .getInstance(context)
                .billFacilityDAO()
                .getListBillFacility();
        Collections.sort(list, new Comparator<BillFacility>() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

            @Override
            public int compare(BillFacility o1, BillFacility o2) {
                try {
                    Date date1 = dateFormat.parse(o1.getDate());
                    Date date2 = dateFormat.parse(o2.getDate());
                    return date1.compareTo(date2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });

        setListBillFacility(list);
        return list;
    }

    public static ArrayList<BillFacility> getListFromTimeDESC(Context context) {
        ArrayList<BillFacility> list = (ArrayList<BillFacility>) BillFacitilyDatabase
                .getInstance(context)
                .billFacilityDAO()
                .getListBillFacility();

        Collections.sort(list, new Comparator<BillFacility>() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

            @Override
            public int compare(BillFacility o1, BillFacility o2) {
                try {
                    Date date1 = dateFormat.parse(o1.getDate());
                    Date date2 = dateFormat.parse(o2.getDate());
                    return date2.compareTo(date1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });

        setListBillFacility(list);
        return list;
    }

    public ArrayList<BarEntry> displayBill(ArrayList<BillFacility> list) {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<BarBill> barBills = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        for (BillFacility b : list) {
            boolean check = false;
            Date billDate = null;
            try {
                billDate = sdf.parse(b.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (billDate == null) continue;

            for (BarBill bar : barBills) {
                if (bar.getDate().equals(billDate)) {
                    check = true;
                    break;
                }
            }

            if (!check) {
                BarBill barBill = new BarBill(billDate, b.getTotalPayment());
                barBills.add(barBill);
            }
        }

        for (int i = 1; i <= 12; i++) {
            float total = 0f;
            for (BarBill bar : barBills) {
                if (bar.getDate().getMonth() + 1 == i && bar.getDate().getYear() == currentYear - 1900) { // Month is 0-based
                    total += bar.getTotal();
                }
            }
            barEntries.add(new BarEntry(i, total));
        }

        return barEntries;
    }

    public void addBillFacility(BillFacility billFacility, Context context){
        listBillFacility.add(billFacility);
        BillFacitilyDatabase.getInstance(context).billFacilityDAO().insertBillFacility(billFacility);
    }
    public void removeBillFacility(BillFacility billFacility){
        listBillFacility.remove(billFacility);
    }

    public void clearListBillFacility(Context context){
        listBillFacility.clear();
        BillFacitilyDatabase.getInstance(context).billFacilityDAO().deleteAllBillFacility();
    }
}
