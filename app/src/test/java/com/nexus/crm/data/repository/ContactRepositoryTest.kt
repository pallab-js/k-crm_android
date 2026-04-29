package com.nexus.crm.data.repository

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.nexus.crm.data.local.ContactDao
import com.nexus.crm.data.local.NexusDatabase
import com.nexus.crm.data.model.Contact
import com.nexus.crm.data.model.ContactStatus
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
class ContactRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: NexusDatabase
    private lateinit var contactDao: ContactDao
    private lateinit var repository: ContactRepository

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, NexusDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        contactDao = db.contactDao()
        repository = ContactRepository(contactDao)
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun `insert and get all contacts`() = runTest {
        val contact = Contact(
            id = 1L,
            name = "Test User",
            email = "test@nexus.com",
            phone = "123",
            company = "Nexus",
            jobTitle = "Tester",
            status = ContactStatus.LEAD
        )
        
        repository.insertContact(contact)
        
        val contacts = repository.getAllContacts().first()
        assertEquals(1, contacts.size)
        assertEquals("Test User", contacts[0].name)
    }

    @Test
    fun `search contacts returns filtered list`() = runTest {
        repository.insertContact(Contact(id = 1, name = "Apple", email = "", phone = "", company = "", jobTitle = "", status = ContactStatus.LEAD))
        repository.insertContact(Contact(id = 2, name = "Banana", email = "", phone = "", company = "", jobTitle = "", status = ContactStatus.LEAD))
        
        val results = repository.searchContacts("App").first()
        assertEquals(1, results.size)
        assertEquals("Apple", results[0].name)
    }
}
