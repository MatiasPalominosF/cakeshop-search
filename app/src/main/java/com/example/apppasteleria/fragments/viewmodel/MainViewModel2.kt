package com.example.apppasteleria.fragments.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apppasteleria.data.model.Order
import com.example.apppasteleria.domain.RepoImpl2

class MainViewModel2 : ViewModel() {

    private val repo2 = RepoImpl2()
    fun fetchDataOrders(): LiveData<MutableList<Order>> {
        val mutableData = MutableLiveData<MutableList<Order>>()
        repo2.getDataOrder().observeForever {
            mutableData.value = it
        }
        return mutableData
    }
}