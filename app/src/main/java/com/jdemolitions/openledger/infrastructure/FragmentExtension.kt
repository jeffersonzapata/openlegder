package com.jdemolitions.openledger.infrastructure

import androidx.fragment.app.Fragment
import com.jdemolitions.openledger.R
import com.jdemolitions.openledger.ui.ValidationError

object FragmentExtension {
fun Fragment.transformValidationError(validationError: ValidationError): String {
    return when (validationError) {
        is ValidationError.InvalidNumber -> getString(R.string.error_message_invalid_number)
        is ValidationError.MaxLength -> getString(R.string.error_message_max_length_exceeded)
        is ValidationError.EmptyString -> getString(R.string.error_message_mandatory)
        is ValidationError.InvalidDate -> getString(R.string.error_message_invalid_date)
    }
}
}