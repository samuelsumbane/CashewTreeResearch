package com.samuelsumbane.cashewtreedata.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samuelsumbane.cashewtreedata.FormerRepository
import com.samuelsumbane.cashewtreedata.domain.model.Former
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.math.exp

class FormerViewModel(
    private val repo: FormerRepository
) : ViewModel() {
    val formersList = repo.formers.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun addFormer(
        name: String,
        birthDay: Long,
        experienceYear: Int,
        genere: String
    ) {
       viewModelScope.launch {
           repo.addFormer(
               Former(
                   name = name,
                   birthDay = birthDay,
                   experienceYear = experienceYear,
                   genere = genere
               )
           )
       }
    }


}