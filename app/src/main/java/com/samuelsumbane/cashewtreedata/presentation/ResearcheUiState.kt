package com.samuelsumbane.cashewtreedata.presentation

data class ResearchUiState(
    val errors: Map<InputName, String> = emptyMap(),
    val showAgeIntervals: Boolean = false,
    val cashewTreeAge: String = "",
    val usedFugicidaPerYear: Double? = null,

)