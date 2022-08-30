package com.hiltretrofitdemo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hiltretrofitdemo.data.Repository
import com.hiltretrofitdemo.db.Channel
import com.hiltretrofitdemo.db.ChannelDao
import com.hiltretrofitdemo.model.DataResponse
import com.hiltretrofitdemo.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository : Repository,
    private val channelDao: ChannelDao
) : ViewModel() {

    private val _response: MutableLiveData<NetworkResult<DataResponse>> = MutableLiveData()
    val response: LiveData<NetworkResult<DataResponse>> = _response

    fun fetchData() = viewModelScope.launch {
        repository.getDog().collect(){
            _response.value = it
        }
    }

    fun addChannelData(channel: Channel){
        viewModelScope.launch(Dispatchers.IO) {
            channelDao.insertChannel(channel)
        }
    }
}