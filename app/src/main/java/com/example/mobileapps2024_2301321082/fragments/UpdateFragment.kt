package com.example.mobileapps2024_2301321082.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mobileapps2024_2301321082.R
import com.example.mobileapps2024_2301321082.data.Task
import com.example.mobileapps2024_2301321082.databinding.FragmentUpdateBinding
import com.example.mobileapps2024_2301321082.viewmodel.TaskViewModel

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mTaskViewModel: TaskViewModel
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)


        binding.etUpdateTitle.setText(args.currentTask.title)
        binding.etUpdateDesc.setText(args.currentTask.description)
        binding.spinnerUpdatePriority.setSelection(args.currentTask.priority - 1)


        binding.btnUpdate.setOnClickListener {
            updateItem()
        }


        binding.btnDelete.setOnClickListener {
            deleteUser()
        }

        return binding.root
    }

    private fun updateItem() {
        val title = binding.etUpdateTitle.text.toString()
        val description = binding.etUpdateDesc.text.toString()
        val priority = binding.spinnerUpdatePriority.selectedItemPosition + 1

        if (inputCheck(title, description)) {
            val updatedTask = Task(args.currentTask.id, title, description, priority)
            mTaskViewModel.updateTask(updatedTask)
            Toast.makeText(requireContext(), "Успешно обновено!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_taskListFragment)
        } else {
            Toast.makeText(requireContext(), "Попълнете всички полета", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(title: String, description: String): Boolean {
        return !(TextUtils.isEmpty(title) || TextUtils.isEmpty(description))
    }


    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Да") { _, _ ->
            mTaskViewModel.deleteTask(args.currentTask)
            Toast.makeText(requireContext(), "Успешно изтрито!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_taskListFragment)
        }
        builder.setNegativeButton("Не") { _, _ -> }
        builder.setTitle("Изтриване на задача?")
        builder.setMessage("Сигурни ли сте, че искате да изтриете '${args.currentTask.title}'?")
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}