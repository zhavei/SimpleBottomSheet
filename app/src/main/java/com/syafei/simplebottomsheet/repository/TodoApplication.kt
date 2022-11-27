package com.syafei.simplebottomsheet.repository

import android.app.Application
import com.syafei.simplebottomsheet.data.TaskItemDataBase

class TodoApplication : Application() {
    private val database by lazy {
        TaskItemDataBase.getDatabase(this)
    }
    val repository by lazy {
        TaskItemRepository(database.taskItemDao())
    }
}