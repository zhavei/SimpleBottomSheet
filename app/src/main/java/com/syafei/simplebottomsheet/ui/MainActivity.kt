package com.syafei.simplebottomsheet.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.syafei.simplebottomsheet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var binding: ActivityMainBinding

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        binding.newTaskButton.setOnClickListener {

            NewTaskSheetFragment(null).show(supportFragmentManager, NewTaskSheetFragment.TAG)
        }

        //this used for textView only without a data class that because used format method
        /*taskViewModel.name.observe(this) {
            binding.textTaskName.text = String.format("Taks Name: %s", it)
        }

        taskViewModel.desk.observe(this) {
            binding.textDescription.text = String.format("Task Description: %s", it)
        }*/

    }
}