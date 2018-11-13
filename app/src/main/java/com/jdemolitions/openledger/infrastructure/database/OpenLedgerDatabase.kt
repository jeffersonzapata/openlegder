package com.jdemolitions.openledger.infrastructure.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jdemolitions.openledger.infrastructure.database.dao.ExpenseDao
import com.jdemolitions.openledger.infrastructure.database.dao.ExpenseRecord

@Database(entities = [ExpenseRecord::class], version = 1, exportSchema = false)
abstract class OpenLedgerDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao

    companion object {

        @Volatile private var instance: OpenLedgerDatabase? = null

        fun getInstance(context: Context): OpenLedgerDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): OpenLedgerDatabase {
            return Room.databaseBuilder(context, OpenLedgerDatabase::class.java, "openledger-db").build()
        }
    }
}