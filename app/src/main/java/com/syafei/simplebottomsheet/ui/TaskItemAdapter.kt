package com.syafei.simplebottomsheet.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.syafei.simplebottomsheet.data.TaskItem
import com.syafei.simplebottomsheet.databinding.TaskItemCellBinding

class TaskItemAdapter(
    private val taskItem: List<TaskItem>
) : RecyclerView.Adapter<TaksItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaksItemViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = TaskItemCellBinding.inflate(from, parent, false)
        return TaksItemViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: TaksItemViewHolder, position: Int) {
        holder.bindTaskItem(taskItem[position])
    }

    override fun getItemCount(): Int = taskItem.size


}