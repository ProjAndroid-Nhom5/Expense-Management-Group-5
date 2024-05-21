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

public class ListStoreEmployeeAdapter extends ArrayAdapter<ListStoreEmployeeData> {
    public ListStoreEmployeeAdapter(@NonNull Context context, ArrayList<ListStoreEmployeeData> dataArrayList) {
        super(context, R.layout.list_item_store_inf_employee, dataArrayList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        ListStoreEmployeeData listData = getItem(position);
        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_store_inf_employee, parent, false);
        }
        TextView listEmployeeName = view.findViewById(R.id.listNameEmployee);
        TextView listNameStore = view.findViewById(R.id.listNameStore);
        listEmployeeName.setText("Name Employee: "+listData.getEmployeeName());
        listNameStore.setText("Name Store: "+listData.getStoreName());
        return view;
    }
}
