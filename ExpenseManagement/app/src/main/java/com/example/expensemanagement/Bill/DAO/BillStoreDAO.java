package com.example.expensemanagement.Bill.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.expensemanagement.Bill.Model.BillFacility;
import com.example.expensemanagement.Bill.Model.BillStore;

import java.util.List;

@Dao
public interface BillStoreDAO {
    @Insert
    void insertBillStore(BillStore billStore);

    @Delete
    void deleteBillStore(BillStore billStore);

    @Query("SELECT * FROM BillStore")
    List<BillStore> getListBillStore();

    @Query("DELETE FROM BillStore")
    void deleteAllBillStore();

    @Query("SELECT * FROM BillStore ORDER BY nameEmploye DESC")
    List<BillStore> getListSortedByNameDesc();

    @Query("SELECT * FROM BillStore ORDER BY nameEmploye ASC")
    List<BillStore> getListSortedByNameASC();

    @Query("SELECT * FROM BillStore ORDER BY total DESC")
    List<BillStore> getListSortedByTotalPaymentDesc();

    @Query("SELECT * FROM BillStore ORDER BY total ASC")
    List<BillStore> getListSortedByTotalPaymentASC();
}
