package com.alialfayed.mviarchitecturedesign.core.di

import com.alialfayed.mviarchitecturedesign.core.domain.api.MainApi
import com.alialfayed.mviarchitecturedesign.core.utils.Constants.BASE_URL_API
import com.alialfayed.mviarchitecturedesign.core.utils.ExtensionsApp.getApiToken
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { ProvideNetwork().provideService() }
}

class ProvideNetwork{

    private fun provideLogging() = HttpLoggingInterceptor().also {
        it.level = HttpLoggingInterceptor.Level.BODY
    }

    private fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val newUrl = chain
                .request()
                .url
                .newBuilder()
                .build()

            val newRequest = chain
                .request()
                .newBuilder()
//                .addHeader("Content-Type", "application/json")
//                .addHeader("Accept", "application/json,*/*")
                .also {
                    it.addHeader("Authorization", getApiToken())
                }
                .url(newUrl)
                .build()

            chain.proceed(newRequest)
        }.addInterceptor(provideLogging())
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)

        .build()

    private fun retrofitService(): Retrofit{

        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .baseUrl(BASE_URL_API)
            .build()

    }

    fun provideService():MainApi{

        return retrofitService().create(MainApi::class.java)

    }
}
