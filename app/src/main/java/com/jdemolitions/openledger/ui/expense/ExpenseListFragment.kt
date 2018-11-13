package com.jdemolitions.openledger.ui.expense

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.jdemolitions.openledger.OpenLedgerRepository
import com.jdemolitions.openledger.databinding.ExpenseListFragmentBinding

import androidx.navigation.findNavController

class ExpenseListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: ExpenseListFragmentBinding = ExpenseListFragmentBinding.inflate(inflater, container, false)
        val repo: OpenLedgerRepository = OpenLedgerRepository.getInstance(requireContext())
        val adapter = ExpenseListAdapter()

        binding.expenseList.adapter = adapter

        repo.getExpenses().observe(this, Observer { expenseRecords ->
            binding.hasExpenses = expenseRecords?.isNotEmpty()
            adapter.submitList(expenseRecords)
        })

        binding.floatingAddExpenseButton.setOnClickListener { view: View ->
            view.findNavController().navigate(ExpenseListFragmentDirections.itemExpenseFragmentToItemExpenseInputFragment())
        }

        return binding.root
    }
}