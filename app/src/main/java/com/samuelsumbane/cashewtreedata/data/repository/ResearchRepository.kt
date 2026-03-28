package com.samuelsumbane.cashewtreedata.data.repository

import com.samuelsumbane.cashewtreedata.domain.model.ResearcDao
import com.samuelsumbane.cashewtreedata.domain.model.Research

class ResearchRepository(private val dao: ResearcDao) {
    val researchs = dao.getAll()

    suspend fun addResearch(research: Research) {
        dao.insertResearch(research)
    }
}