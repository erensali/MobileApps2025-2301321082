package com.example.mobileapps2024_2301321082.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController
import com.example.mobileapps2024_2301321082.R
import com.example.mobileapps2024_2301321082.adapter.TaskAdapter
import com.example.mobileapps2024_2301321082.databinding.FragmentTaskListBinding
import com.example.mobileapps2024_2301321082.viewmodel.TaskViewModel

class TaskListFragment : Fragment() {

    private var _binding: FragmentTaskListBinding? = null
    private val binding get() = _binding!!

    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTaskListBinding.inflate(inflater, container, false)


        val adapter = TaskAdapter()
        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)


        mTaskViewModel.allTasks.observe(viewLifecycleOwner) { task ->
            adapter.setData(task)
        }


        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_taskListFragment_to_addFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}