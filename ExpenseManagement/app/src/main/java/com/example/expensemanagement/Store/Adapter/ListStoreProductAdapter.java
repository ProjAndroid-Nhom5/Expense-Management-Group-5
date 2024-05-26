package com.example.expensemanagement.Store.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.expensemanagement.R;
import com.example.expensemanagement.Store.Model.ListStoreProductData;

import java.util.ArrayList;

public class ListStoreProductAdapter extends ArrayAdapter<ListStoreProductData> {
    public ListStoreProductAdapter(@NonNull Context context, ArrayList<ListStoreProductData> dataArrayList) {
        super(context, R.layout.list_item_store_inf_product, dataArrayList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        ListStoreProductData listData = getItem(position);
        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_store_inf_product, parent, false);
        }
        TextView listProductName = view.findViewById(R.id.listNameProduct);
        TextView listNameStore = view.findViewById(R.id.listNameStore);
        listProductName.setText("Name Product: "+listData.getProductName());
        listNameStore.setText("Name Store: "+listData.getStoreName());
        return view;
    }
}
