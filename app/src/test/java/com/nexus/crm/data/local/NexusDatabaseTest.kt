package com.nexus.crm.data.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.nexus.crm.data.model.Contact
import com.nexus.crm.data.model.ContactStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
class NexusDatabaseTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var contactDao: ContactDao
    private lateinit var db: NexusDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, NexusDatabase::class.java
        ).allowMainThreadQueries().build()
        contactDao = db.contactDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeContactAndReadInList() = runTest {
        val contact = Contact(
            id = 1L,
            name = "John Doe",
            email = "john@example.com",
            phone = "1234567890",
            company = "Acme Corp",
            jobTitle = "Developer",
            status = ContactStatus.LEAD
        )
        contactDao.insertContact(contact)
        val allContacts = contactDao.getAllContacts().first()
        assertEquals(allContacts[0].name, "John Doe")
        assertEquals(allContacts.size, 1)
    }

    @Test
    fun deleteContact() = runTest {
        val contact = Contact(
            id = 2L,
            name = "Jane Doe",
            email = "jane@example.com",
            phone = "0987654321",
            company = "Globex",
            jobTitle = "Manager",
            status = ContactStatus.CUSTOMER
        )
        contactDao.insertContact(contact)
        var count = contactDao.getContactCountSync()
        assertEquals(1, count)

        contactDao.deleteContact(contact)
        count = contactDao.getContactCountSync()
        assertEquals(0, count)
    }

    @Test
    fun searchContacts() = runTest {
        val contact1 = Contact(id = 1, name = "Alice Smith", email = "alice@test.com", phone = "1", company = "Tech", jobTitle = "Dev", status = ContactStatus.LEAD)
        val contact2 = Contact(id = 2, name = "Bob Jones", email = "bob@test.com", phone = "2", company = "Business", jobTitle = "Sales", status = ContactStatus.CUSTOMER)
        
        contactDao.insertContact(contact1)
        contactDao.insertContact(contact2)

        val results = contactDao.searchContacts("Smith").first()
        assertEquals(1, results.size)
        assertEquals("Alice Smith", results[0].name)

        val results2 = contactDao.searchContacts("Tech").first()
        assertEquals(1, results2.size)
        assertEquals("Alice Smith", results2[0].name)
    }
}
