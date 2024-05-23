package com.example.expensemanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListBillWorkshiftAdapter extends ArrayAdapter<ListBillWorkshiftData> {
    public ListBillWorkshiftAdapter(@NonNull Context context, ArrayList<ListBillWorkshiftData> dataArrayList) {
        super(context, R.layout.list_item_bill_inf_workshift, dataArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        ListBillWorkshiftData listData = getItem(position);
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_bill_inf_workshift, parent, false);
        }
        TextView listNameEmploye = view.findViewById(R.id.listNameEmploye);
        TextView listTotalWorked = view.findViewById(R.id.listTotalWorked);
        TextView listTime = view.findViewById(R.id.listTime);
        TextView listTotalPayment = view.findViewById(R.id.listTotalPayment);
        listNameEmploye.setText(listData.getNameEmploye());
        listTotalWorked.setText("Total Worked: " + listData.getTotalWorked());
        listTime.setText(listData.getDate() + " - " + listData.getId());
        listTotalPayment.setText(listData.getTotal().toString());
        return view;
    }
}