package com.jdemolitions.openledger.ui.expense

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.jdemolitions.openledger.infrastructure.database.dao.ExpenseRecord

class ExpenseItemListViewModel(expenseRecord: ExpenseRecord) : ViewModel() {

    val amount = ObservableField<String>(expenseRecord.amount.toString())

    val date = ObservableField<String>(expenseRecord.date)

    val description = ObservableField<String>(expenseRecord.description)
}