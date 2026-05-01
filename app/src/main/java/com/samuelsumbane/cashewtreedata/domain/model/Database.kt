package com.samuelsumbane.cashewtreedata.domain.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.samuelsumbane.cashewtreedata.domain.dbModel.FormerDao
import com.samuelsumbane.cashewtreedata.domain.dbModel.ResearcDao

@Database(
    entities = [Research::class, Farmer::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun researchDao(): ResearcDao
    abstract fun formerDao(): FormerDao
}
