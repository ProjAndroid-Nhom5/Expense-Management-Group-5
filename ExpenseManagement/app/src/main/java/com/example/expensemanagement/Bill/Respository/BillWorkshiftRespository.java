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

    public ArrayList<BillWorkshift> getListForDateRange(ArrayList<BillWorkshift> list, String startDateStr, String endDateStr) {
        ArrayList<BillWorkshift> filteredList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        Date startDate, endDate;
        try {
            startDate = sdf.parse(startDateStr);
            endDate = sdf.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return filteredList; // Xử lý lỗi nếu cần thiết
        }

        for (BillWorkshift b : list) {
            Date billDate;
            try {
                billDate = sdf.parse(b.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
                continue;
            }

            if (billDate != null && !billDate.before(startDate) && !billDate.after(endDate)) {
                filteredList.add(b);
            }
        }

        return filteredList;
    }

    public ArrayList<BarEntry> displayBillForCurrentYear(ArrayList<BillWorkshift> list) {
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

    public ArrayList<BarEntry> displayBillForCurrentMonth(ArrayList<BillWorkshift> list) {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<BarBill> barBills = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1); // Đặt ngày là ngày đầu tiên của tháng
        int lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // Ngày cuối cùng của tháng
        calendar.set(Calendar.DAY_OF_MONTH, lastDayOfMonth); // Đặt ngày là ngày cuối cùng của tháng
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;

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

    public ArrayList<BarEntry> displayBillForCurrentWeek(ArrayList<BillWorkshift> list) {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<BarBill> barBills = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        int currentYear = calendar.get(Calendar.YEAR);
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);

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

    public ArrayList<BarEntry> displayBillForDateRange(ArrayList<BillWorkshift> list, String startDateStr, String endDateStr) {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<BarBill> barBills = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        Date startDate, endDate;
        try {
            startDate = sdf.parse(startDateStr);
            endDate = sdf.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return barEntries; // Hoặc xử lý lỗi khác tùy vào nhu cầu của bạn
        }

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        startCalendar.set(Calendar.HOUR_OF_DAY, 0);
        startCalendar.set(Calendar.MINUTE, 0);
        startCalendar.set(Calendar.SECOND, 0);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        endCalendar.set(Calendar.HOUR_OF_DAY, 23);
        endCalendar.set(Calendar.MINUTE, 59);
        endCalendar.set(Calendar.SECOND, 59);

        int currentYear = startCalendar.get(Calendar.YEAR);
        int currentMonth = startCalendar.get(Calendar.MONTH) + 1;

        for (BillWorkshift b : list) {
            Date billDate;
            try {
                billDate = sdf.parse(b.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
                continue;
            }

            if (billDate == null || billDate.before(startDate) || billDate.after(endDate)) {
                continue; // Bỏ qua các khoản thanh toán không nằm trong khoảng thời gian yêu cầu
            }

            boolean check = false;
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
        int i = 1;
        while (!startCalendar.after(endCalendar)) {
            float total = 0f;
            for (BarBill bar : barBills) {
                Calendar billCalendar = Calendar.getInstance();
                billCalendar.setTime(bar.getDate());
                if (billCalendar.get(Calendar.DAY_OF_MONTH) == startCalendar.get(Calendar.DAY_OF_MONTH)
                        && billCalendar.get(Calendar.MONTH) + 1 == currentMonth
                        && billCalendar.get(Calendar.YEAR) == currentYear) {
                    total += bar.getTotal();
                }
            }
            if (total!=0){
                barEntries.add(new BarEntry(i++, total));
            }

            startCalendar.add(Calendar.DAY_OF_MONTH, 1);
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

    public Float getTotalPaymentForCurrentYear(Context context){
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
    public Float getTotalPaymentForCurrentMonth(Context context){
        ArrayList<BillWorkshift> list = (ArrayList<BillWorkshift>) BillWorkshiftDatabase
                .getInstance(context)
                .billWorkshiftDAO()
                .getListBillWorkshift();
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH); // Tháng hiện tại (0-based)
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        float total = 0f;
        for (BillWorkshift b : list) {
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
        ArrayList<BillWorkshift> list = (ArrayList<BillWorkshift>) BillWorkshiftDatabase
                .getInstance(context)
                .billWorkshiftDAO()
                .getListBillWorkshift();
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY); // Đặt ngày đầu tuần là thứ Hai
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // Đặt ngày là thứ Hai của tuần hiện tại
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR); // Tuần hiện tại trong năm
        int currentYear = calendar.get(Calendar.YEAR); // Năm hiện tại
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        float total = 0f;
        for (BillWorkshift b : list) {
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
