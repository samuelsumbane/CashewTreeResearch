package com.samuelsumbane.cashewtreedata.domain.dbModel

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.samuelsumbane.cashewtreedata.domain.model.Farmer
import com.samuelsumbane.cashewtreedata.domain.model.Research
import com.samuelsumbane.cashewtreedata.domain.model.ResearchWithFarmer
import kotlinx.coroutines.flow.Flow

@Dao
interface ResearcDao {
    @Query("SELECT * FROM research")
    fun getAll(): Flow<List<ResearchWithFarmer>>

    @Insert
    suspend fun insertResearch(research: Research)

    @Delete
    suspend fun delete(research: Research)
}

@Dao
interface FormerDao {
    @Query("SELECT * FROM farmer")
    fun getAllFormers(): Flow<List<Farmer>>

    @Upsert
    suspend fun insertFormer(farmer: Farmer)

}