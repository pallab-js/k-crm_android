package com.nexus.crm.data.repository

import com.nexus.crm.data.local.TaskDao
import com.nexus.crm.data.model.Task
import kotlinx.coroutines.flow.Flow
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
) {
    fun getAllTasks(): Flow<List<Task>> = taskDao.getAllTasks()

    fun getPendingTasks(): Flow<List<Task>> = taskDao.getPendingTasks()

    fun getCompletedTasks(): Flow<List<Task>> = taskDao.getCompletedTasks()

    fun getTasksForToday(): Flow<List<Task>> {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val startOfDay = calendar.timeInMillis

        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        val endOfDay = calendar.timeInMillis

        return taskDao.getTasksForDate(startOfDay, endOfDay)
    }

    fun getOverdueTasks(): Flow<List<Task>> = taskDao.getOverdueTasks(System.currentTimeMillis())

    fun getTaskById(id: Long): Flow<Task?> = taskDao.getTaskById(id)

    fun getPendingTaskCount(): Flow<Int> = taskDao.getPendingTaskCount()

    suspend fun insertTask(task: Task): Long = taskDao.insertTask(task)

    suspend fun updateTask(task: Task) = taskDao.updateTask(task)

    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)
}