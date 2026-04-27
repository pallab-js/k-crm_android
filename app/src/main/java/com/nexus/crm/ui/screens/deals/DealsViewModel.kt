package com.nexus.crm.ui.screens.deals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexus.crm.data.model.Deal
import com.nexus.crm.data.model.DealStage
import com.nexus.crm.data.repository.DealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

enum class DealFilter {
    ALL, ACTIVE, CLOSED_WON, CLOSED_LOST
}

data class DealsUiState(
    val deals: List<Deal> = emptyList(),
    val selectedStage: DealStage = DealStage.APPOINTMENT,
    val stages: List<DealStage> = DealStage.entries.filter { it != DealStage.CLOSED_LOST },
    val totalValue: Double = 0.0,
    val showAddDialog: Boolean = false
)

@HiltViewModel
class DealsViewModel @Inject constructor(
    private val dealRepository: DealRepository
) : ViewModel() {

    private val _selectedStage = MutableStateFlow(DealStage.APPOINTMENT)

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<DealsUiState> = _selectedStage.flatMapLatest { stage ->
        combine(
            dealRepository.getDealsByStage(stage),
            dealRepository.getTotalValueByStage(stage)
        ) { deals: List<Deal>, totalValue: Double? ->
            DealsUiState(
                deals = deals,
                selectedStage = stage,
                totalValue = totalValue ?: 0.0
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DealsUiState()
    )

    fun onStageSelected(stage: DealStage) {
        _selectedStage.value = stage
    }
}