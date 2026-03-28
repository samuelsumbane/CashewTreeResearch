package com.samuelsumbane.cashewtreedata

import androidx.room.Room
import com.samuelsumbane.cashewtreedata.data.repository.ResearchRepository
import com.samuelsumbane.cashewtreedata.domain.model.AppDatabase
import com.samuelsumbane.cashewtreedata.presentation.FormerViewModel
import com.samuelsumbane.cashewtreedata.presentation.ResearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // database
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "cashewTree_db"
        ).build()
    }

    single { get<AppDatabase>().formerDao() }
    single { get<AppDatabase>().researchDao() }


    // repository
    single { ResearchRepository(get()) }
    single { FormerRepository(get()) }

    viewModel { ResearchViewModel(get()) }
    viewModel { FormerViewModel(get()) }
}