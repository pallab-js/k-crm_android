package com.nexus.crm.data.repository

import com.nexus.crm.data.local.DealDao
import com.nexus.crm.data.model.Deal
import com.nexus.crm.data.model.DealStage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DealRepository @Inject constructor(
    private val dealDao: DealDao
) {
    fun getAllDeals(): Flow<List<Deal>> = dealDao.getAllDeals()

    fun getDealsByStage(stage: DealStage): Flow<List<Deal>> = dealDao.getDealsByStage(stage)

    fun getDealsByStages(stages: List<DealStage>): Flow<List<Deal>> = dealDao.getDealsByStages(stages)

    fun getDealById(id: Long): Flow<Deal?> = dealDao.getDealById(id)

    fun getTotalValueByStage(stage: DealStage): Flow<Double?> = dealDao.getTotalValueByStage(stage)

    fun getTotalRevenue(): Flow<Double?> = dealDao.getTotalRevenue()

    fun getActiveDealsCount(): Flow<Int> = dealDao.getActiveDealsCount()

    fun getWonDealsCount(): Flow<Int> = dealDao.getWonDealsCount()

    fun getLostDealsCount(): Flow<Int> = dealDao.getLostDealsCount()

    suspend fun insertDeal(deal: Deal): Long = dealDao.insertDeal(deal)

    suspend fun updateDeal(deal: Deal) = dealDao.updateDeal(deal)

    suspend fun deleteDeal(deal: Deal) = dealDao.deleteDeal(deal)
}