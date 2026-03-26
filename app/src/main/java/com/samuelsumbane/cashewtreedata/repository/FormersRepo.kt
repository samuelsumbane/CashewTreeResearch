package com.samuelsumbane.cashewtreedata.repository

import com.samuelsumbane.cashewtreedata.domain.model.Agricultor
import com.samuelsumbane.cashewtreedata.domain.model.Genere
import kotlinx.coroutines.coroutineScope

object FormersRepo {

    val formers = mutableListOf(
        Agricultor(1, name = "man1", birthDay = 25, experienceYear = 2, genere = Genere.Male.genereName),
        Agricultor(4, name = "girl2", birthDay = 22, experienceYear = 4, genere = Genere.Female.genereName)
    )

    suspend fun addFormer(former: Agricultor) {
        coroutineScope {
            val newFormer = former.copy(id = formers.last().id + 1)
            formers.add(newFormer)
        }
    }
}