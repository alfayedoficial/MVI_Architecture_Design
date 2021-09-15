package com.alialfayed.mviarchitecturedesign.core.domain.state

import com.alialfayed.mviarchitecturedesign.core.domain.model.ErrorResponse
import com.alialfayed.mviarchitecturedesign.core.domain.model.HomeResponse
import com.alialfayed.mviarchitecturedesign.core.room.WishListJob


data class ViewStateJob(
    val isLoading: Boolean = false,
    val errorString: String? = null,
    val error: ErrorResponse? = null,
    val data: HomeResponse? = null) : StateJob()

data class ViewStateLocalJob(
    val isLoading: Boolean = false,
    val errorString: String? = null,
    val data: Any? = null) : StateLocalJob()

data class ViewStateGetJobs(
    val isLoading: Boolean = false,
    val errorString: String? = null,
    val data: List<WishListJob>? = null) : StateLocalGetJobs()

