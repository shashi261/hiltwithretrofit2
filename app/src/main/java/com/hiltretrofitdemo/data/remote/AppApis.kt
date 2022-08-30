package com.hiltretrofitdemo.data.remote

import com.hiltretrofitdemo.model.EmployeeResponse
import com.hiltretrofitdemo.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface AppApis {

    @GET(Constants.RANDOM_URL)
    suspend fun getData(): Response<EmployeeResponse>
}