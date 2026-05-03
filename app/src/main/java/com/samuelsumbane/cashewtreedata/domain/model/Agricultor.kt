package com.samuelsumbane.cashewtreedata.domain.model

data class Agricultor(
    val id: Int,
    val name: String,
    val birthDay: Long,
    val experienceYear: Int,
    val genere: String
)

data class FinalFormer(
    val name: String,
    val age: Int,
    val birthDay: Long,
    val schoolarity: String,
    val experienceYear: String,
    val genere: String,
    val productionArea: String,
    val location: String
)

enum class Genere(val genereName: String) {
    Male("Masculino"),
    Female("Femenino"),
}