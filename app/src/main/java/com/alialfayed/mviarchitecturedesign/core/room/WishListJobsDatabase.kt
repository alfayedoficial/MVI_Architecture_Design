package com.alialfayed.mviarchitecturedesign.core.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WishListJob::class], version = 5, exportSchema = false)
//@TypeConverters(ImageConverter::class)
abstract class WishListJobsDatabase : RoomDatabase() {

    abstract fun jobDao(): WishListJobDao

}
