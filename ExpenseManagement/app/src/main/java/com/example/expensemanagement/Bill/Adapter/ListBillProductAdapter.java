package com.example.expensemanagement.Bill.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.expensemanagement.Bill.Model.BillProduct;
import com.example.expensemanagement.R;

import java.util.ArrayList;

public class ListBillProductAdapter extends ArrayAdapter<BillProduct> {
    public ListBillProductAdapter(@NonNull Context context, ArrayList<BillProduct> dataArrayList) {
        super(context, R.layout.list_item_bill_inf_product, dataArrayList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        BillProduct listData = getItem(position);
        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_bill_inf_product, parent, false);
        }
        TextView listNameProduct = view.findViewById(R.id.listNameProduct);
        TextView listBillID = view.findViewById(R.id.listBillID);
        TextView listQuantity = view.findViewById(R.id.listQuantity);
        listNameProduct.setText(listData.getBill_ProductID()+" - "+listData.getNameProduct());
        listBillID.setText("BillID: "+listData.getBillID());
        listQuantity.setText(listData.getQuantity().toString());
        return view;
    }
}