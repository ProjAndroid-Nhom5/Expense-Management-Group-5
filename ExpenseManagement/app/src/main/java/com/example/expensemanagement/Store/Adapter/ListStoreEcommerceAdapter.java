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
import com.example.expensemanagement.Store.Model.ListStoreProductData;
import com.example.expensemanagement.Store.StoreDetailEcommerce;
import com.example.expensemanagement.Store.StoreDetailProduct;

import java.util.ArrayList;
import java.util.List;

public class ListStoreEcommerceAdapter extends RecyclerView.Adapter<MyViewHolderEcommerce> {
    private Context context;
    private List<ListStoreEcommerceData> ecommerceData;

    public ListStoreEcommerceAdapter(Context context, List<ListStoreEcommerceData> ecommerceData) {
        this.context = context;
        this.ecommerceData = ecommerceData;
    }
    @NonNull
    @Override
    public MyViewHolderEcommerce onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_store_inf_ecommerce, parent, false);
        return new MyViewHolderEcommerce(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderEcommerce holder, int position) {
        holder.listNameEcommerce.setText(ecommerceData.get(position).getEcommerceName());
        holder.listFixedFee.setText(String.valueOf(ecommerceData.get(position).getFixedFee()));

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StoreDetailEcommerce.class);
                intent.putExtra("ecommerceId", ecommerceData.get(holder.getAdapterPosition()).getEcommerceID());
                intent.putExtra("inputNameEcommerce", ecommerceData.get(holder.getAdapterPosition()).getEcommerceName());
                intent.putExtra("inputPaymentFee", ecommerceData.get(holder.getAdapterPosition()).getPaymentFee());
                intent.putExtra("inputFixedFee", ecommerceData.get(holder.getAdapterPosition()).getFixedFee());
                intent.putExtra("inputServiceFee", ecommerceData.get(holder.getAdapterPosition()).getServiceFee());
                intent.putExtra("inputFreightSurcharge", ecommerceData.get(holder.getAdapterPosition()).getFreightSurcharge());
                intent.putExtra("inputReceivable", ecommerceData.get(holder.getAdapterPosition()).getReceivables());
                intent.putExtra("inputVAT", ecommerceData.get(holder.getAdapterPosition()).getVAT());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ecommerceData.size();
    }

    public void searchDataList(ArrayList<ListStoreEcommerceData> searchList){
        ecommerceData = searchList;
        notifyDataSetChanged();
    }
}

class MyViewHolderEcommerce extends RecyclerView.ViewHolder{
    TextView listNameEcommerce, listFixedFee;
    CardView recCard;
    public MyViewHolderEcommerce(@NonNull View itemView) {
        super(itemView);

        listNameEcommerce = itemView.findViewById(R.id.listNameEcommerce);
        listFixedFee = itemView.findViewById(R.id.listFixedFee);

        recCard = itemView.findViewById(R.id.recCard);
    }
}
