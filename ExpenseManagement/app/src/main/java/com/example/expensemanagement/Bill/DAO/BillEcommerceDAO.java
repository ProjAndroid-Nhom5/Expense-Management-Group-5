package com.example.expensemanagement.Bill.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.expensemanagement.Bill.Model.BillEcommerce;

import java.util.List;

@Dao
public interface BillEcommerceDAO {
    @Insert
    void insertBillEcommerce(BillEcommerce billEcommerce);

    @Delete
    void deleteBillEcommerce(BillEcommerce BillEcommerce);

    @Query("SELECT * FROM BillEcommerce")
    List<BillEcommerce> getListBillEcommerce();

    @Query("DELETE FROM BillEcommerce")
    void deleteAllBillEcommerce();

    @Query("SELECT * FROM BillEcommerce ORDER BY nameEcommerce DESC")
    List<BillEcommerce> getListSortedByNameDesc();

    @Query("SELECT * FROM BillEcommerce ORDER BY nameEcommerce ASC")
    List<BillEcommerce> getListSortedByNameASC();

    @Query("SELECT * FROM BillEcommerce ORDER BY totalPayment DESC")
    List<BillEcommerce> getListSortedByTotalPaymentDesc();

    @Query("SELECT * FROM BillEcommerce ORDER BY totalPayment ASC")
    List<BillEcommerce> getListSortedByTotalPaymentASC();
}
