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

public class ListStoreSupplierAdapter extends ArrayAdapter<ListStoreSupplierData> {
    public ListStoreSupplierAdapter(@NonNull Context context, ArrayList<ListStoreSupplierData> dataArrayList) {
        super(context, R.layout.list_item_store_inf_supplier, dataArrayList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        ListStoreSupplierData listData = getItem(position);
        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_store_inf_supplier, parent, false);
        }
        TextView listSupplierName = view.findViewById(R.id.listNameSupplier);
        TextView listNameStore = view.findViewById(R.id.listNameStore);
        listSupplierName.setText("Name Supplier: "+listData.getSupplierName());
        listNameStore.setText("Name Store: "+listData.getStoreName());
        return view;
    }
}
