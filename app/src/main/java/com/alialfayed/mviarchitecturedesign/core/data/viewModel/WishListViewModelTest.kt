package com.alialfayed.mviarchitecturedesign.core.data.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.alialfayed.mviarchitecturedesign.core.base.viewModel.BaseViewModel
import com.alialfayed.mviarchitecturedesign.core.data.dispatcher.LocalDispatcher
import com.alialfayed.mviarchitecturedesign.core.data.dispatcher.NetworkDispatcher
import com.alialfayed.mviarchitecturedesign.core.domain.action.JobAction
import com.alialfayed.mviarchitecturedesign.core.domain.api.MainApi
import com.alialfayed.mviarchitecturedesign.core.domain.model.DataItem
import com.alialfayed.mviarchitecturedesign.core.domain.state.*
import com.alialfayed.mviarchitecturedesign.core.room.WishListJob
import com.alialfayed.mviarchitecturedesign.core.room.WishListJobDao
import kotlinx.coroutines.launch

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 9/13/2021 - 11:00 AM
 */
class WishListViewModelTest(private val dao: WishListJobDao) : BaseViewModel() {

    private val jobLocalDispatcher by lazy {
        LocalDispatcher(dao)
    }

    private val viewStateLocalJob = ViewStateLocalJob()
    private val viewStateGetJobs = ViewStateGetJobs()


    fun getJobsViewStateLocal() = Transformations.map(jobLocalDispatcher.dispatchAction(JobAction.GetAllJobsAction)){
        when (it) {
            is StateLocalGetJobs.Loading -> viewStateGetJobs.copy(isLoading = true)
            is StateLocalGetJobs.Success -> viewStateGetJobs.copy(data = it.data)
            is StateLocalGetJobs.ErrorString -> viewStateGetJobs.copy(errorString = it.message)
            else -> viewStateGetJobs.copy(errorString = "error")
        }
    }


    fun deleteJobsViewStateLocal() = Transformations.map(jobLocalDispatcher.dispatchAction(JobAction.DeleteJobsAction)){
        when (it) {
            is StateLocalJob.Loading -> viewStateLocalJob.copy(isLoading = true)
            is StateLocalJob.Success -> viewStateLocalJob.copy(data = it.data)
            is StateLocalJob.ErrorString -> viewStateLocalJob.copy(errorString = it.message)
            else -> viewStateLocalJob.copy(errorString = "error")
        }
    }


}