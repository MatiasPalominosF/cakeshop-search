package com.example.apppasteleria.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apppasteleria.data.model.Order
import com.example.apppasteleria.vo.Resource
import com.google.firebase.firestore.FirebaseFirestore

class RepoImpl2 {

    fun getDataOrder(): LiveData<MutableList<Order>> {
        val mutableData = MutableLiveData<MutableList<Order>>()
        FirebaseFirestore.getInstance().collection("pedidos").get().addOnSuccessListener { result ->
            val listData = mutableListOf<Order>()
            for (document in result) {
                val image = document.getString("image")
                val name = document.getString("name")
                val nameCakeShop = document.getString("nameCakeShop")
                val description = document.getString("description")
                val price = document.getString("price")
                val order = Order(image!!, name!!, nameCakeShop!!, description!!, price!!)
                listData.add(order)
            }
            mutableData.value = listData
        }

        return mutableData
    }
}