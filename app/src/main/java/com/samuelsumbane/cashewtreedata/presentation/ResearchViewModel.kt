package com.samuelsumbane.cashewtreedata.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samuelsumbane.cashewtreedata.data.repository.ResearchRepository
import com.samuelsumbane.cashewtreedata.domain.model.Research
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ResearchViewModel(
    private val repo: ResearchRepository
) : ViewModel() {

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
}