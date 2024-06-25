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

public class BillEcmmerceRespository {
    private static ArrayList<BillEcommerce> listBillEcommerce  = new ArrayList<>();

    public static ArrayList<BillEcommerce> getListBillEcommerce(Context context) {
        ArrayList<BillEcommerce> list = (ArrayList<BillEcommerce>) BillEcommerceDatabase
                .getInstance(context)
                .billEcommerceDAO()
                .getListBillEcommerce();
        setListBillEcommerce(list);
        return list;
    }

    public static void setListBillEcommerce(ArrayList<BillEcommerce> listBill) {
        BillEcmmerceRespository.listBillEcommerce = listBill;
    }

    public static ArrayList<BillEcommerce> getListFromAToZ(Context context) {
        ArrayList<BillEcommerce> list = (ArrayList<BillEcommerce>) BillEcommerceDatabase
                .getInstance(context)
                .billEcommerceDAO()
                .getListSortedByNameASC();
        setListBillEcommerce(list);
        return list;
    }

    public static ArrayList<BillEcommerce> getListFromZToA(Context context) {
        ArrayList<BillEcommerce> list = (ArrayList<BillEcommerce>) BillEcommerceDatabase
                .getInstance(context)
                .billEcommerceDAO()
                .getListSortedByTotalPaymentDesc();
        setListBillEcommerce(list);
        return list;
    }

    public static ArrayList<BillEcommerce> getListFromTotalPaymentASC(Context context) {
        ArrayList<BillEcommerce> list = (ArrayList<BillEcommerce>) BillEcommerceDatabase
                .getInstance(context)
                .billEcommerceDAO()
                .getListSortedByTotalPaymentASC();
        setListBillEcommerce(list);
        return list;
    }

    public static ArrayList<BillEcommerce> getListFromTotalPaymentDESC(Context context) {
        ArrayList<BillEcommerce> list = (ArrayList<BillEcommerce>) BillEcommerceDatabase
                .getInstance(context)
                .billEcommerceDAO()
                .getListSortedByTotalPaymentDesc();
        setListBillEcommerce(list);
        return list;
    }

    public static ArrayList<BillEcommerce> getListFromTimeASC(Context context) {
        ArrayList<BillEcommerce> list = (ArrayList<BillEcommerce>) BillEcommerceDatabase
                .getInstance(context)
                .billEcommerceDAO()
                .getListBillEcommerce();
        Collections.sort(list, new Comparator<BillEcommerce>() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

            @Override
            public int compare(BillEcommerce o1, BillEcommerce o2) {
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

        setListBillEcommerce(list);
        return list;
    }

    public static ArrayList<BillEcommerce> getListFromTimeDESC(Context context) {
        ArrayList<BillEcommerce> list = (ArrayList<BillEcommerce>) BillEcommerceDatabase
                .getInstance(context)
                .billEcommerceDAO()
                .getListBillEcommerce();

        Collections.sort(list, new Comparator<BillEcommerce>() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

            @Override
            public int compare(BillEcommerce o1, BillEcommerce o2) {
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

        setListBillEcommerce(list);
        return list;
    }

    public ArrayList<BarEntry> displayBill(ArrayList<BillEcommerce> list) {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<BarBill> barBills = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        for (BillEcommerce b : list) {
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

    public void addBillEcommerce(BillEcommerce billEcommerce, Context context){
        listBillEcommerce.add(billEcommerce);
        BillEcommerceDatabase.getInstance(context).billEcommerceDAO().insertBillEcommerce(billEcommerce);
    }
    public void removeBillEcommerce(BillEcommerce billEcommerce){
        listBillEcommerce.remove(billEcommerce);
    }

    public void clearListBillEcommerce(Context context){
        listBillEcommerce.clear();
        BillEcommerceDatabase.getInstance(context).billEcommerceDAO().deleteAllBillEcommerce();
    }

    public Float getTotalPaymentForCurrentMonth(Context context){
        ArrayList<BillEcommerce> list = (ArrayList<BillEcommerce>) BillEcommerceDatabase
                .getInstance(context)
                .billEcommerceDAO()
                .getListBillEcommerce();
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        float total = 0f;
        for (BillEcommerce b : list) {
            Date billDate = null;
            try {
                billDate = sdf.parse(b.getDate());
                if (billDate.getYear() == currentYear - 1900) { // Month is 0-based
                    total += b.getTotalPayment();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return total;
    }
}
