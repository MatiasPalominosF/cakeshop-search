package com.example.apppasteleria.domain

import androidx.lifecycle.LiveData
import com.example.apppasteleria.data.model.Order

interface Repo {
    fun getDataOrder(): LiveData<MutableList<Order>>
}