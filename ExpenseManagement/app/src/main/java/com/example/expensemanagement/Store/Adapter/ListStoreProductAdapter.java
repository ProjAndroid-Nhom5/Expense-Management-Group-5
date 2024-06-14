package com.example.expensemanagement.Store.Adapter;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanagement.R;
import com.example.expensemanagement.Store.Model.ListStoreProductData;
import com.example.expensemanagement.Store.StoreDetailProduct;

import java.util.ArrayList;
import java.util.List;

public class ListStoreProductAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<ListStoreProductData> productData;

    public ListStoreProductAdapter(Context context, List<ListStoreProductData> productData) {
        this.context = context;
        this.productData = productData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_store_inf_product, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.listNameProdcut.setText(productData.get(position).getProductName());
        holder.listPrice.setText(String.valueOf(productData.get(position).getPrice()));

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StoreDetailProduct.class);
                intent.putExtra("productId", productData.get(holder.getAdapterPosition()).getProductID());
                intent.putExtra("inputNameProduct", productData.get(holder.getAdapterPosition()).getProductName());
                intent.putExtra("inputQuantity", productData.get(holder.getAdapterPosition()).getQuantity());
                intent.putExtra("inputPrice", productData.get(holder.getAdapterPosition()).getPrice());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productData.size();
    }

    public void searchDataList(ArrayList<ListStoreProductData> searchList){
        productData = searchList;
        notifyDataSetChanged();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{
    TextView listNameProdcut, listPrice;
    CardView recCard;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        listNameProdcut = itemView.findViewById(R.id.listNameProduct);
        listPrice = itemView.findViewById(R.id.listPrice);

        recCard = itemView.findViewById(R.id.recCard);
    }
}
