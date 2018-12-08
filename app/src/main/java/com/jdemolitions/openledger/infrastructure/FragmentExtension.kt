package com.jdemolitions.openledger.infrastructure

import android.app.DatePickerDialog
import androidx.fragment.app.Fragment
import com.jdemolitions.openledger.R
import com.jdemolitions.openledger.ui.ValidationError
import java.time.LocalDate

object FragmentExtension {
    fun Fragment.transformValidationError(validationError: ValidationError): String {
        return when (validationError) {
            is ValidationError.InvalidNumber -> getString(R.string.error_message_invalid_number)
            is ValidationError.MaxLength -> getString(R.string.error_message_max_length_exceeded)
            is ValidationError.MandatoryField -> getString(R.string.error_message_mandatory)
            is ValidationError.InvalidDate -> getString(R.string.error_message_invalid_date)
        }
    }

    fun Fragment.createDatePicker(init: LocalDate, onDateSet: (dateSettled: LocalDate) -> Unit): DatePickerDialog =
            DatePickerDialog(
                    context,
                    DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                        onDateSet(LocalDate.of(year, (month + 1), dayOfMonth))
                    },
                    init.year,
                    (init.monthValue - 1),
                    init.dayOfMonth)
}