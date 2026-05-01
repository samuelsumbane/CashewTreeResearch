package com.samuelsumbane.cashewtreedata.repository

import com.samuelsumbane.cashewtreedata.domain.model.ProductionQuality
import com.samuelsumbane.cashewtreedata.domain.model.Research

object CashewTreeRepository {

    val cashewData = mutableListOf<Research>()
//        Research(
//            formerId = 1,
//            location = "Chongoene",
//            fugicidaName = "Fugicida",
//            puliverizationMonth = "Junho",
//            productionYear = "2020",
//            cashewTreeAge = 2,
//            productionQuality = ProductionQuality.Low.stringValue,
//            producedQuantity = 12.0,
//            pricePerKG = 100.0,
//            wasPulverized = true,
//            deases = "no"
//        ),
//        Research(
//            formerId = 2,
//            location = "Manjacaze",
//            fugicidaName = "",
//            puliverizationMonth = "Julho",
//            productionYear = "2021",
//            cashewTreeAge = 3,
//            productionQuality = ProductionQuality.Medium.stringValue,
//            producedQuantity = 12.0,
//            pricePerKG = 100.0,
//            wasPulverized = true,
//            deases = ""
//        ),
//        Research(
//            formerId = 3,
//            location = "Chongoene",
//            fugicidaName = "Cajocida",
//            puliverizationMonth = "Julho",
//            productionYear = "2022",
//            cashewTreeAge = 2,
//            productionQuality = ProductionQuality.Low.stringValue,
//            producedQuantity = 12.0,
//            pricePerKG = 100.0,
//            wasPulverized = true,
//            deases = ""
//        ),

//    val cashewDataList = cashewData.

//    fun addCashew(researchData: Research) {
    fun addCashew(research: Research) {
//        val n = Research(
//            personalData = 4,
//            location = "Novo dado",
//            fugicidaName = "Cajocida",
//            puliverizationMonth = "Julho",
//            productionYear = "2022",
//            cashewTreeAge = 2,
//            productionQuality = ProductionQuality.Low,
//            producedQuantity = 12.0,
//            pricePerKG = 100.0,
//            wasPulverized = true,
//            deases = ""
//        )
        cashewData.add(research)
    }
}