package com.jdemolitions.openledger.ui.expense

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import arrow.data.NonEmptyList
import com.jdemolitions.openledger.APP_TAG
import com.jdemolitions.openledger.OpenLedgerRepository
import com.jdemolitions.openledger.databinding.ExpenseInputFragmentBinding
import com.jdemolitions.openledger.infrastructure.ViewExtension.hideKeyboard
import com.jdemolitions.openledger.infrastructure.FragmentExtension.transformValidationError
import com.jdemolitions.openledger.ui.FieldError

val TAG = "$APP_TAG-ExpenseInputFragment"

class ExpenseInputFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val binding: ExpenseInputFragmentBinding = ExpenseInputFragmentBinding.inflate(inflater, container, false)
        val viewModel: ExpenseInputViewModel = ViewModelProviders.of(this).get(ExpenseInputViewModel::class.java)
        val repo: OpenLedgerRepository = OpenLedgerRepository.getInstance(requireContext())

        binding.addExpenseButton.setOnClickListener {
            Log.d(TAG, "validating inputs")
            viewModel.validate(
                    binding.amountEditText.text.toString(),
                    binding.dateEditText.text.toString(),
                    binding.descriptionEditText.text.toString())
        }

        viewModel.validatedExpense.observe(this, Observer {
            Log.d(TAG, "valid expense $it")
            repo.saveExpense(it)
            binding.root.hideKeyboard()
            binding.root.findNavController().popBackStack()
            Toast.makeText(context, "added expense", Toast.LENGTH_LONG).show()
        })

        viewModel.errors.observe(this, Observer { errors: NonEmptyList<FieldError>? ->
            Log.d(TAG, "result inputs $errors")
            errors?.map { error ->
                when (error) {
                    is FieldError.Amount -> binding.amountEditText.error = transformValidationError(error.value)
                    is FieldError.Date -> binding.dateEditText.error = transformValidationError(error.value)
                    is FieldError.Description -> binding.descriptionEditText.error = transformValidationError(error.value)
                }
            }
        })

        return binding.root
    }
}
