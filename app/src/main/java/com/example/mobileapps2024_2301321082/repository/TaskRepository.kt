package com.example.mobileapps2024_2301321082.repository

import androidx.lifecycle.LiveData
import com.example.mobileapps2024_2301321082.data.Task
import com.example.mobileapps2024_2301321082.data.TaskDao

class TaskRepository(private val taskDao: TaskDao) {

    val readAllData: LiveData<List<Task>> = taskDao.readAllData()

    suspend fun addTask(task: Task) {
        taskDao.addTask(task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    fun searchDatabase(query: String): LiveData<List<Task>> {
        return taskDao.searchDatabase(query)
    }
}