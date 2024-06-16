package com.example.expensemanagement.Bill.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.expensemanagement.Bill.Model.BillEcommerce;
import com.example.expensemanagement.R;

import java.util.ArrayList;

public class ListBillEcommerceAdapter extends ArrayAdapter<BillEcommerce> {
    public ListBillEcommerceAdapter(@NonNull Context context, ArrayList<BillEcommerce> dataArrayList) {
        super(context, R.layout.list_item_bill_inf_ecommerce, dataArrayList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        BillEcommerce listData = getItem(position);
        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_bill_inf_ecommerce, parent, false);
        }
        TextView listNameCustomer = view.findViewById(R.id.listNameCustomer);
        TextView listNameEcommerce = view.findViewById(R.id.listNameEcommerce);
        TextView listTime = view.findViewById(R.id.listTime);
        TextView listTotalPayment = view.findViewById(R.id.listTotalPayment);
        listNameCustomer.setText(listData.getNameCustomer());
        listNameEcommerce.setText("Name Ecommerce: "+listData.getNameEcommerce());
        listTime.setText(listData.getDate()+" - "+ listData.getId());
        listTotalPayment.setText(listData.getTotalPayment().toString());
        return view;
    }
}
