package com.hiltretrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hiltretrofitdemo.databinding.ActivityMainBinding
import com.hiltretrofitdemo.db.Channel
import com.hiltretrofitdemo.utils.NetworkResult
import com.hiltretrofitdemo.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        fetchData()
        /*_binding.imgRefresh.setOnClickListener {
            fetchResponse()
        }

        _binding.add.setOnClickListener {
            val channel = Channel(1,"test")
            viewModel.addChannelData(channel)
        }*/

        _binding.employeesList.layoutManager = LinearLayoutManager(this)
    }

    private fun fetchData() {
        fetchResponse()
        viewModel.response.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let {
                        Log.e("Mango",it.toString())
                        it.data?.let {
                            _binding.employeesList.adapter = EmployeeAdapter(it)
                        }

                    }
                    _binding.pbDog.visibility = View.GONE
                }

                is NetworkResult.Error -> {
                    _binding.pbDog.visibility = View.GONE
                    Toast.makeText(
                        this,
                        response.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is NetworkResult.Loading -> {
                    _binding.pbDog.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun fetchResponse() {
        viewModel.fetchData()
        _binding.pbDog.visibility = View.VISIBLE

    }
}