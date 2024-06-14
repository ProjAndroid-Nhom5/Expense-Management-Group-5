package com.example.expensemanagement.Store.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanagement.R;
import com.example.expensemanagement.Store.Model.ListStoreEcommerceData;
import com.example.expensemanagement.Store.Model.ListStoreSupplierData;
import com.example.expensemanagement.Store.StoreDetailEcommerce;
import com.example.expensemanagement.Store.StoreDetailSupplier;

import java.util.ArrayList;
import java.util.List;

public class ListStoreSupplierAdapter extends  RecyclerView.Adapter<MyViewHolderSupplier>{
    private Context context;
    private List<ListStoreSupplierData> supplierData;

    public ListStoreSupplierAdapter(Context context, List<ListStoreSupplierData> supplierData) {
        this.context = context;
        this.supplierData = supplierData;
    }

    @NonNull
    @Override
    public MyViewHolderSupplier onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_store_inf_supplier, parent, false);
        return new MyViewHolderSupplier(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderSupplier holder, int position) {
        holder.listSupplierName.setText(supplierData.get(position).getSupplierName());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StoreDetailSupplier.class);
                intent.putExtra("supplierId", supplierData.get(holder.getAdapterPosition()).getSupplierID());
                intent.putExtra("inputNameSupplier", supplierData.get(holder.getAdapterPosition()).getSupplierName());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return supplierData.size();
    }
    public void searchDataList(ArrayList<ListStoreSupplierData> searchList){
        supplierData = searchList;
        notifyDataSetChanged();
    }
}
class MyViewHolderSupplier extends RecyclerView.ViewHolder{
    TextView listSupplierName;
    CardView recCard;
    public MyViewHolderSupplier(@NonNull View itemView) {
        super(itemView);

        listSupplierName = itemView.findViewById(R.id.listSupplierName);

        recCard = itemView.findViewById(R.id.recCard);
    }
}
