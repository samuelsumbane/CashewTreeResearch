package com.samuelsumbane.cashewtreedata.data.repository

import com.samuelsumbane.cashewtreedata.domain.dbModel.ResearcDao
import com.samuelsumbane.cashewtreedata.domain.model.Research

class ResearchRepository(private val dao: ResearcDao) {
    val researchsWithFormers = dao.getAll()

    suspend fun addResearch(research: Research) {
        dao.insertResearch(research)
    }
}