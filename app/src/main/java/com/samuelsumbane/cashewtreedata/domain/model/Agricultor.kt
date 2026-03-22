package com.samuelsumbane.cashewtreedata.domain.model

data class Agricultor(
    val name: String,
    val age: Int,
    val experienceYear: Int,
    val genere: Genere
)

enum class Genere { Male, Female }