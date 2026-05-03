package com.samuelsumbane.cashewtreedata.presentation

import com.samuelsumbane.cashewtreedata.domain.model.Agricultor

data class FormerUiState(
    val formersList: List<Agricultor> = emptyList(),
    val fieldsErrors: Map<InputName, String> = emptyMap(),
    val location: String = "",
    val productionArea: String = "",
    val schoolarity: String = "",
)
