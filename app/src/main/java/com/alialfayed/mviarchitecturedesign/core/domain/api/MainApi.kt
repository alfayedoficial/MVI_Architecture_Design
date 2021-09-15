package com.alialfayed.mviarchitecturedesign.core.domain.api

import com.alialfayed.mviarchitecturedesign.core.domain.model.HomeResponse
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do : Main Api Interface
 */
interface MainApi {

    @POST("GetCompanyUsers")
    suspend fun responseHome(): HomeResponse

}