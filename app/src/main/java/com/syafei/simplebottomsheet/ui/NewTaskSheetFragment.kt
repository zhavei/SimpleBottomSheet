package com.syafei.simplebottomsheet.ui

import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.syafei.simplebottomsheet.data.TaskItem
import com.syafei.simplebottomsheet.databinding.FragmentNewTaskSheetBinding
import java.time.LocalTime


class NewTaskSheetFragment(var taskItem: TaskItem?) : BottomSheetDialogFragment() {

    private var _binding: FragmentNewTaskSheetBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskViewModel: TaskViewModel
    private var dueTime: LocalTime? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNewTaskSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        taskViewModel = ViewModelProvider(activity).get(TaskViewModel::class.java)

        binding.saveButton.setOnClickListener {
            saveAction()
        }

        binding.btnTimePicker.setOnClickListener {
            openTimePicker()
        }

        if (taskItem != null) {
            binding.taskTitle.text = "Edit Task"
            val editable = Editable.Factory.getInstance()
            binding.etName.text = editable.newEditable(taskItem?.name)
            binding.etDesc.text = editable.newEditable(taskItem?.desc)

            if (taskItem?.dueTime() != null) {
                dueTime = taskItem?.dueTime()
                updateTimeButtonText()
            }

        } else {
            binding.taskTitle.text = "New Task"
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun openTimePicker() {
        if (dueTime == null)
            dueTime = LocalTime.now()
        val listener =
            TimePickerDialog.OnTimeSetListener { timePicker, selectedHours, selectedMinutes ->
                dueTime = LocalTime.of(selectedHours, selectedMinutes)
                updateTimeButtonText()
            }
        val dialogTimePicker =
            TimePickerDialog(activity, listener, dueTime!!.hour, dueTime!!.minute, true)
        dialogTimePicker.setTitle("Task Due")
        dialogTimePicker.show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateTimeButtonText() {
        binding.btnTimePicker.text = String.format("%02d:%02d", dueTime?.hour, dueTime?.minute)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveAction() {
        val taskName = binding.etName.text.toString()
        val taskDesc = binding.etDesc.text.toString()
        val dueTimeString = if (dueTime == null) null else TaskItem.timeFormatter.format(dueTime)

        if (taskItem == null) {
            val newTask = TaskItem(taskName, taskDesc, dueTimeString, null)
            taskViewModel.addTaskItem(newTask)
        } else {
            taskItem?.name = taskName
            taskItem?.desc = taskDesc
            taskItem?.dueTimeString = dueTimeString

            taskViewModel.updateTaskItem(taskItem!!)
        }

        binding.etName.setText("")
        binding.etDesc.setText("")
        dismiss()
        Toast.makeText(requireContext(), "Task $taskName entered", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "NewTaskFragment"

    }

}