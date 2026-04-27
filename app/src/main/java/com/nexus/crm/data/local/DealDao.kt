package com.nexus.crm.data.local

import androidx.room.*
import com.nexus.crm.data.model.Deal
import com.nexus.crm.data.model.DealStage
import kotlinx.coroutines.flow.Flow

@Dao
interface DealDao {
    @Query("SELECT * FROM deals ORDER BY updatedAt DESC")
    fun getAllDeals(): Flow<List<Deal>>

    @Query("SELECT * FROM deals WHERE stage = :stage ORDER BY updatedAt DESC")
    fun getDealsByStage(stage: DealStage): Flow<List<Deal>>

    @Query("SELECT * FROM deals WHERE stage IN (:stages) ORDER BY updatedAt DESC")
    fun getDealsByStages(stages: List<DealStage>): Flow<List<Deal>>

    @Query("SELECT * FROM deals WHERE id = :id")
    fun getDealById(id: Long): Flow<Deal?>

    @Query("SELECT SUM(value) FROM deals WHERE stage = :stage")
    fun getTotalValueByStage(stage: DealStage): Flow<Double?>

    @Query("SELECT SUM(value) FROM deals WHERE stage NOT IN ('CLOSED_LOST')")
    fun getTotalRevenue(): Flow<Double?>

    @Query("SELECT COUNT(*) FROM deals WHERE stage NOT IN ('CLOSED_LOST', 'CLOSED_WON')")
    fun getActiveDealsCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM deals WHERE stage = 'CLOSED_WON'")
    fun getWonDealsCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM deals WHERE stage = 'CLOSED_LOST'")
    fun getLostDealsCount(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeal(deal: Deal): Long

    @Update
    suspend fun updateDeal(deal: Deal)

    @Delete
    suspend fun deleteDeal(deal: Deal)
}