package com.example.expensemanagement.Bill.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.expensemanagement.Bill.DAO.BillStoreDAO;
import com.example.expensemanagement.Bill.DAO.BillSupplyDAO;
import com.example.expensemanagement.Bill.Model.BillStore;
import com.example.expensemanagement.Bill.Model.BillSupply;

@Database(entities = {BillSupply.class},version = 1)
public abstract class BillSupplyDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "BillSupply.db";
    private static BillSupplyDatabase instance;
    public static synchronized BillSupplyDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), BillSupplyDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract BillSupplyDAO billSupplyDAO();
}
