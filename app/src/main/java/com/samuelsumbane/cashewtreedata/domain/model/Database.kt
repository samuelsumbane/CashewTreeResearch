package com.samuelsumbane.cashewtreedata.domain.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Research::class, Former::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun researchDao(): ResearcDao
    abstract fun formerDao(): FormerDao
}
