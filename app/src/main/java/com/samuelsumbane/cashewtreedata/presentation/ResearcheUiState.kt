package com.samuelsumbane.cashewtreedata.presentation

import com.samuelsumbane.cashewtreedata.domain.model.FungicidaUnity

data class ResearchUiState(
    val errors: Map<InputName, String> = emptyMap(),
    val showAgeIntervals: Boolean = false,
    val cashewTreeAge: String = "",
    val usedFugicidaPerYear: Double = 0.0,
    val fungicidaUnity: String = FungicidaUnity.Litre.unity,
    val showFungicidaUnityDropDown: Boolean = false,

    )