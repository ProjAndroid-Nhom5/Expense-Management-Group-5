package com.example.expensemanagement.Bill.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.expensemanagement.Bill.DAO.BillProductDAO;
import com.example.expensemanagement.Bill.DAO.BillWorkshiftDAO;
import com.example.expensemanagement.Bill.Model.BillProduct;
import com.example.expensemanagement.Bill.Model.BillWorkshift;

@Database(entities = {BillProduct.class},version = 1)
public abstract class BillProductDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "BillProduct.db";
    private static BillProductDatabase instance;
    public static synchronized BillProductDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), BillProductDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract BillProductDAO billProductDAO();
}
