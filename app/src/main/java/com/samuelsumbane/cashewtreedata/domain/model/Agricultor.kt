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
    val experienceYear: String,
    val genere: String,
    val productionArea: Double,
    val location: String
)

enum class Genere(val genereName: String) {
    Male("Masculino"),
    Female("Femenino"),
    Other("Outro")
}