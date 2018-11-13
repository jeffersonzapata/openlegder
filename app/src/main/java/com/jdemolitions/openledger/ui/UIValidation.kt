package com.jdemolitions.openledger.ui

import android.util.Log
import arrow.core.Try
import arrow.data.Validated
import arrow.data.invalid
import arrow.data.valid
import com.jdemolitions.openledger.APP_TAG

sealed class FieldError {
    data class Amount(val value: ValidationError) : FieldError()
    data class Date(val value: ValidationError) : FieldError()
    data class Description(val value: ValidationError) : FieldError()
}

sealed class ValidationError {
    object EmptyString : ValidationError()
    object MaxLength : ValidationError()
    object InvalidNumber : ValidationError()
    object InvalidDate : ValidationError()
}

val TAG = "$APP_TAG-Validations"
object Validations {

    fun String.notEmptyString(): Validated<ValidationError, String> =
            when {
                this.isNotEmpty() -> this.valid()
                else -> ValidationError.EmptyString.invalid()
            }

    fun String.maxLength(maxLength: Int): Validated<ValidationError, String> =
            when {
                this.length <= maxLength -> this.valid()
                else -> ValidationError.MaxLength.invalid()
            }

    fun String.validateNumber(): Validated<ValidationError, Int> {
            return Try { this.toInt() }
                    .fold(
                            { it: Throwable ->
                                Log.d(TAG, "Error validating $this", it)
                                ValidationError.InvalidNumber.invalid()
                            },
                            { it -> it.valid() }
                    )
    }
}
