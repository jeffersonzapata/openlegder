package com.jdemolitions.openledger.data

import arrow.data.Nel
import arrow.data.Validated
import arrow.data.fix
import arrow.data.nel
import arrow.instances.nonemptylist.semigroup.semigroup
import arrow.instances.validated.applicative.applicative
import com.jdemolitions.openledger.ui.FieldError
import com.jdemolitions.openledger.ui.Validations.maxLength
import com.jdemolitions.openledger.ui.Validations.notEmptyString
import com.jdemolitions.openledger.ui.Validations.validateNumber
import java.util.UUID.randomUUID

data class Expense(val id: String, val amount: Long, val date: String, val description: String) {

    companion object {
        fun validate(amount: String, date: String, description: String): Validated<Nel<FieldError>, Expense> {
            return Validated
                    .applicative<Nel<FieldError>>(Nel.semigroup())
                    .map(
                            amount.validateNumber().leftMap { FieldError.Amount(it).nel() },
                            date.notEmptyString().leftMap { FieldError.Date(it).nel() },
                            description.maxLength(20).leftMap { FieldError.Description(it).nel() }
                    ) { Expense(randomUUID().toString(), it.a.toLong(), it.b, it.c) }
                    .fix()
        }
    }
}
