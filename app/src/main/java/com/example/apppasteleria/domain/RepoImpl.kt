package com.example.apppasteleria.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apppasteleria.data.DataSource
import com.example.apppasteleria.data.model.Order
import com.example.apppasteleria.vo.Resource

class RepoImpl(private val dataSource: DataSource) : Repo {
    override fun getOrdersList(): Resource<List<Order>> {
        Log.d("DATA", "DataSource: ${dataSource.generateOrdersList}")
        return dataSource.generateOrdersList
    }

    override fun getDataOrder(): LiveData<MutableList<Order>> {
        TODO("Not yet implemented")
    }
}