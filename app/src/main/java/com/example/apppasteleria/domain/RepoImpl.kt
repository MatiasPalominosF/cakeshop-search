package com.example.apppasteleria.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apppasteleria.data.model.Order
import com.example.apppasteleria.data.model.Pasteleria
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RepoImpl : Repo {

    override fun getDataOrder(): LiveData<MutableList<Order>> {
        var auth: FirebaseAuth = FirebaseAuth.getInstance()
        val mutableData = MutableLiveData<MutableList<Order>>()
        FirebaseFirestore.getInstance().collection("pedidos").get().addOnSuccessListener { result ->
            val listData = mutableListOf<Order>()
            for (document in result) {
                Log.d("DOCUMENTO", "ESTO VIENE AHORA: ${document.data}")
                Log.d("UID", "ESTO TIENE EL UID ACA: ${auth.uid}")
                val uid = document.getString("uid")
                val image = document.getString("image")
                val name = document.getString("name")
                val nameCakeShop = document.getString("nameCakeShop")
                val description = document.getString("description")
                val price = document.getString("price")
                val quantity = document.getString("quantity")
                val order = Order(
                    uid!!,
                    image!!,
                    name!!,
                    nameCakeShop!!,
                    description!!,
                    price!!,
                    quantity!!
                )
                if (uid == auth.uid.toString()) {
                    println("ENTRÓ")
                    listData.add(order)
                }

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