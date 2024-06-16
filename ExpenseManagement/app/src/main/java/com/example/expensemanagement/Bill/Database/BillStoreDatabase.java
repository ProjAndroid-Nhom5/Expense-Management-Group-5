package com.example.expensemanagement.Bill.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.expensemanagement.Bill.DAO.BillFacilityDAO;
import com.example.expensemanagement.Bill.DAO.BillStoreDAO;
import com.example.expensemanagement.Bill.Model.BillFacility;
import com.example.expensemanagement.Bill.Model.BillStore;

@Database(entities = {BillStore.class},version = 1)
public abstract class BillStoreDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "BillStore.db";
    private static BillStoreDatabase instance;
    public static synchronized BillStoreDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), BillStoreDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract BillStoreDAO billStoreDAO();
}
