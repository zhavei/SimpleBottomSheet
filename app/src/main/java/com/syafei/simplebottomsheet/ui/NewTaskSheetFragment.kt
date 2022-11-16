package com.syafei.simplebottomsheet.ui

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.syafei.simplebottomsheet.data.TaskItem
import com.syafei.simplebottomsheet.databinding.FragmentNewTaskSheetBinding


class NewTaskSheetFragment(var taskItem: TaskItem?) : BottomSheetDialogFragment() {

    private var _binding: FragmentNewTaskSheetBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNewTaskSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        taskViewModel = ViewModelProvider(activity).get(TaskViewModel::class.java)

        binding.saveButton.setOnClickListener {
            saveAction()

        }

        if (taskItem != null) {
            binding.taskTitle.text = "Edit Task"
            val editable = Editable.Factory.getInstance()
            binding.name.text = editable.newEditable(taskItem?.name)
            binding.desc.text = editable.newEditable(taskItem?.desc)
        } else {
            binding.taskTitle.text = "New Task"
        }

    }

    private fun saveAction() {
        val taskName = binding.name.text.toString()
        val taskDesc = binding.desc.text.toString()

        if (taskItem == null) {
            val newTask = TaskItem(taskName, taskDesc, null, null)
            taskViewModel.addTaskItem(newTask)
        } else {
            taskViewModel.updateTaskItem(taskItem!!.id, taskName, taskDesc, null)
        }

        binding.name.setText("")
        binding.desc.setText("")
        dismiss()
        Toast.makeText(requireContext(), "Task $taskName entered", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        val TAG = "NewTaskFragment"

    }

}