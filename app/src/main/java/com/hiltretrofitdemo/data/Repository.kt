package com.hiltretrofitdemo.data

import com.hiltretrofitdemo.data.remote.RemoteDataSource
import com.hiltretrofitdemo.model.BaseApiResponse
import com.hiltretrofitdemo.model.EmployeeResponse
import com.hiltretrofitdemo.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {

    suspend fun getDog(): Flow<NetworkResult<EmployeeResponse>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getData() })
        }.flowOn(Dispatchers.IO)
    }
}