package com.example.expensemanagement.Bill.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.expensemanagement.Bill.Model.BillSupply;
import com.example.expensemanagement.Bill.Model.BillWorkshift;

import java.util.List;

@Dao
public interface BillWorkshiftDAO {
    @Insert
    void insertBillWorkshift(BillWorkshift billWorkshift);

    @Delete
    void deleteBillWorkshift(BillWorkshift billWorkshift);

    @Query("SELECT * FROM BillWorkshift")
    List<BillWorkshift> getListBillWorkshift();

    @Query("DELETE FROM BillWorkshift")
    void deleteAllBillWorkshift();

    @Query("SELECT * FROM BillWorkshift ORDER BY nameEmploye DESC")
    List<BillWorkshift> getListSortedByNameDesc();

    @Query("SELECT * FROM BillWorkshift ORDER BY nameEmploye ASC")
    List<BillWorkshift> getListSortedByNameASC();

    @Query("SELECT * FROM BillWorkshift ORDER BY total DESC")
    List<BillWorkshift> getListSortedByTotalPaymentDesc();

    @Query("SELECT * FROM BillWorkshift ORDER BY total ASC")
    List<BillWorkshift> getListSortedByTotalPaymentASC();
}
