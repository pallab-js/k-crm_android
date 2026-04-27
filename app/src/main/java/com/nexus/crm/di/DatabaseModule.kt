package com.nexus.crm.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.nexus.crm.data.local.ContactDao
import com.nexus.crm.data.local.DealDao
import com.nexus.crm.data.local.NexusDatabase
import com.nexus.crm.data.local.TaskDao
import com.nexus.crm.data.model.Contact
import com.nexus.crm.data.model.ContactStatus
import com.nexus.crm.data.model.Deal
import com.nexus.crm.data.model.DealStage
import com.nexus.crm.data.model.Priority
import com.nexus.crm.data.model.Task
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideNexusDatabase(
        @ApplicationContext context: Context
    ): NexusDatabase {
        return Room.databaseBuilder(
            context,
            NexusDatabase::class.java,
            "nexus_crm_database"
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    seedData(context)
                }
            })
            .build()
    }

    private fun seedData(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val db = Room.databaseBuilder(context, NexusDatabase::class.java, "nexus_crm_database").build()
            db.contactDao().insertContact(Contact(name = "Sarah Chen", email = "sarah@techcorp.io", company = "TechCorp", status = ContactStatus.CUSTOMER, phone = "+1 555-0101"))
            db.contactDao().insertContact(Contact(name = "Marcus Johnson", email = "marcus@innovate.co", company = "Innovate Co", status = ContactStatus.LEAD, phone = "+1 555-0102"))
            db.contactDao().insertContact(Contact(name = "Emily Rodriguez", email = "emily@startup.io", company = "Startup Inc", status = ContactStatus.OPPORTUNITY, phone = "+1 555-0103"))
            db.contactDao().insertContact(Contact(name = "David Kim", email = "david@enterprise.com", company = "Enterprise Ltd", status = ContactStatus.CUSTOMER, phone = "+1 555-0104"))
            db.dealDao().insertDeal(Deal(title = "TechCorp Enterprise", value = 50000.0, stage = DealStage.CLOSED_WON, contactName = "Sarah Chen"))
            db.dealDao().insertDeal(Deal(title = "Innovate SaaS", value = 15000.0, stage = DealStage.PROPOSAL, contactName = "Marcus Johnson"))
            db.dealDao().insertDeal(Deal(title = "Startup Platform", value = 25000.0, stage = DealStage.PRESENTATION, contactName = "Emily Rodriguez"))
            db.dealDao().insertDeal(Deal(title = "Enterprise Deal", value = 100000.0, stage = DealStage.NEGOTIATION, contactName = "David Kim"))
            val today = System.currentTimeMillis()
            val day = 24 * 60 * 60 * 1000L
            db.taskDao().insertTask(Task(title = "Call Sarah", dueDate = today, priority = Priority.HIGH, isCompleted = false))
            db.taskDao().insertTask(Task(title = "Prepare proposal", dueDate = today, priority = Priority.MEDIUM, isCompleted = false))
            db.taskDao().insertTask(Task(title = "Follow up with Emily", dueDate = today + day, priority = Priority.LOW, isCompleted = false))
            db.taskDao().insertTask(Task(title = "Review contract", dueDate = today + day, priority = Priority.HIGH, isCompleted = false))
        }
    }

    @Provides
    @Singleton
    fun provideContactDao(database: NexusDatabase): ContactDao = database.contactDao()

    @Provides
    @Singleton
    fun provideDealDao(database: NexusDatabase): DealDao = database.dealDao()

    @Provides
    @Singleton
    fun provideTaskDao(database: NexusDatabase): TaskDao = database.taskDao()
}