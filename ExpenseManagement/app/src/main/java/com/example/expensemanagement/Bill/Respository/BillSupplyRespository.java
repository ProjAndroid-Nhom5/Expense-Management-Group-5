package com.example.expensemanagement.Bill.Respository;


import android.content.Context;

import com.example.expensemanagement.Bill.Database.BillStoreDatabase;
import com.example.expensemanagement.Bill.Database.BillSupplyDatabase;
import com.example.expensemanagement.Bill.Model.BarBill;
import com.example.expensemanagement.Bill.Model.BillStore;
import com.example.expensemanagement.Bill.Model.BillSupply;
import com.github.mikephil.charting.data.BarEntry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class BillSupplyRespository {
    private static ArrayList<BillSupply> listBillSupply  = new ArrayList<>();

    public static ArrayList<BillSupply> getListBillSupply(Context context) {
        ArrayList<BillSupply> list = (ArrayList<BillSupply>) BillSupplyDatabase
                .getInstance(context)
                .billSupplyDAO()
                .getListBillSupply();
        setListBillSupply(list);
        return list;
    }

    public static void setListBillSupply(ArrayList<BillSupply> listBill) {
        BillSupplyRespository.listBillSupply = listBill;
    }

    public static ArrayList<BillSupply> getListFromAToZ(Context context) {
        ArrayList<BillSupply> list = (ArrayList<BillSupply>) BillSupplyDatabase
                .getInstance(context)
                .billSupplyDAO()
                .getListSortedByNameASC();
        setListBillSupply(list);
        return list;
    }

    public static ArrayList<BillSupply> getListFromZToA(Context context) {
        ArrayList<BillSupply> list = (ArrayList<BillSupply>) BillSupplyDatabase
                .getInstance(context)
                .billSupplyDAO()
                .getListSortedByTotalPaymentDesc();
        setListBillSupply(list);
        return list;
    }

    public static ArrayList<BillSupply> getListFromTotalPaymentASC(Context context) {
        ArrayList<BillSupply> list = (ArrayList<BillSupply>) BillSupplyDatabase
                .getInstance(context)
                .billSupplyDAO()
                .getListSortedByTotalPaymentASC();
        setListBillSupply(list);
        return list;
    }

    public static ArrayList<BillSupply> getListFromTotalPaymentDESC(Context context) {
        ArrayList<BillSupply> list = (ArrayList<BillSupply>) BillSupplyDatabase
                .getInstance(context)
                .billSupplyDAO()
                .getListSortedByTotalPaymentDesc();
        setListBillSupply(list);
        return list;
    }

    public static ArrayList<BillSupply> getListFromTimeASC(Context context) {
        ArrayList<BillSupply> list = (ArrayList<BillSupply>) BillSupplyDatabase
                .getInstance(context)
                .billSupplyDAO()
                .getListBillSupply();
        Collections.sort(list, new Comparator<BillSupply>() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

            @Override
            public int compare(BillSupply o1, BillSupply o2) {
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

        setListBillSupply(list);
        return list;
    }

    public static ArrayList<BillSupply> getListFromTimeDESC(Context context) {
        ArrayList<BillSupply> list = (ArrayList<BillSupply>) BillSupplyDatabase
                .getInstance(context)
                .billSupplyDAO()
                .getListBillSupply();

        Collections.sort(list, new Comparator<BillSupply>() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

            @Override
            public int compare(BillSupply o1, BillSupply o2) {
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

        setListBillSupply(list);
        return list;
    }

    public ArrayList<BarEntry> displayBill(ArrayList<BillSupply> list) {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<BarBill> barBills = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        for (BillSupply b : list) {
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
                BarBill barBill = new BarBill(billDate, b.getTotal());
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

    public void addBillSupply(BillSupply billSupply, Context context){
        listBillSupply.add(billSupply);
        BillSupplyDatabase.getInstance(context).billSupplyDAO().insertBillSupply(billSupply);
    }
    public void removeBillSupply(BillSupply billSupply){
        listBillSupply.remove(billSupply);
    }

    public void clearListBillSupply(Context context){
        listBillSupply.clear();
        BillSupplyDatabase.getInstance(context).billSupplyDAO().deleteAllBillSupply();
    }

    public Float getTotalPaymentForCurrentMonth(Context context){
        ArrayList<BillSupply> list = (ArrayList<BillSupply>) BillSupplyDatabase
                .getInstance(context)
                .billSupplyDAO()
                .getListBillSupply();
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        float total = 0f;
        for (BillSupply b : list) {
            Date billDate = null;
            try {
                billDate = sdf.parse(b.getDate());
                if (billDate.getYear() == currentYear - 1900) { // Month is 0-based
                    total += b.getTotal();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return total;
    }
}
