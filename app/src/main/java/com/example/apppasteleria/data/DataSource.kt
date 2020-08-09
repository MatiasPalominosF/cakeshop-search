package com.example.apppasteleria.data

import android.util.Log
import android.widget.Toast
import com.example.apppasteleria.data.model.Order
import com.example.apppasteleria.vo.Resource
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class DataSource {

    //val generateOrdersList = Resource.Success()
    val generateOrdersList = Resource.Success(
        listOf(
            Order(
                "https://comidaschilenas.com/wp-content/uploads/2019/08/Receta-de-torta-de-mil-hojas.jpg",
                "Torta de lúcuma",
                "Pastelería Amada JyM",
                "Para 20 personas",
                "20000"
            ),
            Order(
                "https://www.mozart.cl/wp-content/uploads/2020/05/tmp376.jpg",
                "Torta de Mil hojas",
                "Pastelería Las Palmeras",
                "Para 15 personas",
                "15000"
            ),
            Order(
                "https://blogs.eitb.eus/wp-content/uploads/sites/45/2011/11/DSC_0391_phixr-573x383.jpg",
                "Galletas",
                "Pastelería de Curicó",
                "20 unidades",
                "2500"
            )
        )
    )


}