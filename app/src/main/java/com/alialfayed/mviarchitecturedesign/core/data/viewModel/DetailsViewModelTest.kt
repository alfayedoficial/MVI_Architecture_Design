package com.alialfayed.mviarchitecturedesign.core.data.viewModel

import android.util.Log
import androidx.lifecycle.Transformations
import com.alialfayed.mviarchitecturedesign.core.base.viewModel.BaseViewModel
import com.alialfayed.mviarchitecturedesign.core.data.dispatcher.LocalDispatcher
import com.alialfayed.mviarchitecturedesign.core.domain.action.JobAction
import com.alialfayed.mviarchitecturedesign.core.domain.state.StateLocalJob
import com.alialfayed.mviarchitecturedesign.core.domain.state.ViewStateLocalJob
import com.alialfayed.mviarchitecturedesign.core.room.WishListJob
import com.alialfayed.mviarchitecturedesign.core.room.WishListJobDao

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 9/15/2021 - 1:53 AM
 */
class DetailsViewModelTest(private val dao: WishListJobDao) :BaseViewModel() {

    private val jobLocalDispatcher by lazy {
        LocalDispatcher(dao)
    }

    private val viewStateLocalJob = ViewStateLocalJob()

    fun insertViewStateInsertJob(jobs : WishListJob) = Transformations.map(jobLocalDispatcher.dispatchAction(
        JobAction.InsertJobAction(jobs)
    )){
        when (it) {
            is StateLocalJob.Loading -> viewStateLocalJob.copy(isLoading = true)
            is StateLocalJob.Success -> viewStateLocalJob.copy(data = it.data)
            is StateLocalJob.ErrorString -> viewStateLocalJob.copy(errorString = it.message)
            else -> viewStateLocalJob.copy(errorString = "error")
        }
    }

}