package com.jdemolitions.openledger.ui.expense

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import arrow.data.NonEmptyList
import com.jdemolitions.openledger.data.Expense
import com.jdemolitions.openledger.ui.FieldError

class ExpenseInputViewModel : ViewModel() {

    private val mValidatedExpense: MutableLiveData<Expense> = MutableLiveData()

    private val mErrors: MutableLiveData<NonEmptyList<FieldError>> = MutableLiveData()

    val validatedExpense: LiveData<Expense> = mValidatedExpense

    val errors: LiveData<NonEmptyList<FieldError>> = mErrors

    fun validate(amount: String, date: String, description: String) {
        Expense
                .validate(amount = amount, date = date, description = description)
                .fold({ ve -> mErrors.value = ve }, { e -> mValidatedExpense.value = e })
    }
}