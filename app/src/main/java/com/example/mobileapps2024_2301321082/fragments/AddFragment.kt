package com.example.mobileapps2024_2301321082.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mobileapps2024_2301321082.R
import com.example.mobileapps2024_2301321082.data.Task
import com.example.mobileapps2024_2301321082.databinding.FragmentAddBinding
import com.example.mobileapps2024_2301321082.viewmodel.TaskViewModel

class AddFragment : Fragment() {

    private lateinit var mTaskViewModel: TaskViewModel
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddBinding.inflate(inflater, container, false)

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        binding.btnAdd.setOnClickListener {
            insertDataToDatabase()
        }

        return binding.root
    }

    private fun insertDataToDatabase() {
        val title = binding.etTitle.text.toString()
        val description = binding.etDesc.text.toString()

        val priority = binding.spinnerPriority.selectedItemPosition + 1

        if (inputCheck(title, description)) {

            val task = Task(0, title, description, priority)

            mTaskViewModel.addTask(task)
            Toast.makeText(requireContext(), "Успешно добавено!", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_addFragment_to_taskListFragment)
        } else {
            Toast.makeText(requireContext(), "Моля попълнете всички полета.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(title: String, description: String): Boolean {
        return !(TextUtils.isEmpty(title) || TextUtils.isEmpty(description))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}