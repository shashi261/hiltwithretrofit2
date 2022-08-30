package com.hiltretrofitdemo.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val service: AppApis) {

    suspend fun getData() = service.getData()

}