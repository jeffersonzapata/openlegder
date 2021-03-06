package com.jdemolitions.openledger.infrastructure

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

object ViewExtension {
    fun View.hideKeyboard() {
        val imm: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}