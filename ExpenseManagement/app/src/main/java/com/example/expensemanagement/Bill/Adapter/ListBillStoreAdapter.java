package com.example.expensemanagement.Bill.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.expensemanagement.Bill.Model.BillStore;
import com.example.expensemanagement.R;

import java.util.ArrayList;

public class ListBillStoreAdapter extends ArrayAdapter<BillStore> {
    public ListBillStoreAdapter(@NonNull Context context, ArrayList<BillStore> dataArrayList) {
        super(context, R.layout.list_item_bill_inf_store, dataArrayList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        BillStore listData = getItem(position);
        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_bill_inf_store, parent, false);
        }
        TextView listNameEmploye = view.findViewById(R.id.listNameEmploye);
        TextView listTime = view.findViewById(R.id.listTime);
        TextView listTotalPayment = view.findViewById(R.id.listTotalPayment);
        listNameEmploye.setText(listData.getNameEmploye());
        listTime.setText(listData.getDate()+" - "+listData.getId());
        listTotalPayment.setText(listData.getTotal().toString());
        return view;
    }
}
