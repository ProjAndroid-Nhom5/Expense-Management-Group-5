package com.example.expensemanagement.Bill.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.expensemanagement.Bill.Model.BillFacility;
import com.example.expensemanagement.R;

import java.util.ArrayList;

public class ListBillFacitilyAdapter extends ArrayAdapter<BillFacility> {
    public ListBillFacitilyAdapter(@NonNull Context context, ArrayList<BillFacility> dataArrayList) {
        super(context, R.layout.list_item_bill_inf_facility, dataArrayList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        BillFacility listData = getItem(position);
        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_bill_inf_facility, parent, false);
        }
        TextView listNameFacility = view.findViewById(R.id.listNameFacility);
        TextView listNameManager = view.findViewById(R.id.listNameManager);
        TextView listTime = view.findViewById(R.id.listTime);
        TextView listTotalPayment = view.findViewById(R.id.listTotalPayment);
        listNameFacility.setText(listData.getNameFacility());
        listNameFacility.setText("Name Manager: "+listData.getNameManager());
        listTime.setText(listData.getDate()+" - "+ listData.getId());
        listTotalPayment.setText(listData.getTotalPayment().toString());
        return view;
    }
}
