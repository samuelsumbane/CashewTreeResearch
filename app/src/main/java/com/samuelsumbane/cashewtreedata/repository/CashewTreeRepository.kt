package com.samuelsumbane.cashewtreedata.repository

import com.samuelsumbane.cashewtreedata.domain.model.ProductionQuality
import com.samuelsumbane.cashewtreedata.domain.model.Research
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object CashewTreeRepository {

    val cashewData = mutableListOf(
        Research(
            personalData = 1,
            location = "Chongoene",
            fugicidaName = "Fugicida",
            puliverizationMonth = "Junho",
            productionYear = "2020",
            cashewTreeAge = 2,
            productionQuality = ProductionQuality.Low,
            producedQuantity = 12.0,
            pricePerKG = 100.0,
            wasPulverized = true,
            deases = "no"
        ),
        Research(
            personalData = 2,
            location = "Manjacaze",
            fugicidaName = "",
            puliverizationMonth = "Julho",
            productionYear = "2021",
            cashewTreeAge = 3,
            productionQuality = ProductionQuality.Medium,
            producedQuantity = 12.0,
            pricePerKG = 100.0,
            wasPulverized = true,
            deases = ""
        ),
        Research(
            personalData = 3,
            location = "Chongoene",
            fugicidaName = "Cajocida",
            puliverizationMonth = "Julho",
            productionYear = "2022",
            cashewTreeAge = 2,
            productionQuality = ProductionQuality.Low,
            producedQuantity = 12.0,
            pricePerKG = 100.0,
            wasPulverized = true,
            deases = ""
        ),
    )

//    fun addCashew(researchData: Research) {
    fun addCashew() {
        val n = Research(
            personalData = 4,
            location = "Novo dado",
            fugicidaName = "Cajocida",
            puliverizationMonth = "Julho",
            productionYear = "2022",
            cashewTreeAge = 2,
            productionQuality = ProductionQuality.Low,
            producedQuantity = 12.0,
            pricePerKG = 100.0,
            wasPulverized = true,
            deases = ""
        )
        cashewData.add(n)
    }

    fun fillForm(
        personalData: Int? = null,
        location: String? = null,
        fugicidaName: String? = null,
        pulverizationMonth: String? = null,
        productionYear: String? = null,
        cashewTreeAge: Int? = null,
        producedQuality: ProductionQuality? = null,
        producedQuantity: Double? = null,
        pricePerKg: Double? = null,
        wasPulverized: Boolean? = null,
        deases: String? = null
    ) {
//        personalData?.let { newValue -> _researchUiState.update { it.copy(personalData = newValue) } }
//        location?.let { newValue -> _researchUiState.update { it.copy(location = newValue) } }



//        fugicidaName?.let { newValue -> _researchUiState.update { it.copy(fugicidaName = newValue) } }
//        pulverizationMonth?.let { newValue -> _researchUiState.update { it.copy(puliverizationMonth = newValue) } }
//        productionYear?.let { newValue -> _researchUiState.update { it.copy(productionYear = newValue) } }
//        cashewTreeAge?.let { newValue -> _researchUiState.update { it.copy(cashewTreeAge = newValue) } }
//        producedQuality?.let { newValue -> _researchUiState.update { it.copy(productionQuality = newValue) } }
//        producedQuantity?.let { newValue -> _researchUiState.update { it.copy(producedQuantity = newValue) } }
//        pricePerKg?.let { newValue -> _researchUiState.update { it.copy(pricePerKG = newValue) } }
//        wasPulverized?.let { newValue -> _researchUiState.update { it.copy(wasPulverized = newValue) } }
//        deases?.let { newValue -> _researchUiState.update { it.copy(deases = newValue) } }
    }
}