package com.example.eaglecompany.models

import retrofit2.Call
import retrofit2.http.GET


interface WorkerApi {
    @GET("carrier")
    fun carrier(): Call<Worker>?

    @GET("porter")
    fun porter(): Call<Worker>?

    @GET("worker")
    fun worker(): Call<Worker>?
}