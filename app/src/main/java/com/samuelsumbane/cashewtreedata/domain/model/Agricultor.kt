package com.samuelsumbane.cashewtreedata.domain.model

data class Agricultor(
    val id: Int,
    val name: String,
    val birthDay: Long,
    val experienceYear: Int,
    val genere: String
)

enum class Genere(val genereName: String) {
    Male("Masculino"),
    Female("Femenino"),
    Other("Outro")
}