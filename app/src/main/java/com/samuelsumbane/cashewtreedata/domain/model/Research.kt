package com.samuelsumbane.cashewtreedata.domain.model

data class Research(
    val personalData: Int, //formerId
    val location: String,
    val fugicidaName: String,
    val puliverizationMonth: String,
    val productionYear: String,
    val cashewTreeAge: Int,
    val productionQuality: ProductionQuality,
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