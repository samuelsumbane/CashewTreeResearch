package com.samuelsumbane.cashewtreedata.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samuelsumbane.cashewtreedata.FormerRepository
import com.samuelsumbane.cashewtreedata.domain.model.FinalFormer
import com.samuelsumbane.cashewtreedata.domain.model.Former
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
import kotlin.math.exp

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

    @RequiresApi(Build.VERSION_CODES.O)
    fun Long?.calculateAge(): Int {
        return this?.let {
            val birthDate = Instant.ofEpochMilli(it)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()

           Period.between(birthDate, LocalDate.now()).years
        } ?: run { 0 }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun generateFinalFormersList(): List<FinalFormer> {
        return buildList {
            formersList.value.forEach {
                add(FinalFormer(it.name, age = it.birthDay.calculateAge(), it.experienceYear, it.genere))
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
}