package com.alialfayed.mviarchitecturedesign.core.data.viewModel

import android.util.Log
import androidx.lifecycle.Transformations
import com.alialfayed.mviarchitecturedesign.core.base.viewModel.BaseViewModel
import com.alialfayed.mviarchitecturedesign.core.data.dispatcher.NetworkDispatcher
import com.alialfayed.mviarchitecturedesign.core.domain.action.JobAction
import com.alialfayed.mviarchitecturedesign.core.domain.api.MainApi
import com.alialfayed.mviarchitecturedesign.core.domain.state.StateJob
import com.alialfayed.mviarchitecturedesign.core.domain.state.ViewStateJob

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 9/15/2021 - 1:44 AM
 */
class HomeViewModelTest(private val api : MainApi) : BaseViewModel() {


    private val jobNetworkDispatcher by lazy {
        NetworkDispatcher(api)
    }

    private val viewStateJob = ViewStateJob()

    val jobViewStateHome =
        Transformations.map(jobNetworkDispatcher.dispatchAction(JobAction.GetHomeAction)) {
            when (it) {
                is StateJob.Loading -> viewStateJob.copy(isLoading = true)
                is StateJob.Success -> viewStateJob.copy(data = it.data)
                is StateJob.Error -> viewStateJob.copy(error = it.error)
                is StateJob.ErrorString -> viewStateJob.copy(errorString = it.message)
                else -> viewStateJob.copy(errorString = "error")
            }
        }


}