package com.example.apppasteleria.fragments.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.apppasteleria.domain.Repo
import com.example.apppasteleria.vo.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repo: Repo) : ViewModel() {
    val fetchOrderList = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getOrdersList())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}