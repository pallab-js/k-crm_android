package com.nexus.crm.data.local

import androidx.room.*
import com.nexus.crm.data.model.Contact
import com.nexus.crm.data.model.ContactStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts ORDER BY name ASC")
    fun getAllContacts(): Flow<List<Contact>>

    @Query("SELECT * FROM contacts WHERE status = :status ORDER BY name ASC")
    fun getContactsByStatus(status: ContactStatus): Flow<List<Contact>>

    @Query("SELECT * FROM contacts WHERE name LIKE '%' || :query || '%' OR company LIKE '%' || :query || '%' ORDER BY name ASC")
    fun searchContacts(query: String): Flow<List<Contact>>

    @Query("SELECT * FROM contacts WHERE id = :id")
    fun getContactById(id: Long): Flow<Contact?>

    @Query("SELECT COUNT(*) FROM contacts")
    fun getContactCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM contacts")
    suspend fun getContactCountSync(): Int

    @Query("SELECT COUNT(*) FROM contacts WHERE status = :status")
    fun getContactCountByStatus(status: ContactStatus): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: Contact): Long

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)
}