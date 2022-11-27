package com.syafei.simplebottomsheet.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.syafei.simplebottomsheet.data.TaskItem
import com.syafei.simplebottomsheet.repository.TaskItemRepository
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*

class TaskViewModel(private val repository: TaskItemRepository) : ViewModel() {

    /*var name = MutableLiveData<String>()
    var desk = MutableLiveData<String>()*/

    var taskItems: LiveData<List<TaskItem>> = repository.allTaskItems.asLiveData()

    fun addTaskItem(newTaskItem: TaskItem) = viewModelScope.launch {
        repository.insertTaskItem(newTaskItem)
    }

    fun updateTaskItem(updateTaskItem: TaskItem) = viewModelScope.launch {
        repository.updateTaskItem(updateTaskItem)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setCompleted(compTaskItem: TaskItem) = viewModelScope.launch {
        if (!compTaskItem.isCompleted())
            compTaskItem.completeDateString = TaskItem.dateFormatter.format(LocalDate.now())
        repository.updateTaskItem(compTaskItem)
    }
    /* init {
         taskItems.value = mutableListOf()
     }*/

    /*fun addTaskItem(newTaskItem: TaskItem) {
        val list = taskItems.value
        list?.add(newTaskItem)
        taskItems.postValue(list)
    }*/

    /*fun updateTaskItem(id: UUID, name: String, desc: String, dueTime: LocalTime?) {

        val list = taskItems.value
        val task = list!!.find { it.id == id }!!
        task.name = name
        task.desc = desc
        task.dueTimeString = dueTime
        taskItems.postValue(list)
    }*/

    /* @RequiresApi(Build.VERSION_CODES.O)
     fun setCompleted(taskItem: TaskItem) {

         val list = taskItems.value
         val task = list!!.find { it.id == taskItem.id }!!
         if (task.completeDateString == null){
             task.completeDateString = LocalDate.now()
         }
         taskItems.postValue(list)
     }*/

}

class TaskItemModelFactory(private val repository: TaskItemRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java))
            return TaskViewModel(repository) as T

        throw java.lang.IllegalArgumentException("unknown Class for ViewModel")
    }

}