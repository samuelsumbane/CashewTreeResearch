package com.samuelsumbane.cashewtreedata.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samuelsumbane.cashewtreedata.data.repository.ResearchRepository
import com.samuelsumbane.cashewtreedata.domain.model.FinalResearch
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

    val researchs = repo.researchsWithFormers.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun addResearch(
        farmerId: Int,
        fugicidaName: String,
        usedFugicidaPerYear: Double,
        fungicidaUnity: String,
        puliverizationMonth: String,
        productionYear: String,
        cashewTreeAge: String,
        productionQuality: String,
        producedQuantity: Double,
        pricePerKG: Double,
        wasPulverized: Boolean,
        deases: String
    ) {
        viewModelScope.launch {
            val newResearch = Research(
                farmerId = farmerId,
                productionYear = productionYear,
                fugicidaName = fugicidaName,
                usedFugicidaPerYear = usedFugicidaPerYear,
                fungicidaUnity = fungicidaUnity,
                wasPulverized = wasPulverized,
                puliverizationMonth = puliverizationMonth,
                cashewTreeAge = cashewTreeAge,
                productionQuality = productionQuality,
                producedQuantity = producedQuantity,
                pricePerKG = pricePerKG,
                deases = deases
            )
            repo.addResearch(newResearch)
        }
    }

    fun exportResearchData(): List<FinalResearch> {
        return buildList {
            researchs.value.forEach {
                add(
                    FinalResearch(
                    it.farmer.name,
                    it.farmer.location,
                    it.farmer.genere,
                    it.farmer.experienceYear,
                    it.research.productionYear,
                    it.research.fugicidaName,
                        it.research.usedFugicidaPerYear,
                        it.research.fungicidaUnity,
                    it.research.wasPulverized,
                    it.research.puliverizationMonth,
                    it.research.cashewTreeAge,
                    it.research.productionQuality,
                    it.research.producedQuantity,
                    it.research.pricePerKG,
                    it.research.deases,
                    )
                )
            }
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

    fun fillResearchFoarm(
        showAgeIntervalDropMenu: Boolean? = null,
        cashewTreeAge: String? = null,
        usedFugicidaPerYear: Double? = null,
        fungicidaUnity: String? = null,
        showFungicidaUnityDropDown: Boolean? = null,
    ) {
        showAgeIntervalDropMenu?.let { _state.update { it.copy(showAgeIntervals = showAgeIntervalDropMenu) } }
        cashewTreeAge?.let { _state.update { it.copy(cashewTreeAge = cashewTreeAge) } }
        usedFugicidaPerYear?.let { _state.update { it.copy(usedFugicidaPerYear = usedFugicidaPerYear) } }
        fungicidaUnity?.let { unity -> _state.update { it.copy(fungicidaUnity = unity) } }
        showFungicidaUnityDropDown?.let { newValue -> _state.update { it.copy(showFungicidaUnityDropDown = newValue)}}
    }
}