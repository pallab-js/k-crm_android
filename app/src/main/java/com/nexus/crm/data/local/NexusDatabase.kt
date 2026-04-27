package com.nexus.crm.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nexus.crm.data.model.Contact
import com.nexus.crm.data.model.Deal
import com.nexus.crm.data.model.Task

@Database(
    entities = [Contact::class, Deal::class, Task::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class NexusDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
    abstract fun dealDao(): DealDao
    abstract fun taskDao(): TaskDao
}