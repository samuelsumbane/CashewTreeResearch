package com.samuelsumbane.cashewtreedata

import com.samuelsumbane.cashewtreedata.domain.model.Former
import com.samuelsumbane.cashewtreedata.domain.model.FormerDao

class FormerRepository(private val formerDao: FormerDao) {

    val formers = formerDao.getAllFormers()

    suspend fun addFormer(former: Former) {
        formerDao.insertFormer(former)
    }
}