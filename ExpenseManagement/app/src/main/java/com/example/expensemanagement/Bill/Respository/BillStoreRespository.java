package com.example.expensemanagement.Bill.Respository;


import android.content.Context;

import com.example.expensemanagement.Bill.Database.BillFacitilyDatabase;
import com.example.expensemanagement.Bill.Database.BillStoreDatabase;
import com.example.expensemanagement.Bill.Model.BarBill;
import com.example.expensemanagement.Bill.Model.BillEcommerce;
import com.example.expensemanagement.Bill.Model.BillFacility;
import com.example.expensemanagement.Bill.Model.BillStore;
import com.github.mikephil.charting.data.BarEntry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class BillStoreRespository {
    private static ArrayList<BillStore> listBillStore  = new ArrayList<>();

    public static ArrayList<BillStore> getListBillStore(Context context) {
        ArrayList<BillStore> list = (ArrayList<BillStore>) BillStoreDatabase
                .getInstance(context)
                .billStoreDAO()
                .getListBillStore();
        setListBillStore(list);
        return list;
    }

    public static void setListBillStore(ArrayList<BillStore> listBill) {
        BillStoreRespository.listBillStore = listBill;
    }

    public static ArrayList<BillStore> getListFromAToZ(Context context) {
        ArrayList<BillStore> list = (ArrayList<BillStore>) BillStoreDatabase
                .getInstance(context)
                .billStoreDAO()
                .getListSortedByNameASC();
        setListBillStore(list);
        return list;
    }

    public static ArrayList<BillStore> getListFromZToA(Context context) {
        ArrayList<BillStore> list = (ArrayList<BillStore>) BillStoreDatabase
                .getInstance(context)
                .billStoreDAO()
                .getListSortedByTotalPaymentDesc();
        setListBillStore(list);
        return list;
    }

    public static ArrayList<BillStore> getListFromTotalPaymentASC(Context context) {
        ArrayList<BillStore> list = (ArrayList<BillStore>) BillStoreDatabase
                .getInstance(context)
                .billStoreDAO()
                .getListSortedByTotalPaymentASC();
        setListBillStore(list);
        return list;
    }

    public static ArrayList<BillStore> getListFromTotalPaymentDESC(Context context) {
        ArrayList<BillStore> list = (ArrayList<BillStore>) BillStoreDatabase
                .getInstance(context)
                .billStoreDAO()
                .getListSortedByTotalPaymentDesc();
        setListBillStore(list);
        return list;
    }

    public static ArrayList<BillStore> getListFromTimeASC(Context context) {
        ArrayList<BillStore> list = (ArrayList<BillStore>) BillStoreDatabase
                .getInstance(context)
                .billStoreDAO()
                .getListBillStore();
        Collections.sort(list, new Comparator<BillStore>() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

            @Override
            public int compare(BillStore o1, BillStore o2) {
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

        setListBillStore(list);
        return list;
    }

    public static ArrayList<BillStore> getListFromTimeDESC(Context context) {
        ArrayList<BillStore> list = (ArrayList<BillStore>) BillStoreDatabase
                .getInstance(context)
                .billStoreDAO()
                .getListBillStore();

        Collections.sort(list, new Comparator<BillStore>() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

            @Override
            public int compare(BillStore o1, BillStore o2) {
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

        setListBillStore(list);
        return list;
    }

    public ArrayList<BarEntry> displayBillForCurrentYear(ArrayList<BillStore> list) {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<BarBill> barBills = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        for (BillStore b : list) {
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

    public ArrayList<BarEntry> displayBillForCurrentMonth(ArrayList<BillStore> list) {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<BarBill> barBills = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1); // Đặt ngày là ngày đầu tiên của tháng
        int lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // Ngày cuối cùng của tháng
        calendar.set(Calendar.DAY_OF_MONTH, lastDayOfMonth); // Đặt ngày là ngày cuối cùng của tháng
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;

        for (BillStore b : list) {
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

        for (int i = 1; i <= lastDayOfMonth; i++) {
            float total = 0f;
            for (BarBill bar : barBills) {
                if (bar.getDate().getDate() == i
                        && bar.getDate().getMonth() + 1 == currentMonth
                        && bar.getDate().getYear() == currentYear - 1900) {
                    total += bar.getTotal();
                }
            }
            barEntries.add(new BarEntry(i, total));
        }

        return barEntries;
    }

    public ArrayList<BarEntry> displayBillForCurrentWeek(ArrayList<BillStore> list) {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<BarBill> barBills = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        int currentYear = calendar.get(Calendar.YEAR);
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);

        for (BillStore b : list) {
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

        // Lấy ngày đầu tiên và ngày cuối cùng của tuần hiện tại
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        Date startDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_WEEK, 6);

        for (int i = 0; i < 7; i++) {
            calendar.setTime(startDate);
            calendar.add(Calendar.DAY_OF_WEEK, i);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            float total = 0f;
            for (BarBill bar : barBills) {
                Calendar billCalendar = Calendar.getInstance();
                billCalendar.setTime(bar.getDate());
                if (billCalendar.get(Calendar.YEAR) == currentYear
                        && billCalendar.get(Calendar.WEEK_OF_YEAR) == currentWeek
                        && billCalendar.get(Calendar.DAY_OF_WEEK) == dayOfWeek) {
                    total += bar.getTotal();
                }
            }
            barEntries.add(new BarEntry(dayOfWeek, total));
        }

        return barEntries;
    }

    public void addBillStore(BillStore billStore, Context context){
        listBillStore.add(billStore);
        BillStoreDatabase.getInstance(context).billStoreDAO().insertBillStore(billStore);
    }
    public void removeBillStore(BillStore billStore){
        listBillStore.remove(billStore);
    }

    public void clearListBillStore(Context context){
        listBillStore.clear();
        BillStoreDatabase.getInstance(context).billStoreDAO().deleteAllBillStore();
    }

    public Float getTotalPaymentForCurrentYear(Context context){
        ArrayList<BillStore> list = (ArrayList<BillStore>) BillStoreDatabase
                .getInstance(context)
                .billStoreDAO()
                .getListBillStore();
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        float total = 0f;
        for (BillStore b : list) {
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

    public Float getTotalPaymentForCurrentMonth(Context context){
        ArrayList<BillStore> list = (ArrayList<BillStore>) BillStoreDatabase
                .getInstance(context)
                .billStoreDAO()
                .getListBillStore();
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH); // Tháng hiện tại (0-based)
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        float total = 0f;
        for (BillStore b : list) {
            Date billDate = null;
            try {
                billDate = sdf.parse(b.getDate());
                Calendar billCalendar = Calendar.getInstance();
                billCalendar.setTime(billDate);
                int billYear = billCalendar.get(Calendar.YEAR);
                int billMonth = billCalendar.get(Calendar.MONTH);
                if (billYear == currentYear && billMonth == currentMonth) {
                    total += b.getTotal();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return total;
    }

    public Float getTotalPaymentForCurrentWeek(Context context){
        ArrayList<BillStore> list = (ArrayList<BillStore>) BillStoreDatabase
                .getInstance(context)
                .billStoreDAO()
                .getListBillStore();
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY); // Đặt ngày đầu tuần là thứ Hai
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // Đặt ngày là thứ Hai của tuần hiện tại
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR); // Tuần hiện tại trong năm
        int currentYear = calendar.get(Calendar.YEAR); // Năm hiện tại
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        float total = 0f;
        for (BillStore b : list) {
            Date billDate = null;
            try {
                billDate = sdf.parse(b.getDate());
                Calendar billCalendar = Calendar.getInstance();
                billCalendar.setTime(billDate);
                int billYear = billCalendar.get(Calendar.YEAR);
                int billWeek = billCalendar.get(Calendar.WEEK_OF_YEAR);
                if (billYear == currentYear && billWeek == currentWeek) {
                    total += b.getTotal();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return total;
    }
}
