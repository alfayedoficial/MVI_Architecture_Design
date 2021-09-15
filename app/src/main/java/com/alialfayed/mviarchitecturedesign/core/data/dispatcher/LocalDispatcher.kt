package com.alialfayed.mviarchitecturedesign.core.data.dispatcher

import android.util.Log
import androidx.lifecycle.liveData
import com.alialfayed.mviarchitecturedesign.core.domain.action.JobAction
import com.alialfayed.mviarchitecturedesign.core.domain.model.DataItem
import com.alialfayed.mviarchitecturedesign.core.domain.state.StateLocalGetJobs
import com.alialfayed.mviarchitecturedesign.core.domain.state.StateLocalJob
import com.alialfayed.mviarchitecturedesign.core.room.WishListJob
import com.alialfayed.mviarchitecturedesign.core.room.WishListJobDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.ArrayList

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 9/13/2021 - 12:59 PM
 */
class LocalDispatcher(private val dao : WishListJobDao) {

    fun dispatchAction(action: JobAction) = liveData{

        when (action) {
            is JobAction.InsertJobAction -> {
                emit(StateLocalJob.Loading)
                emit(insertJob(action.job))
            }
            is JobAction.GetAllJobsAction -> {
                emit(StateLocalJob.Loading)
                emit(getJobs())
            }
            else -> {
                emit(StateLocalJob.Loading)
                emit(deleteJobs())
            }
        }
    }

    private suspend fun deleteJobs(): StateLocalJob {
        withContext(Dispatchers.IO){
            dao.deleteAllWishListJobs()
        }
        return StateLocalJob.Success("Success")
    }

    private var models = listOf<WishListJob>()
     private suspend fun getJobs(): StateLocalGetJobs {
         withContext(Dispatchers.IO){
             models = dao.getAllWishListJobs()
         }
        return StateLocalGetJobs.Success(models)
    }

    private suspend fun insertJob(job: WishListJob): StateLocalJob {
        val res:Long
        withContext(Dispatchers.IO){
            res = dao.insertWishListJobs(job)
        }
       return StateLocalJob.Success(res)
    }

}