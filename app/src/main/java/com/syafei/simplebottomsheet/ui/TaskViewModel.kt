package com.syafei.simplebottomsheet.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.syafei.simplebottomsheet.data.TaskItem
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

class TaskViewModel : ViewModel() {

    var name = MutableLiveData<String>()
    var desk = MutableLiveData<String>()

    var taskItems = MutableLiveData<MutableList<TaskItem>>()

    init {
        taskItems.value = mutableListOf()
    }

    fun addTaskItem(newTaskItem: TaskItem) {
        val list = taskItems.value
        list?.add(newTaskItem)
        taskItems.postValue(list)
    }

    fun updateTaskItem(id: UUID, name: String, desc: String, dueTime: LocalTime?) {

        val list = taskItems.value
        val task = list!!.find { it.id == id }!!
        task.name = name
        task.desc = desc
        task.dueTime = dueTime
        taskItems.postValue(list)
    }



    @RequiresApi(Build.VERSION_CODES.O)
    fun setCompleted(taskItem: TaskItem) {

        val list = taskItems.value
        val task = list!!.find { it.id == taskItem.id }!!
        if (task.completeDate == null){
            task.completeDate = LocalDate.now()
        }
        taskItems.postValue(list)
    }

}