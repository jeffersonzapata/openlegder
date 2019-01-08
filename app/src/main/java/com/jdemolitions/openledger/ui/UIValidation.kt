package com.jdemolitions.openledger.ui

import android.util.Log
import arrow.data.Validated
import arrow.data.invalid
import arrow.data.valid
import com.jdemolitions.openledger.APP_TAG
import com.jdemolitions.openledger.infrastructure.StringExtension.toNumber
import com.jdemolitions.openledger.infrastructure.StringExtension.toLocalDate
import java.time.LocalDate
import java.time.format.DateTimeFormatter

sealed class FieldError {
    data class Amount(val value: ValidationError) : FieldError()
    data class Date(val value: ValidationError) : FieldError()
    data class Description(val value: ValidationError) : FieldError()
}

sealed class ValidationError {
    object MandatoryField : ValidationError()
    object MaxLength : ValidationError()
    object InvalidNumber : ValidationError()
    object InvalidDate : ValidationError()
}

val TAG = "$APP_TAG-Validations"

object Validations {

    fun String.mandatory(): Validated<ValidationError, String> =
            when {
                this.isNotEmpty() -> this.valid()
                else -> ValidationError.MandatoryField.invalid()
            }

    fun String.maxLength(maxLength: Int): Validated<ValidationError, String> =
            when {
                this.length <= maxLength -> this.valid()
                else -> ValidationError.MaxLength.invalid()
            }

    fun String.mandatoryDate(dateFormatter: DateTimeFormatter): Validated<ValidationError, LocalDate> =
            when {
                this.isEmpty() -> ValidationError.MandatoryField.invalid()
                else -> this.toLocalDate(dateFormatter)
                        .fold(
                                { it: Throwable ->
                                    Log.d(TAG, "Error validating $this", it)
                                    return@fold ValidationError.InvalidDate.invalid<ValidationError, LocalDate>()
                                },
                                { it -> it.valid() }
                        )
            }

    fun String.mandatoryNumber(): Validated<ValidationError, Int> =
            when {
                this.isEmpty() -> ValidationError.MandatoryField.invalid()
                else -> this.toNumber()
                        .fold(
                                { it: Throwable ->
                                    Log.d(TAG, "Error validating $this", it)
                                    return@fold ValidationError.InvalidNumber.invalid<ValidationError, Int>()
                                },
                                { it -> it.valid() }
                        )
            }
}
