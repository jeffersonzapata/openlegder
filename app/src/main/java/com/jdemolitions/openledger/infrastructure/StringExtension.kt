package com.jdemolitions.openledger.infrastructure

import arrow.core.Try
import com.jdemolitions.openledger.DATE_FORMATTER
import java.time.LocalDate

object StringExtension {
    fun String.toNumber(): Try<Int> = Try { this.toInt() }

    fun String.toLocalDate(): Try<LocalDate> = Try {
        LocalDate.parse(this, DATE_FORMATTER)
    }
}
