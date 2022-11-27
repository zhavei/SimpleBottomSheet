package com.syafei.simplebottomsheet.data

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.syafei.simplebottomsheet.R
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@Entity(tableName = "taks_item_table")
data class TaskItem(
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "desc")
    var desc: String,
    @ColumnInfo(name = "dueTimeString")
    var dueTimeString: String?,
    @ColumnInfo(name = "completedDateString")
    var completeDateString: String?,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun completedDate(): LocalDate? =
        if (completeDateString == null) null else LocalDate.parse(completeDateString, dateFormatter)

    @RequiresApi(Build.VERSION_CODES.O)
    fun dueTime(): LocalTime? =
        if (dueTimeString == null) null else LocalTime.parse(dueTimeString, timeFormatter)


    @RequiresApi(Build.VERSION_CODES.O)
    fun isCompleted() = completedDate() != null

    @RequiresApi(Build.VERSION_CODES.O)
    fun imageResource() = if (isCompleted()) R.drawable.check_24 else R.drawable.unchecked_24

    @RequiresApi(Build.VERSION_CODES.O)
    fun imageColor(context: Context): Int = if (isCompleted()) purple(context) else black(context)

    private fun purple(context: Context) = ContextCompat.getColor(context, R.color.purple_500)
    private fun black(context: Context) = ContextCompat.getColor(context, R.color.black)

    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        val timeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_TIME

        @RequiresApi(Build.VERSION_CODES.O)
        val dateFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE
    }
}
