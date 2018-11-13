package com.jdemolitions.openledger.infrastructure.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expenses")
    fun getExpenses(): LiveData<List<ExpenseRecord>>

    @Query("SELECT * FROM expenses where id = :expenseId")
    fun getExpense(expenseId: Long): LiveData<ExpenseRecord>

    @Insert
    fun insert(expenseRecord: ExpenseRecord): Long

    @Delete
    fun delete(expenseRecord: ExpenseRecord)

    @Query("DELETE FROM expenses")
    fun deleteAll()
}