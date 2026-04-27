package com.nexus.crm.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class Priority {
    HIGH,
    MEDIUM,
    LOW
}

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String = "",
    val dueDate: Long = System.currentTimeMillis(),
    val priority: Priority = Priority.MEDIUM,
    val isCompleted: Boolean = false,
    val contactId: Long? = null,
    val dealId: Long? = null
)