package com.syafei.simplebottomsheet.ui

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.syafei.simplebottomsheet.data.TaskItem
import com.syafei.simplebottomsheet.databinding.TaskItemCellBinding

class TaksItemViewHolder(
    private val context: Context,
    private val binding: TaskItemCellBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTaskItem(taskItem: TaskItem) {
        binding.tvName.text = taskItem.name
    }
}