package com.example.expensemanagement.Home.Inventory;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public static List<Date> getDateList(){
        List<Date> dateList = new ArrayList<>();

        Date thisYear = new Date("This Year");
        dateList.add(thisYear);

        Date thisMoth = new Date("This Month");
        dateList.add(thisMoth);

        Date thisWeek = new Date("This Week");
        dateList.add(thisWeek);

        return dateList;
    }
}
