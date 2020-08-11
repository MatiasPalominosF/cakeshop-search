package com.example.apppasteleria.fragments.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apppasteleria.data.model.Order
import com.example.apppasteleria.data.model.Pasteleria
import com.example.apppasteleria.domain.RepoImpl

class MainViewModel : ViewModel() {

    private val repo = RepoImpl()
    fun fetchDataOrders(): LiveData<MutableList<Order>> {
        val mutableData = MutableLiveData<MutableList<Order>>()
        repo.getDataOrder().observeForever {
            mutableData.value = it
        }
        return mutableData
    }

    fun fetchDataPastelerias(): LiveData<MutableList<Pasteleria>> {
        val mutableData = MutableLiveData<MutableList<Pasteleria>>()
        repo.getDataPastelerias().observeForever {
            mutableData.value = it
        }
        return mutableData
    }
}