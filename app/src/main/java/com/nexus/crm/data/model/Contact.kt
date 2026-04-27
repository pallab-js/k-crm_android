package com.nexus.crm.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class ContactStatus {
    LEAD,
    CUSTOMER,
    OPPORTUNITY
}

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val email: String = "",
    val phone: String = "",
    val company: String = "",
    val status: ContactStatus = ContactStatus.LEAD,
    val industry: String = "",
    val notes: String = "",
    val createdAt: Long = System.currentTimeMillis()
)