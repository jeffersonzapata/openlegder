package com.jdemolitions.openledger.infrastructure.database.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class ExpenseRecord(
    @PrimaryKey @ColumnInfo val id: String,
    @ColumnInfo val amount: Long,
    @ColumnInfo val date: String,
    @ColumnInfo val description: String
)