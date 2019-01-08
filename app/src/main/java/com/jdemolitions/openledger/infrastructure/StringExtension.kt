package com.jdemolitions.openledger.infrastructure

import arrow.core.Try
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object StringExtension {
    fun String.toNumber(): Try<Int> = Try { this.toInt() }

    fun String.toLocalDate(dateFormatter: DateTimeFormatter): Try<LocalDate> = Try {
        LocalDate.parse(this, dateFormatter)
    }
}
