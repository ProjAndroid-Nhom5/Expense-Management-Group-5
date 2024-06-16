package com.example.expensemanagement.Bill.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.expensemanagement.Bill.DAO.BillEcommerceDAO;
import com.example.expensemanagement.Bill.Model.BillEcommerce;

@Database(entities = {BillEcommerce.class},version = 1)
public abstract class BillEcommerceDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "BillEcommerce.db";
    private static BillEcommerceDatabase instance;
    public static synchronized BillEcommerceDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), BillEcommerceDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract BillEcommerceDAO billEcommerceDAO();
}
