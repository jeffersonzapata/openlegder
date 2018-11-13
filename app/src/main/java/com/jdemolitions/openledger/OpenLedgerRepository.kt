package com.jdemolitions.openledger

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.jdemolitions.openledger.data.Expense
import com.jdemolitions.openledger.infrastructure.database.dao.ExpenseDao
import com.jdemolitions.openledger.infrastructure.database.dao.ExpenseRecord
import com.jdemolitions.openledger.infrastructure.database.OpenLedgerDatabase

val TAG = "$APP_TAG-OpenLedgerRepository"

interface OpenLedgerRepository {

    val expenseDao: ExpenseDao

    fun getExpense(expenseId: Long) = expenseDao.getExpense(expenseId)

    fun getExpenses(): LiveData<List<ExpenseRecord>> {
        Log.d(TAG, "getting expenses")
        return expenseDao.getExpenses()
    }

    fun saveExpense(expense: Expense) {
        runOnIoThread {
            Log.d(TAG, "saving expense")
            expenseDao.insert(ExpenseRecord(expense.id, expense.amount, expense.date, expense.description))
        }
    }

    fun deleteAll() {
        runOnIoThread {
            Log.d(TAG, "deleting all")
            expenseDao.deleteAll()
        }
    }

    companion object {
        @Volatile
        private var instance: OpenLedgerRepository? = null

        fun getInstance(context: Context): OpenLedgerRepository =
                instance ?: synchronized(this) {
                    Log.d(TAG, "creating repo instance")
                    instance ?: object : OpenLedgerRepository {
                        val db = OpenLedgerDatabase.getInstance(context)
                        override val expenseDao: ExpenseDao = db.expenseDao()
                    }.also {
                        Log.d(TAG, "getting instance")
                        instance = it
                    }
                }
    }
}