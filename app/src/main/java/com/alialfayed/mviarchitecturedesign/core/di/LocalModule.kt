package com.alialfayed.mviarchitecturedesign.core.di

import android.app.Application
import androidx.room.Room
import com.alialfayed.mviarchitecturedesign.core.room.WishListJobDao
import com.alialfayed.mviarchitecturedesign.core.room.WishListJobsDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localModule = module {
    single { ProvideLocal().provideDatabase(androidApplication()) }
    single { ProvideLocal().jobDao(get()) }
}

class ProvideLocal{

    fun provideDatabase(application: Application): WishListJobsDatabase {
        return Room.databaseBuilder(application, WishListJobsDatabase::class.java, "selflearn.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun jobDao(database: WishListJobsDatabase): WishListJobDao {
        return database.jobDao()
    }

}
