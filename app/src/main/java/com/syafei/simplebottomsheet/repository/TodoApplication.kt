package com.syafei.simplebottomsheet.repository

import android.app.Application
import com.syafei.simplebottomsheet.data.TaskItemDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class TodoApplication : Application() {

    //val applicationScope = CoroutineScope(SupervisorJob())

    //benefit this class is for an instance of database and repository then using it in MainActivity
    private val database by lazy {
        TaskItemDataBase.getDatabase(this)
    }
    val repository by lazy {
        TaskItemRepository(database.taskItemDao())
    }
}