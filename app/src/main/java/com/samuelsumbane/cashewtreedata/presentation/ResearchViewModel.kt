package com.samuelsumbane.cashewtreedata.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samuelsumbane.cashewtreedata.data.repository.ResearchRepository
import com.samuelsumbane.cashewtreedata.domain.model.Research
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ResearchViewModel(
    private val repo: ResearchRepository
) : ViewModel() {

    val _state = MutableStateFlow(ResearchUiState())
    val researchUiState = _state.asStateFlow()

    val researchs = repo.researchs.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun addResearch(
        formerId: Int,
        location: String,
        fugicidaName: String,
        puliverizationMonth: String,
        productionYear: String,
        cashewTreeAge: Int,
        productionQuality: String,
        producedQuantity: Double,
        pricePerKG: Double,
        wasPulverized: Boolean,
        deases: String
    ) {
        viewModelScope.launch {
            val newResearch = Research(
                formerId = formerId,
                location = location,
                fugicidaName = fugicidaName,
                puliverizationMonth = puliverizationMonth,
                productionYear = productionYear,
                cashewTreeAge = cashewTreeAge,
                productionQuality = productionQuality,
                producedQuantity = producedQuantity,
                pricePerKG = pricePerKG,
                wasPulverized = wasPulverized,
                deases = deases
            )
            repo.addResearch(newResearch)
        }
    }

    fun setError(inputName: InputName, error: String) {
        _state.update {
            it.copy(errors = it.errors.toMutableMap().apply { put(inputName, error)})
        }
    }

    fun removeError(inputName: InputName) {
        _state.update { it.copy(errors = it.errors.toMutableMap().apply { remove(inputName) }) }
    }
}