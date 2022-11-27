package com.syafei.simplebottomsheet.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.syafei.simplebottomsheet.data.TaskItem
import com.syafei.simplebottomsheet.databinding.ActivityMainBinding
import com.syafei.simplebottomsheet.repository.TodoApplication

class MainActivity : AppCompatActivity(), TaskItemClickListener {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var binding: ActivityMainBinding

    //by viewModel KTX with instance of database and repository
    private val taskViewModel: TaskViewModel by viewModels {
        TaskItemModelFactory((application as TodoApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        binding.newTaskButton.setOnClickListener {

            //fragment bottomSheet showing up
            NewTaskSheetFragment(null).show(supportFragmentManager, NewTaskSheetFragment.TAG)
        }

        //this used for textView only without a data class that because used format method
        /*taskViewModel.name.observe(this) {
            binding.textTaskName.text = String.format("Taks Name: %s", it)
        }

        taskViewModel.desk.observe(this) {
            binding.textDescription.text = String.format("Task Description: %s", it)
        }*/

        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        val mainActivity = this@MainActivity
        taskViewModel.taskItems.observe(mainActivity) {
            binding.toDoListRecyclerview.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = TaskItemAdapter(it, mainActivity)

            }
        }

    }

    override fun editTaskItemClicked(taskItem: TaskItem) {
        NewTaskSheetFragment(taskItem).show(supportFragmentManager, NewTaskSheetFragment.TAG)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun completeTaskItemClicked(taskItem: TaskItem) {
        taskViewModel.setCompleted(taskItem)
    }
}