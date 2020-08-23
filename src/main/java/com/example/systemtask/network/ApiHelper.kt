package com.example.systemtask.network


import com.example.systemtask.repo.pojo.Data
import io.reactivex.Single
import retrofit2.http.GET

interface ApiHelper {

    @GET("s/2iodh4vg0eortkl/facts")
    fun getFacts(
    ): Single<Data>

}