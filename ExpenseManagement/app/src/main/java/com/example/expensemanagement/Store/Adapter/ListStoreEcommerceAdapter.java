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
import com.example.expensemanagement.Store.Model.ListStoreEcommerceData;

import java.util.ArrayList;

public class ListStoreEcommerceAdapter extends ArrayAdapter<ListStoreEcommerceData> {
    public ListStoreEcommerceAdapter(@NonNull Context context, ArrayList<ListStoreEcommerceData> dataArrayList) {
        super(context, R.layout.list_item_store_inf_ecommerce, dataArrayList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        ListStoreEcommerceData listData = getItem(position);
        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_store_inf_ecommerce, parent, false);
        }
        TextView listNameEcommerce = view.findViewById(R.id.listNameEcommerce);
        TextView listNameStore = view.findViewById(R.id.listNameStore);
        listNameEcommerce.setText("Name Ecommerce: "+listData.getEcommerceName());
        listNameStore.setText("Name Store: "+listData.getNameStore());
        return view;
    }
}
