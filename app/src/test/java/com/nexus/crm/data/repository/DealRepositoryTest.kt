package com.nexus.crm.data.repository

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.nexus.crm.data.local.DealDao
import com.nexus.crm.data.local.NexusDatabase
import com.nexus.crm.data.model.Deal
import com.nexus.crm.data.model.DealStatus
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
class DealRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: NexusDatabase
    private lateinit var dealDao: DealDao
    private lateinit var repository: DealRepository

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, NexusDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dealDao = db.dealDao()
        repository = DealRepository(dealDao)
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun `insert and get all deals`() = runTest {
        val deal = Deal(
            id = 1L,
            title = "Big Deal",
            value = 10000.0,
            status = DealStatus.NEGOTIATION,
            contactId = 1L,
            company = "Acme"
        )
        
        repository.insertDeal(deal)
        
        val deals = repository.getAllDeals().first()
        assertEquals(1, deals.size)
        assertEquals("Big Deal", deals[0].title)
    }
}
