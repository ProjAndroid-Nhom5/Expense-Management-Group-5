package com.example.expensemanagement.Bill.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.expensemanagement.Bill.DAO.BillSupplyDAO;
import com.example.expensemanagement.Bill.DAO.BillWorkshiftDAO;
import com.example.expensemanagement.Bill.Model.BillSupply;
import com.example.expensemanagement.Bill.Model.BillWorkshift;

@Database(entities = {BillWorkshift.class},version = 1)
public abstract class BillWorkshiftDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "BillWorkshift.db";
    private static BillWorkshiftDatabase instance;
    public static synchronized BillWorkshiftDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), BillWorkshiftDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract BillWorkshiftDAO billWorkshiftDAO();
}
