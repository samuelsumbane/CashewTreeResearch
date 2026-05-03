package com.samuelsumbane.cashewtreedata.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samuelsumbane.cashewtreedata.FormerRepository
import com.samuelsumbane.cashewtreedata.domain.model.FinalFormer
import com.samuelsumbane.cashewtreedata.domain.model.Farmer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId

class  FormerViewModel(
    private val repo: FormerRepository
) : ViewModel() {

    val _state = MutableStateFlow(FormerUiState())

    val formerState = _state.asStateFlow()

    val formersList = repo.formers.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )


    fun addFormer(
        name: String,
        location: String,
        birthDay: Long,
        schoolarity: String,
        productionArea: String,
        experienceYear: String,
        genere: String
    ) {
       viewModelScope.launch {
           repo.addFormer(
               Farmer(
                   name = name,
                   location = location,
                   birthDay = birthDay,
                   schoolarity = schoolarity,
                   productionArea = productionArea,
                   experienceYear = experienceYear,
                   genere = genere
               )
           )
       }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun calculateAge(birthday: Long?): Int {
        return birthday?.let {
            val birthDate = Instant.ofEpochMilli(it)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()

           Period.between(birthDate, LocalDate.now()).years
        } ?: run { 0 }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun generateFinalFormersList(givenList: List<Farmer>): List<FinalFormer> {
        return buildList {
            givenList.forEach {
                add(FinalFormer(it.name, age = calculateAge(it.birthDay), it.birthDay, it.schoolarity, it.experienceYear, it.genere, it.productionArea, it.location))
            }
        }
    }

    fun setFieldError(field: InputName, error: String) {
        _state.update {
            it.copy(fieldsErrors = it.fieldsErrors.toMutableMap().apply { put(field, error) })
        }
    }

    fun removeFieldError(field: InputName) {
        _state.update {
            it.copy(fieldsErrors = it.fieldsErrors.toMutableMap().apply { remove(field) })
        }
    }

    fun fillForm(
        location: String? = null,
        productionArea: String? = null,
        schoolarity: String? = null
    ) {
        location?.let { farmerLocation -> _state.update { it.copy(location = farmerLocation) } }
        productionArea?.let { pArea -> _state.update { it.copy(productionArea = pArea) } }
        schoolarity?.let { school -> _state.update { it.copy(schoolarity = school) }}
    }
}