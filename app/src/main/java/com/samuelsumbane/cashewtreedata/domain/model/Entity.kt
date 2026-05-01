package com.samuelsumbane.cashewtreedata.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "research")
data class Research(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val farmerId: Int = 0,

    val productionYear: String,
//    val useFugicida: Boolean,
    val wasPulverized: Boolean,
    val fugicidaName: String,
    val usedFugicidaPerYear: Double?,
    val puliverizationMonth: String,
    val cashewTreeAge: String,
    val productionQuality: String,
    val producedQuantity: Double,
    val pricePerKG: Double,
    val deases: String
)

@Entity(tableName = "farmer")
data class Farmer(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,
    val location: String,
    val birthDay: Long,
    val productionArea: Double,
    val experienceYear: String,
    val genere: String
)

data class ResearchWithFarmer(
    @Embedded val research: Research,

    @Relation(
        parentColumn = "farmerId",
        entityColumn = "id"
    )

    val farmer: Farmer
)