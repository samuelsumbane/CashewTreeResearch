package com.samuelsumbane.cashewtreedata.domain.model

data class FinalResearch(
    val name: String,
    val genere: String,
    val experienceYear: Int,
    val location: String,
    val fugicidaName: String,
    val puliverizationMonth: String,
    val productionYear: String,
    val cashewTreeAge: Int,
    val productionQuality: String,
    val producedQuantity: Double,
    val pricePerKG: Double,
    val wasPulverized: Boolean,
    val deases: String
)



enum class ProductionQuality(val stringValue: String) {
    High("Alto"),
    Medium("Médio"),
    Low("Baixo")
}