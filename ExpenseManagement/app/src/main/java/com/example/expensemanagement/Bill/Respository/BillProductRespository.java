package com.example.expensemanagement.Bill.Respository;


import android.content.Context;

import com.example.expensemanagement.Bill.Database.BillProductDatabase;
import com.example.expensemanagement.Bill.Database.BillWorkshiftDatabase;
import com.example.expensemanagement.Bill.Model.BarBill;
import com.example.expensemanagement.Bill.Model.BillProduct;
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

public class BillProductRespository {
    private static ArrayList<BillProduct> listBillProduct  = new ArrayList<>();

    public static ArrayList<BillProduct> getListBillProduct(Context context) {
        ArrayList<BillProduct> list = (ArrayList<BillProduct>) BillProductDatabase
                .getInstance(context)
                .billProductDAO()
                .getListBillProduct();
        setListBillProduct(list);
        return list;
    }

    public static void setListBillProduct(ArrayList<BillProduct> listBill) {
        BillProductRespository.listBillProduct = listBill;
    }

    public static ArrayList<BillProduct> getListFromAToZ(Context context) {
        ArrayList<BillProduct> list = (ArrayList<BillProduct>) BillProductDatabase
                .getInstance(context)
                .billProductDAO()
                .getListSortedByNameASC();
        setListBillProduct(list);
        return list;
    }

    public static ArrayList<BillProduct> getListFromZToA(Context context) {
        ArrayList<BillProduct> list = (ArrayList<BillProduct>) BillProductDatabase
                .getInstance(context)
                .billProductDAO()
                .getListSortedByNameDesc();
        setListBillProduct(list);
        return list;
    }

    public static ArrayList<BillProduct> getListFromQuantityASC(Context context) {
        ArrayList<BillProduct> list = (ArrayList<BillProduct>) BillProductDatabase
                .getInstance(context)
                .billProductDAO()
                .getListSortedByQuantityASC();
        setListBillProduct(list);
        return list;
    }

    public static ArrayList<BillProduct> getListFromQuantityDESC(Context context) {
        ArrayList<BillProduct> list = (ArrayList<BillProduct>) BillProductDatabase
                .getInstance(context)
                .billProductDAO()
                .getListSortedByQuantityDesc();
        setListBillProduct(list);
        return list;
    }

//    public ArrayList<BarEntry> displayBill(ArrayList<BillProduct> list) {
//        ArrayList<BarEntry> barEntries = new ArrayList<>();
//        ArrayList<BarBill> barBills = new ArrayList<>();
//        Calendar calendar = Calendar.getInstance();
//        int currentYear = calendar.get(Calendar.YEAR);
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
//
//        for (BillProduct b : list) {
//            boolean check = false;
//            Date billDate = null;
//
//            for (BarBill bar : barBills) {
//                if (bar.getDate().equals(billDate)) {
//                    check = true;
//                    break;
//                }
//            }
//
//            if (!check) {
//                BarBill barBill = new BarBill(billDate, b.getTotal());
//                barBills.add(barBill);
//            }
//        }
//
//        for (int i = 1; i <= 12; i++) {
//            float total = 0f;
//            for (BarBill bar : barBills) {
//                if (bar.getDate().getMonth() + 1 == i && bar.getDate().getYear() == currentYear - 1900) { // Month is 0-based
//                    total += bar.getTotal();
//                }
//            }
//            barEntries.add(new BarEntry(i, total));
//        }
//
//        return barEntries;
//    }

    public void addBillProduct(BillProduct billProduct, Context context){
        listBillProduct.add(billProduct);
        BillProductDatabase.getInstance(context).billProductDAO().insertBillProduct(billProduct);
    }
    public void removeBillProduct(BillProduct billProduct){
        listBillProduct.remove(billProduct);
    }

    public void clearListBillProduct(Context context){
        listBillProduct.clear();
        BillProductDatabase.getInstance(context).billProductDAO().deleteAllBillProduct();
    }
}
