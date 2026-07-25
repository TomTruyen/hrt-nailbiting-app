package com.tomtruyen.cusp.di

import androidx.room.Room
import com.tomtruyen.cusp.data.local.AppDatabase
import com.tomtruyen.cusp.data.repository.CheckInRepository
import com.tomtruyen.cusp.ui.screens.SharedViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "cusp_database"
        ).build()
    }
    
    single { get<AppDatabase>().checkInDao() }
    
    single { CheckInRepository(get()) }
    
    viewModel { SharedViewModel(get()) }
}
