package com.example.apppasteleria.domain

import com.example.apppasteleria.data.DataSource
import com.example.apppasteleria.data.model.Order
import com.example.apppasteleria.vo.Resource

class RepoImpl(private val dataSource: DataSource) : Repo {
    override fun getOrdersList(): Resource<List<Order>> {
        return dataSource.generateOrdersList
    }

}