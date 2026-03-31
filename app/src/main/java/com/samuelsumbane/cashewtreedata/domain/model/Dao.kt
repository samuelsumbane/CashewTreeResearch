package com.samuelsumbane.cashewtreedata.domain.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ResearcDao {
    @Query("SELECT * FROM research")
    fun getAll(): Flow<List<ResearchWithFormer>>

    @Insert
    suspend fun insertResearch(research: Research)

    @Delete
    suspend fun delete(research: Research)
}

@Dao
interface FormerDao {
    @Query("SELECT * FROM former")
    fun getAllFormers(): Flow<List<Former>>

    @Upsert
    suspend fun insertFormer(former: Former)

}