package com.samuelsumbane.cashewtreedata

import com.samuelsumbane.cashewtreedata.domain.model.Farmer
import com.samuelsumbane.cashewtreedata.domain.dbModel.FormerDao

class FormerRepository(private val formerDao: FormerDao) {

    val formers = formerDao.getAllFormers()

    suspend fun addFormer(farmer: Farmer) {
        formerDao.insertFormer(farmer)
    }
}