package com.example.systemtask.di.module

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class HttpClientModule {

    @Singleton
    @Provides
    fun providesOkHttp(
        @Named("connectionTimeoutInSeconds") connectionTimeOut: Int,
        @Named("readTimeoutInSeconds") readTimeOut: Int,
        isDebug: Boolean,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(connectionTimeOut.toLong(), TimeUnit.SECONDS)
            .readTimeout(readTimeOut.toLong(), TimeUnit.SECONDS)
        if (isDebug) {
            builder.addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }

    @Singleton
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }
}