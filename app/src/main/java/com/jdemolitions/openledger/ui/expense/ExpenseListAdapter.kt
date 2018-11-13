package com.jdemolitions.openledger.ui.expense

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jdemolitions.openledger.R
import com.jdemolitions.openledger.databinding.ExpenseItemListFragmentBinding
import com.jdemolitions.openledger.infrastructure.database.dao.ExpenseRecord

class ExpenseListAdapter : ListAdapter<ExpenseRecord, ExpenseAdapterViewHolder>(ExpenseAdapterDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseAdapterViewHolder {
        return ExpenseAdapterViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.expense_item_list_fragment,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: ExpenseAdapterViewHolder, position: Int) {
        getItem(position).let { expenseRecord ->
            with(holder) {
                itemView.tag = expenseRecord
                bind(expenseRecord)
            }
        }
    }
}

class ExpenseAdapterViewHolder(private val binding: ExpenseItemListFragmentBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(expenseRecord: ExpenseRecord) {
        with(binding) {
            this.viewModel = ExpenseItemListViewModel(expenseRecord)
            executePendingBindings()
        }
    }
}

class ExpenseAdapterDiffCallback : DiffUtil.ItemCallback<ExpenseRecord>() {
    override fun areItemsTheSame(oldItem: ExpenseRecord, newItem: ExpenseRecord): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ExpenseRecord, newItem: ExpenseRecord): Boolean {
        return oldItem === newItem
    }
}