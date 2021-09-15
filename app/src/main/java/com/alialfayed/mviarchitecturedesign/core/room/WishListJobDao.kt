package com.alialfayed.mviarchitecturedesign.core.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alialfayed.mviarchitecturedesign.core.domain.model.DataItem
import kotlinx.coroutines.flow.Flow

@Dao
interface WishListJobDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWishListJobs(jobs : WishListJob) : Long

    @Query("select * from JOB_TABLE ")
    fun getAllWishListJobs(): List<WishListJob>

    @Query("delete from JOB_TABLE")
    suspend fun deleteAllWishListJobs()

}