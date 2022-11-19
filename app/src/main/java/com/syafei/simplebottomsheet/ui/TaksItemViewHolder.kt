package com.syafei.simplebottomsheet.ui

import android.content.Context
import android.graphics.Paint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.syafei.simplebottomsheet.data.TaskItem
import com.syafei.simplebottomsheet.databinding.TaskItemCellBinding
import java.time.format.DateTimeFormatter

class TaksItemViewHolder(
    private val context: Context,
    private val binding: TaskItemCellBinding,
    private val clickListener: TaskItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    @RequiresApi(Build.VERSION_CODES.O)
    private val timeFormat = DateTimeFormatter.ofPattern("HH:mm")

    @RequiresApi(Build.VERSION_CODES.O)
    fun bindTaskItem(taskItem: TaskItem) {
        binding.tvName.text = taskItem.name

        if (taskItem.isCompleted()) {
            binding.tvName.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.tvDueTime.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }

        binding.btnComplete.setImageResource(taskItem.imageResource())
        binding.btnComplete.setColorFilter(taskItem.imageColor(context))

        binding.btnComplete.setOnClickListener{
            clickListener.completeTaskItemClicked(taskItem)
        }

        binding.taskCellContainer.setOnClickListener{
            clickListener.editTaskItemClicked(taskItem)
        }

        if (taskItem.dueTime != null)
            binding.tvDueTime.text = timeFormat.format(taskItem.dueTime)
        else
            binding.tvDueTime.text = ""

    }


}