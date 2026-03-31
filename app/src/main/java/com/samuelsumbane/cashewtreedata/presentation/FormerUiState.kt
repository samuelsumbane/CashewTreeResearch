package com.samuelsumbane.cashewtreedata.presentation

import com.samuelsumbane.cashewtreedata.domain.model.Agricultor
import com.samuelsumbane.cashewtreedata.domain.model.Research

data class FormerUiState(
    val formersList: List<Agricultor> = emptyList(),
    val fieldsErrors: Map<InputName, String> = emptyMap()
)
