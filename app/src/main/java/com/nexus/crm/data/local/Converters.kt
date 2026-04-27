package com.nexus.crm.data.local

import androidx.room.TypeConverter
import com.nexus.crm.data.model.ContactStatus
import com.nexus.crm.data.model.DealStage
import com.nexus.crm.data.model.Priority

class Converters {
    @TypeConverter
    fun fromContactStatus(status: ContactStatus): String = status.name

    @TypeConverter
    fun toContactStatus(value: String): ContactStatus = ContactStatus.valueOf(value)

    @TypeConverter
    fun fromDealStage(stage: DealStage): String = stage.name

    @TypeConverter
    fun toDealStage(value: String): DealStage = DealStage.valueOf(value)

    @TypeConverter
    fun fromPriority(priority: Priority): String = priority.name

    @TypeConverter
    fun toPriority(value: String): Priority = Priority.valueOf(value)
}