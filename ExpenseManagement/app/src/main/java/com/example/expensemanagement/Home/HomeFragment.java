package com.example.expensemanagement.Home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.expensemanagement.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    BarChart barChart1, barChart2;

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
        // Xu ly code
        barChart1 = view.findViewById(R.id.barchart1);

        BarDataSet barDataSet1 = new BarDataSet(barEntries1(),"Revenue");
        barDataSet1.setColors(Color.RED);

        BarDataSet barDataSet2 = new BarDataSet(barEntries2(),"Expenses");
        barDataSet2.setColor(Color.BLUE);

        BarData barData1 = new BarData(barDataSet1,barDataSet2);
        barChart1.setData(barData1);

        String[] days = new String[]{"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
        XAxis xAxis = barChart1.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(days));
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

        barChart1.invalidate();

        barChart2 = view.findViewById(R.id.barchart2);

        BarDataSet barDataSet3 = new BarDataSet(barEntries3(),"Revenue");
        barDataSet3.setColor(Color.RED);

        BarDataSet barDataSet4 = new BarDataSet(barEntries4(),"Expenses");
        barDataSet4.setColor(Color.BLUE);

        BarData barData2 = new BarData(barDataSet3,barDataSet4);
        barChart2.setData(barData2);

        String[] days2 = new String[]{"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Satureday"};
        XAxis xAxis2 = barChart2.getXAxis();
        xAxis2.setValueFormatter(new IndexAxisValueFormatter(days2));
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
        barChart2.groupBars(0,groupSpace2,barSpace2);

        barChart2.invalidate();
    }

    private ArrayList<BarEntry> barEntries1(){
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1,2000));
        barEntries.add(new BarEntry(2,791));
        barEntries.add(new BarEntry(3,630));
        barEntries.add(new BarEntry(4,450));
        barEntries.add(new BarEntry(5,2724));
        barEntries.add(new BarEntry(6,500));
        barEntries.add(new BarEntry(7,2173));
        return barEntries;
    }

    private ArrayList<BarEntry> barEntries2(){
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1,900));
        barEntries.add(new BarEntry(2,631));
        barEntries.add(new BarEntry(3,1040));
        barEntries.add(new BarEntry(4,382));
        barEntries.add(new BarEntry(5,2614));
        barEntries.add(new BarEntry(6,5000));
        barEntries.add(new BarEntry(7,1173));
        return barEntries;
    }

    private ArrayList<BarEntry> barEntries3(){
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1,2000));
        barEntries.add(new BarEntry(2,791));
        barEntries.add(new BarEntry(3,630));
        barEntries.add(new BarEntry(4,450));
        barEntries.add(new BarEntry(5,2724));
        barEntries.add(new BarEntry(6,500));
        barEntries.add(new BarEntry(7,2173));
        return barEntries;
    }

    private ArrayList<BarEntry> barEntries4(){
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1,900));
        barEntries.add(new BarEntry(2,631));
        barEntries.add(new BarEntry(3,1040));
        barEntries.add(new BarEntry(4,382));
        barEntries.add(new BarEntry(5,2614));
        barEntries.add(new BarEntry(6,5000));
        barEntries.add(new BarEntry(7,1173));
        return barEntries;
    }
}