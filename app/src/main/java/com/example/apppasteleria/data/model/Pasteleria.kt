package com.example.apppasteleria.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pasteleria(
    val id: String = "",
    val image: String = "",
    val nombre: String = "",
    val direccion: String = "",
    val dueno: String = "",
    val celular: String = ""
) : Parcelable