package com.example.expensemanagement.Bill.Respository;


import android.content.Context;

import com.example.expensemanagement.Bill.Database.BillStoreDatabase;
import com.example.expensemanagement.Bill.Database.BillSupplyDatabase;
import com.example.expensemanagement.Bill.Database.BillWorkshiftDatabase;
import com.example.expensemanagement.Bill.Model.BarBill;
import com.example.expensemanagement.Bill.Model.BillStore;
import com.example.expensemanagement.Bill.Model.BillSupply;
import com.example.expensemanagement.Bill.Model.BillWorkshift;
import com.github.mikephil.charting.data.BarEntry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class BillWorkshiftRespository {
    private static ArrayList<BillWorkshift> listBillWorkshift  = new ArrayList<>();

    public static ArrayList<BillWorkshift> getListBillWorkshift(Context context) {
        ArrayList<BillWorkshift> list = (ArrayList<BillWorkshift>) BillWorkshiftDatabase
                .getInstance(context)
                .billWorkshiftDAO()
                .getListBillWorkshift();
        setListBillWorkshift(list);
        return list;
    }

    public static void setListBillWorkshift(ArrayList<BillWorkshift> listBill) {
        BillWorkshiftRespository.listBillWorkshift = listBill;
    }

    public static ArrayList<BillWorkshift> getListFromAToZ(Context context) {
        ArrayList<BillWorkshift> list = (ArrayList<BillWorkshift>) BillWorkshiftDatabase
                .getInstance(context)
                .billWorkshiftDAO()
                .getListSortedByNameASC();
        setListBillWorkshift(list);
        return list;
    }

    public static ArrayList<BillWorkshift> getListFromZToA(Context context) {
        ArrayList<BillWorkshift> list = (ArrayList<BillWorkshift>) BillWorkshiftDatabase
                .getInstance(context)
                .billWorkshiftDAO()
                .getListSortedByTotalPaymentDesc();
        setListBillWorkshift(list);
        return list;
    }

    public static ArrayList<BillWorkshift> getListFromTotalPaymentASC(Context context) {
        ArrayList<BillWorkshift> list = (ArrayList<BillWorkshift>) BillWorkshiftDatabase
                .getInstance(context)
                .billWorkshiftDAO()
                .getListSortedByTotalPaymentASC();
        setListBillWorkshift(list);
        return list;
    }

    public static ArrayList<BillWorkshift> getListFromTotalPaymentDESC(Context context) {
        ArrayList<BillWorkshift> list = (ArrayList<BillWorkshift>) BillWorkshiftDatabase
                .getInstance(context)
                .billWorkshiftDAO()
                .getListSortedByTotalPaymentDesc();
        setListBillWorkshift(list);
        return list;
    }

    public static ArrayList<BillWorkshift> getListFromTimeASC(Context context) {
        ArrayList<BillWorkshift> list = (ArrayList<BillWorkshift>) BillWorkshiftDatabase
                .getInstance(context)
                .billWorkshiftDAO()
                .getListBillWorkshift();
        Collections.sort(list, new Comparator<BillWorkshift>() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

            @Override
            public int compare(BillWorkshift o1, BillWorkshift o2) {
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

        setListBillWorkshift(list);
        return list;
    }

    public static ArrayList<BillWorkshift> getListFromTimeDESC(Context context) {
        ArrayList<BillWorkshift> list = (ArrayList<BillWorkshift>) BillWorkshiftDatabase
                .getInstance(context)
                .billWorkshiftDAO()
                .getListBillWorkshift();

        Collections.sort(list, new Comparator<BillWorkshift>() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

            @Override
            public int compare(BillWorkshift o1, BillWorkshift o2) {
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

        setListBillWorkshift(list);
        return list;
    }

    public ArrayList<BarEntry> displayBill(ArrayList<BillWorkshift> list) {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<BarBill> barBills = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        for (BillWorkshift b : list) {
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

    public void addBillWorkshift(BillWorkshift billWorkshift, Context context){
        listBillWorkshift.add(billWorkshift);
        BillWorkshiftDatabase.getInstance(context).billWorkshiftDAO().insertBillWorkshift(billWorkshift);
    }
    public void removeBillWorkshift(BillWorkshift billWorkshift){
        listBillWorkshift.remove(billWorkshift);
    }

    public void clearListBillWorkshift(Context context){
        listBillWorkshift.clear();
        BillWorkshiftDatabase.getInstance(context).billWorkshiftDAO().deleteAllBillWorkshift();
    }

    public Float getTotalPaymentForCurrentMonth(Context context){
        ArrayList<BillWorkshift> list = (ArrayList<BillWorkshift>) BillWorkshiftDatabase
                .getInstance(context)
                .billWorkshiftDAO()
                .getListBillWorkshift();
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        float total = 0f;
        for (BillWorkshift b : list) {
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
