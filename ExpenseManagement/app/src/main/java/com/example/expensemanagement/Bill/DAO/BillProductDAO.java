package com.example.expensemanagement.Bill.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.expensemanagement.Bill.Model.BillProduct;
import com.example.expensemanagement.Bill.Model.BillWorkshift;

import java.util.List;

@Dao
public interface BillProductDAO {
    @Insert
    void insertBillProduct(BillProduct billProduct);

    @Delete
    void deleteBillProduct(BillProduct billProduct);

    @Query("SELECT * FROM BillProduct")
    List<BillProduct> getListBillProduct();

    @Query("DELETE FROM BillProduct")
    void deleteAllBillProduct();

    @Query("SELECT * FROM BillProduct ORDER BY nameProduct DESC")
    List<BillProduct> getListSortedByNameDesc();

    @Query("SELECT * FROM BillProduct ORDER BY nameProduct ASC")
    List<BillProduct> getListSortedByNameASC();

    @Query("SELECT * FROM BillProduct ORDER BY Quantity DESC")
    List<BillProduct> getListSortedByQuantityDesc();

    @Query("SELECT * FROM BillProduct ORDER BY Quantity ASC")
    List<BillProduct> getListSortedByQuantityASC();
}
