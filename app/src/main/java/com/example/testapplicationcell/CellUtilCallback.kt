package com.example.testapplicationcell

import androidx.recyclerview.widget.DiffUtil

class CellUtilCallback:DiffUtil.ItemCallback<Cell>() {
    override fun areItemsTheSame(oldItem: Cell, newItem: Cell): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: Cell, newItem: Cell): Boolean {
        return oldItem==newItem
    }
}