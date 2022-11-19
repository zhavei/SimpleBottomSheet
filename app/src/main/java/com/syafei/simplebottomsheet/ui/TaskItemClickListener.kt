package com.syafei.simplebottomsheet.ui

import com.syafei.simplebottomsheet.data.TaskItem

interface TaskItemClickListener {

    fun editTaskItemClicked(taskItem: TaskItem)
    fun completeTaskItemClicked(taskItem: TaskItem)
}