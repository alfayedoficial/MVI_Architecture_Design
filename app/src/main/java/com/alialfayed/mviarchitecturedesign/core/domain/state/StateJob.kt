package com.alialfayed.mviarchitecturedesign.core.domain.state

import com.alialfayed.mviarchitecturedesign.core.domain.model.ErrorResponse
import com.alialfayed.mviarchitecturedesign.core.domain.model.HomeResponse
import com.alialfayed.mviarchitecturedesign.core.room.WishListJob


sealed class StateJob {
    object Loading : StateJob()
    data class Success(val data: HomeResponse?) : StateJob()
    data class Error(val error: ErrorResponse?) : StateJob()
    data class ErrorString(val message: String?) : StateJob()
}

sealed class StateLocalJob {
    object Loading : StateLocalJob()
    data class Success(val data: Any?) : StateLocalJob()
    data class ErrorString(val message: String?) : StateLocalJob()
}

sealed class StateLocalGetJobs {
    object Loading : StateLocalGetJobs()
    data class Success(val data: List<WishListJob>?) : StateLocalGetJobs()
    data class ErrorString(val message: String?) : StateLocalGetJobs()
}
