package com.example.expensemanagement.Bill.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.expensemanagement.Bill.Model.BillSupply;
import com.example.expensemanagement.R;

import java.util.ArrayList;

public class ListBillSupplyAdapter extends ArrayAdapter<BillSupply> {
    public ListBillSupplyAdapter(@NonNull Context context, ArrayList<BillSupply> dataArrayList) {
        super(context, R.layout.list_item_bill_inf_supply, dataArrayList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        BillSupply listData = getItem(position);
        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_bill_inf_supply, parent, false);
        }
        TextView listNameSupplier = view.findViewById(R.id.listNameSupplier);
        TextView listTime = view.findViewById(R.id.listTime);
        TextView listTotalPayment = view.findViewById(R.id.listTotalPayment);
        listNameSupplier.setText(listData.getNameSupplier()+" - "+listData.getNumber());
        listTime.setText(listData.getDate()+" - "+listData.getId());
        listTotalPayment.setText(listData.getTotal().toString());
        return view;
    }
}
