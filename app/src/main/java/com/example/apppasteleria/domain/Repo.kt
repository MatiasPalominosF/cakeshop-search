package com.example.apppasteleria.domain

import androidx.lifecycle.LiveData
import com.example.apppasteleria.data.model.Order
import com.example.apppasteleria.data.model.Pasteleria

interface Repo {
    fun getDataOrder(): LiveData<MutableList<Order>>
    fun getDataPastelerias(): LiveData<MutableList<Pasteleria>>
}