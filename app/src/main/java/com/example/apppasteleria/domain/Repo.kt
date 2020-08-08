package com.example.apppasteleria.domain

import com.example.apppasteleria.data.model.Order
import com.example.apppasteleria.vo.Resource

interface Repo {
    fun getOrdersList(): Resource<List<Order>>
}