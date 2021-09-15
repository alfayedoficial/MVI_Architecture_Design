package com.alialfayed.mviarchitecturedesign.core.data.dispatcher

import androidx.lifecycle.liveData
import com.alialfayed.mviarchitecturedesign.core.domain.action.JobAction
import com.alialfayed.mviarchitecturedesign.core.domain.api.MainApi
import com.alialfayed.mviarchitecturedesign.core.domain.model.ErrorResponse
import com.alialfayed.mviarchitecturedesign.core.domain.state.StateJob
import com.squareup.moshi.Moshi
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 9/13/2021 - 12:59 PM
 */
class NetworkDispatcher(private val api: MainApi) {
    fun dispatchAction(action: JobAction) = liveData{

        when(action){
            is JobAction.GetHomeAction -> {
                emit(StateJob.Loading)
                emit(getHomeApi())
            }
        }
    }



    private suspend fun getHomeApi() : StateJob {
        val response = api.responseHome()
        return try {
            StateJob.Success(response)
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> StateJob.ErrorString(throwable.message)
                is HttpException -> {
                    //val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    StateJob.Error(errorResponse)
                }
                else -> {
                    StateJob.Error(null)
                }
            }
        }
    }

    private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
        return try {
            throwable.response()?.errorBody()?.source()?.let {
                val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
                moshiAdapter.fromJson(it)
            }
        } catch (exception: Exception) {
            null
        }
    }


}