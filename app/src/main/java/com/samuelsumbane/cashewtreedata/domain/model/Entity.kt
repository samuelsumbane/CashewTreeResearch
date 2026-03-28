package com.samuelsumbane.cashewtreedata.domain.model

import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "research")
data class Research(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val formerId: Int = 0,

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

@Entity(tableName = "former")
data class Former(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,
    val birthDay: Long,
    val experienceYear: Int,
    val genere: String
)


data class ResearchWithFormer(
    @Embedded val research: Research,

    @Relation(
        parentColumn = "formerId",
        entityColumn = "id"
    )

    val former: Former
)