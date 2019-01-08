package com.jdemolitions.openledger.ui.expense

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.jdemolitions.openledger.DATE_FORMATTER
import com.jdemolitions.openledger.infrastructure.database.dao.ExpenseRecord

class ExpenseItemListViewModel(expenseRecord: ExpenseRecord) : ViewModel() {

    val amount = ObservableField<String>(expenseRecord.amount.toString())

    val date = ObservableField<String>(expenseRecord.date.format(DATE_FORMATTER))

    val description = ObservableField<String>(expenseRecord.description)
}