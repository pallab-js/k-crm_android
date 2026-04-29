package com.nexus.crm.ui.screens.tasks

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.nexus.crm.data.local.NexusDatabase
import com.nexus.crm.data.model.Task
import com.nexus.crm.data.model.TaskPriority
import com.nexus.crm.data.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
class TasksViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    
    private lateinit var db: NexusDatabase
    private lateinit var repository: TaskRepository
    private lateinit var viewModel: TasksViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, NexusDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        repository = TaskRepository(db.taskDao())
        viewModel = TasksViewModel(repository)
    }

    @After
    fun tearDown() {
        db.close()
        Dispatchers.resetMain()
    }

    @Test
    fun `tasks are loaded from repository`() = runTest {
        repository.insertTask(Task(id = 1, title = "Task 1", description = "", dueDate = 0, priority = TaskPriority.MEDIUM, isCompleted = false, contactId = 1))
        
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.uiState.test {
            val state = awaitItem()
            assertEquals(1, state.tasks.size)
            assertEquals("Task 1", state.tasks[0].title)
        }
    }
}
