package com.nexus.crm.data.repository

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.nexus.crm.data.local.TaskDao
import com.nexus.crm.data.local.NexusDatabase
import com.nexus.crm.data.model.Task
import com.nexus.crm.data.model.TaskPriority
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
class TaskRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: NexusDatabase
    private lateinit var taskDao: TaskDao
    private lateinit var repository: TaskRepository

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, NexusDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        taskDao = db.taskDao()
        repository = TaskRepository(taskDao)
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun `insert and get all tasks`() = runTest {
        val task = Task(
            id = 1L,
            title = "Follow up",
            description = "Call John",
            dueDate = System.currentTimeMillis(),
            priority = TaskPriority.HIGH,
            isCompleted = false,
            contactId = 1L
        )
        
        repository.insertTask(task)
        
        val tasks = repository.getAllTasks().first()
        assertEquals(1, tasks.size)
        assertEquals("Follow up", tasks[0].title)
    }
}
