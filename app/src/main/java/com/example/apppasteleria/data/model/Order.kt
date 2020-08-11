package com.example.apppasteleria.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Order(
    val uid: String = "",
    val image: String = "",
    val name: String = "",
    val nameCakeShop: String = "",
    val description: String = "",
    val price: String = "",
    val quantity: String = ""
) : Parcelable