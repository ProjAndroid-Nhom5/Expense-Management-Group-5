package com.example.expensemanagement.Bill.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.expensemanagement.Bill.Model.BillStore;
import com.example.expensemanagement.Bill.Model.BillSupply;

import java.util.List;

@Dao
public interface BillSupplyDAO {
    @Insert
    void insertBillSupply(BillSupply billSupply);

    @Delete
    void deleteBillSupply(BillSupply billSupply);

    @Query("SELECT * FROM BillSupply")
    List<BillSupply> getListBillSupply();

    @Query("DELETE FROM BillSupply")
    void deleteAllBillSupply();

    @Query("SELECT * FROM BillSupply ORDER BY nameSupplier DESC")
    List<BillSupply> getListSortedByNameDesc();

    @Query("SELECT * FROM BillSupply ORDER BY nameSupplier ASC")
    List<BillSupply> getListSortedByNameASC();

    @Query("SELECT * FROM BillSupply ORDER BY total DESC")
    List<BillSupply> getListSortedByTotalPaymentDesc();

    @Query("SELECT * FROM BillSupply ORDER BY total ASC")
    List<BillSupply> getListSortedByTotalPaymentASC();
}
