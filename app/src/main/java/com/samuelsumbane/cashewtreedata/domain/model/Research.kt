package com.samuelsumbane.cashewtreedata.domain.model

data class FinalResearch(
    val name: String,
    val location: String,
    val genere: String,
    val experienceYear: String,
    val productionYear: String,
    val fugicidaName: String,
    val usedFugicidaPerYear: Double?,
    val fungicidaUnity: String,
    val wasPulverized: Boolean,
    val puliverizationMonth: String,
    val cashewTreeAge: String,
    val productionQuality: String,
    val producedQuantity: Double,
    val pricePerKG: Double,
    val deases: String
)



enum class ProductionQuality(val stringValue: String) {
    High("Alta"),
    Medium("Média"),
    Low("Baixa")
}