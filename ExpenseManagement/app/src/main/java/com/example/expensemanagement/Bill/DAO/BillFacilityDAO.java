package com.example.expensemanagement.Bill.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.expensemanagement.Bill.Model.BillEcommerce;
import com.example.expensemanagement.Bill.Model.BillFacility;

import java.util.List;

@Dao
public interface BillFacilityDAO {
    @Insert
    void insertBillFacility(BillFacility billFacility);

    @Delete
    void deleteBillFacility(BillFacility billFacility);

    @Query("SELECT * FROM BillFacility")
    List<BillFacility> getListBillFacility();

    @Query("DELETE FROM BillFacility")
    void deleteAllBillFacility();

    @Query("SELECT * FROM BillFacility ORDER BY nameFacility DESC")
    List<BillFacility> getListSortedByNameDesc();

    @Query("SELECT * FROM BillFacility ORDER BY nameFacility ASC")
    List<BillFacility> getListSortedByNameASC();

    @Query("SELECT * FROM BillFacility ORDER BY totalPayment DESC")
    List<BillFacility> getListSortedByTotalPaymentDesc();

    @Query("SELECT * FROM BillFacility ORDER BY totalPayment ASC")
    List<BillFacility> getListSortedByTotalPaymentASC();
}
