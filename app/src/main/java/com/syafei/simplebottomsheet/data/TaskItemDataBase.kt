package com.syafei.simplebottomsheet.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.syafei.simplebottomsheet.repository.TaskItemDao

@Database(entities = [TaskItem::class], version = 1, exportSchema = false)
abstract class TaskItemDataBase : RoomDatabase() {

    abstract fun taskItemDao(): TaskItemDao

    companion object {
        @Volatile
        private var INSTANCE: TaskItemDataBase? = null

        fun getDatabase(context: Context): TaskItemDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskItemDataBase::class.java,
                    "task_Item_database"
                ).build()
                INSTANCE = instance
                instance
            }

        }

    }
}