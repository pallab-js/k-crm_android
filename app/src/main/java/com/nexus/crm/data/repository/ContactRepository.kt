package com.nexus.crm.data.repository

import com.nexus.crm.data.local.ContactDao
import com.nexus.crm.data.model.Contact
import com.nexus.crm.data.model.ContactStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactRepository @Inject constructor(
    private val contactDao: ContactDao
) {
    fun getAllContacts(): Flow<List<Contact>> = contactDao.getAllContacts()

    fun getContactsByStatus(status: ContactStatus): Flow<List<Contact>> = contactDao.getContactsByStatus(status)

    fun searchContacts(query: String): Flow<List<Contact>> = contactDao.searchContacts(query)

    fun getContactById(id: Long): Flow<Contact?> = contactDao.getContactById(id)

    fun getContactCount(): Flow<Int> = contactDao.getContactCount()

    fun getContactCountByStatus(status: ContactStatus): Flow<Int> = contactDao.getContactCountByStatus(status)

    suspend fun insertContact(contact: Contact): Long = contactDao.insertContact(contact)

    suspend fun updateContact(contact: Contact) = contactDao.updateContact(contact)

    suspend fun deleteContact(contact: Contact) = contactDao.deleteContact(contact)
}