package com.jdemolitions.openledger.infrastructure

import arrow.core.Try

object StringExtension {
    fun String.toNumber(): Try<Int> = Try { this.toInt() }
}
