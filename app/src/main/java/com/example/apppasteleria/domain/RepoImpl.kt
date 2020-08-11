package com.example.apppasteleria.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apppasteleria.data.model.Order
import com.example.apppasteleria.data.model.Pasteleria
import com.google.firebase.firestore.FirebaseFirestore

class RepoImpl : Repo {

    override fun getDataOrder(): LiveData<MutableList<Order>> {
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

    override fun getDataPastelerias(): LiveData<MutableList<Pasteleria>> {
        val mutableData = MutableLiveData<MutableList<Pasteleria>>()
        FirebaseFirestore.getInstance().collection("pastelerias").get()
            .addOnSuccessListener { result ->
                val listData = mutableListOf<Pasteleria>()
                for (document in result) {
                    Log.d("DOCUMENTO", "$document")
                    val id = document.id
                    val image = document.getString("imagen")
                    val nombre = document.getString("nombre")
                    val direccion = document.getString("direccion")
                    val dueno = document.getString("dueno")
                    val celular = document.getString("celular")
                    val pasteleria =
                        Pasteleria(id!!, image!!, nombre!!, direccion!!, dueno!!, celular!!)
                    listData.add(pasteleria)
                    Log.d("PASTELERIA", "$pasteleria")
                }
                mutableData.value = listData
            }
        return mutableData
    }
}