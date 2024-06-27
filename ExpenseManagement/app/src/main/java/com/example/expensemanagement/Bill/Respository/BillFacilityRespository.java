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
    public ArrayList<BillFacility> getListForDateRange(ArrayList<BillFacility> list, String startDateStr, String endDateStr) {
        ArrayList<BillFacility> filteredList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        Date startDate, endDate;
        try {
            startDate = sdf.parse(startDateStr);
            endDate = sdf.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return filteredList; // Xử lý lỗi nếu cần thiết
        }

        for (BillFacility b : list) {
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

    public ArrayList<BarEntry> displayAllBills(ArrayList<BillFacility> list) {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        int i = 1;

        for (BillFacility b : list) {
            Date billDate = null;
            try {
                billDate = sdf.parse(b.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (billDate != null) {
                barEntries.add(new BarEntry(i++, b.getTotalPayment()));
            }
        }

        return barEntries;
    }

    public ArrayList<BarEntry> displayBillForCurrentYear(ArrayList<BillFacility> list) {
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

    public ArrayList<BarEntry> displayBillForCurrentMonth(ArrayList<BillFacility> list) {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<BarBill> barBills = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1); // Đặt ngày là ngày đầu tiên của tháng
        int lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // Ngày cuối cùng của tháng
        calendar.set(Calendar.DAY_OF_MONTH, lastDayOfMonth); // Đặt ngày là ngày cuối cùng của tháng
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;

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

    public ArrayList<BarEntry> displayBillForCurrentWeek(ArrayList<BillFacility> list) {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<BarBill> barBills = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        int currentYear = calendar.get(Calendar.YEAR);
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);

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

        // Lấy ngày đầu tiên và ngày cuối cùng của tuần hiện tại
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        Date startDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_WEEK, 6);
        Date endDate = calendar.getTime();

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
    public ArrayList<BarEntry> displayBillForDateRange(ArrayList<BillFacility> list, String startDateStr, String endDateStr) {
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

        for (BillFacility b : list) {
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
                BarBill barBill = new BarBill(billDate, b.getTotalPayment());
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

    public Float getTotalPaymentForCurrentYear(Context context){
        ArrayList<BillFacility> list = (ArrayList<BillFacility>) BillFacitilyDatabase
                .getInstance(context)
                .billFacilityDAO()
                .getListBillFacility();
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        float total = 0f;
        for (BillFacility b : list) {
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

    public Float getTotalPaymentForCurrentMonth(Context context){
        ArrayList<BillFacility> list = (ArrayList<BillFacility>) BillFacitilyDatabase
                .getInstance(context)
                .billFacilityDAO()
                .getListBillFacility();
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH); // Tháng hiện tại (0-based)
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        float total = 0f;
        for (BillFacility b : list) {
            Date billDate = null;
            try {
                billDate = sdf.parse(b.getDate());
                Calendar billCalendar = Calendar.getInstance();
                billCalendar.setTime(billDate);
                int billYear = billCalendar.get(Calendar.YEAR);
                int billMonth = billCalendar.get(Calendar.MONTH);
                if (billYear == currentYear && billMonth == currentMonth) {
                    total += b.getTotalPayment();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return total;
    }

    public Float getTotalPaymentForCurrentWeek(Context context){
        ArrayList<BillFacility> list = (ArrayList<BillFacility>) BillFacitilyDatabase
                .getInstance(context)
                .billFacilityDAO()
                .getListBillFacility();
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY); // Đặt ngày đầu tuần là thứ Hai
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // Đặt ngày là thứ Hai của tuần hiện tại
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR); // Tuần hiện tại trong năm
        int currentYear = calendar.get(Calendar.YEAR); // Năm hiện tại
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        float total = 0f;
        for (BillFacility b : list) {
            Date billDate = null;
            try {
                billDate = sdf.parse(b.getDate());
                Calendar billCalendar = Calendar.getInstance();
                billCalendar.setTime(billDate);
                int billYear = billCalendar.get(Calendar.YEAR);
                int billWeek = billCalendar.get(Calendar.WEEK_OF_YEAR);
                if (billYear == currentYear && billWeek == currentWeek) {
                    total += b.getTotalPayment();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return total;
    }
}
