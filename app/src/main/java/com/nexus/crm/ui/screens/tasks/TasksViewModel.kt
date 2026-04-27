package com.nexus.crm.ui.screens.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexus.crm.data.model.Task
import com.nexus.crm.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class TaskFilter {
    ALL, TODAY, OVERDUE
}

data class TasksUiState(
    val tasks: List<Task> = emptyList(),
    val selectedFilter: TaskFilter = TaskFilter.ALL,
    val pendingCount: Int = 0
)

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val _selectedFilter = MutableStateFlow(TaskFilter.ALL)

    val uiState: StateFlow<TasksUiState> = _selectedFilter.flatMapLatest { filter ->
        val tasksFlow = when (filter) {
            TaskFilter.ALL -> taskRepository.getAllTasks()
            TaskFilter.TODAY -> taskRepository.getTasksForToday()
            TaskFilter.OVERDUE -> taskRepository.getOverdueTasks()
        }

        combine(
            tasksFlow,
            taskRepository.getPendingTaskCount()
        ) { tasks, pendingCount ->
            TasksUiState(
                tasks = tasks,
                selectedFilter = filter,
                pendingCount = pendingCount
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = TasksUiState()
    )

    fun toggleTaskComplete(task: Task) {
        viewModelScope.launch {
            taskRepository.updateTask(task.copy(isCompleted = !task.isCompleted))
        }
    }

    fun onFilterChange(filter: TaskFilter) {
        _selectedFilter.value = filter
    }
}