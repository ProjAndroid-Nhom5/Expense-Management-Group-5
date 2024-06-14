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
import com.example.expensemanagement.Store.Model.ListStoreEmployeeData;
import com.example.expensemanagement.Store.StoreDetailEmployee;

import java.util.List;

public class ListStoreEmployeeAdapter extends RecyclerView.Adapter<MyViewHolderEmployee> {
    private Context context;
    private List<ListStoreEmployeeData> employeeData;

    public ListStoreEmployeeAdapter(Context context, List<ListStoreEmployeeData> employeeData) {
        this.context = context;
        this.employeeData = employeeData;
    }

    @NonNull
    @Override
    public MyViewHolderEmployee onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_store_inf_employee, parent, false);
        return new MyViewHolderEmployee(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderEmployee holder, int position) {
        holder.listNameEmployee.setText(employeeData.get(position).getEmployeeName());
        holder.listPosition.setText(employeeData.get(position).getPosition());
        holder.listEmail.setText(employeeData.get(position).getEmail());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StoreDetailEmployee.class);
                intent.putExtra("employeeId", employeeData.get(holder.getAdapterPosition()).getEmployeeID());
                intent.putExtra("inputNameEmployee", employeeData.get(holder.getAdapterPosition()).getEmployeeName());
                intent.putExtra("inputEmail", employeeData.get(holder.getAdapterPosition()).getEmail());
                intent.putExtra("inputPhone", employeeData.get(holder.getAdapterPosition()).getPhone());
                intent.putExtra("inputAddress", employeeData.get(holder.getAdapterPosition()).getAddress());
                intent.putExtra("inputDayOfWork", employeeData.get(holder.getAdapterPosition()).getDayOfWork());
                intent.putExtra("inputPosition", employeeData.get(holder.getAdapterPosition()).getPosition());
                intent.putExtra("inputBaseSalary", employeeData.get(holder.getAdapterPosition()).getBaseSalary());
                intent.putExtra("inputBonusSalary", employeeData.get(holder.getAdapterPosition()).getBonusSalary());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return employeeData.size();
    }

    public void searchDataList(List<ListStoreEmployeeData> searchList) {
        employeeData = searchList;
        notifyDataSetChanged();
    }
}

class MyViewHolderEmployee extends RecyclerView.ViewHolder {
    TextView listNameEmployee, listPosition, listEmail;
    CardView recCard;

    public MyViewHolderEmployee(@NonNull View itemView) {
        super(itemView);

        listNameEmployee = itemView.findViewById(R.id.listNameEmployee);
        listPosition = itemView.findViewById(R.id.listPosition);
        listEmail = itemView.findViewById(R.id.listEmail);
        recCard = itemView.findViewById(R.id.recCard);
    }
}
