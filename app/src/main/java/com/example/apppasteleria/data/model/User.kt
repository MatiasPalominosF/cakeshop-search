package com.example.apppasteleria.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val rut: String = "",
    val name: String = "",
    val lastname: String = "",
    val email: String = "",
    val password: String = ""
) : Parcelable