package com.nexus.crm.ui.screens.deals

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.nexus.crm.data.local.NexusDatabase
import com.nexus.crm.data.model.Deal
import com.nexus.crm.data.model.DealStatus
import com.nexus.crm.data.repository.DealRepository
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
class DealsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    
    private lateinit var db: NexusDatabase
    private lateinit var repository: DealRepository
    private lateinit var viewModel: DealsViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, NexusDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        repository = DealRepository(db.dealDao())
        viewModel = DealsViewModel(repository)
    }

    @After
    fun tearDown() {
        db.close()
        Dispatchers.resetMain()
    }

    @Test
    fun `deals are loaded from repository`() = runTest {
        repository.insertDeal(Deal(id = 1, title = "Deal 1", value = 100.0, status = DealStatus.LEAD, contactId = 1, company = ""))
        
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.uiState.test {
            val state = awaitItem()
            assertEquals(1, state.deals.size)
            assertEquals("Deal 1", state.deals[0].title)
        }
    }
}
