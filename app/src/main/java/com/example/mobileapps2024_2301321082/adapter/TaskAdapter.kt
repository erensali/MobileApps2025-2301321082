package com.example.mobileapps2024_2301321082.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapps2024_2301321082.R
import com.example.mobileapps2024_2301321082.data.Task
import com.example.mobileapps2024_2301321082.fragments.TaskListFragmentDirections

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.MyViewHolder>() {

    private var taskList = emptyList<Task>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tv_task_title)
        val description: TextView = itemView.findViewById(R.id.tv_task_desc)
        val priority: TextView = itemView.findViewById(R.id.tv_priority_indicator)
        val rowLayout: ConstraintLayout = itemView.findViewById(R.id.rowLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_task_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = taskList[position]

        holder.title.text = currentItem.title
        holder.description.text = currentItem.description


        when(currentItem.priority) {
            1 -> {
                holder.priority.text = "Low"
                holder.priority.setTextColor(Color.parseColor("#00FF00"))
            }
            2 -> {
                holder.priority.text = "Medium"
                holder.priority.setTextColor(Color.parseColor("#FFC107"))
            }
            3 -> {
                holder.priority.text = "High"
                holder.priority.setTextColor(Color.RED)
            }
        }


        holder.rowLayout.setOnClickListener {

            val action = TaskListFragmentDirections.actionTaskListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(task: List<Task>) {
        this.taskList = task
        notifyDataSetChanged()
    }
}