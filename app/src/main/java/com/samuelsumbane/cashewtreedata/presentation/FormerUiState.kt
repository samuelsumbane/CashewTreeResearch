package com.samuelsumbane.cashewtreedata.presentation

import com.samuelsumbane.cashewtreedata.domain.model.Research

data class FormerUiState(
    val formersList: List<Research> = emptyList(),
    val fieldsErrors: Map<InputName, String> = emptyMap()
)
