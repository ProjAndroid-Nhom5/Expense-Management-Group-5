package com.example.expensemanagement.Bill.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.expensemanagement.Bill.DAO.BillEcommerceDAO;
import com.example.expensemanagement.Bill.DAO.BillFacilityDAO;
import com.example.expensemanagement.Bill.Model.BillEcommerce;
import com.example.expensemanagement.Bill.Model.BillFacility;

@Database(entities = {BillFacility.class},version = 1)
public abstract class BillFacitilyDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "BillFacility.db";
    private static BillFacitilyDatabase instance;
    public static synchronized BillFacitilyDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), BillFacitilyDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract BillFacilityDAO billFacilityDAO();
}
