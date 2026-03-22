package com.samuelsumbane.cashewtreedata.repository

import com.samuelsumbane.cashewtreedata.domain.model.Agricultor
import com.samuelsumbane.cashewtreedata.domain.model.Genere

object FormersRepo {

    val formers = listOf(
        Agricultor(name = "man1", age = 25, experienceYear = 2, genere = Genere.Male),
        Agricultor(name = "man2", age = 34, experienceYear = 7, genere = Genere.Male),
        Agricultor(name = "girl1", age = 27, experienceYear = 3, genere = Genere.Female),
        Agricultor(name = "girl2", age = 22, experienceYear = 4, genere = Genere.Female)
    )
}