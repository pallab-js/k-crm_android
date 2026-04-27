package com.nexus.crm.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class DealStage {
    APPOINTMENT,
    PRESENTATION,
    PROPOSAL,
    NEGOTIATION,
    CLOSED_WON,
    CLOSED_LOST
}

@Entity(tableName = "deals")
data class Deal(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val value: Double = 0.0,
    val stage: DealStage = DealStage.APPOINTMENT,
    val contactId: Long = 0,
    val contactName: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)