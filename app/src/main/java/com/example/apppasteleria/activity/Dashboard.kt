package com.example.apppasteleria.activity

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.apppasteleria.R
import com.example.apppasteleria.data.DataSource
import com.example.apppasteleria.domain.RepoImpl
import com.example.apppasteleria.fragments.viewmodel.MainViewModel
import com.example.apppasteleria.fragments.viewmodel.VMFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_dashboard.*


class Dashboard : AppCompatActivity() {

    //Se crean las variables de Firebase.
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    //Se crean las variables del XML.
    private lateinit var mTxtUsuario: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val navController: NavController = findNavController(R.id.nav_host_fragment)
        bottomNavigationView.setupWithNavController(navController)

        //NavigationUI.setupWithNavController(bottomNavigationView, navController)

        //Se inicializan las variables obtenidas del XML.
        //this.mTxtUsuario = findViewById(R.id.txtUsuario)
        //Se inicializan las varibles de Firebase.
        this.auth = FirebaseAuth.getInstance()
        this.db = FirebaseFirestore.getInstance()
        //cargarDatos()


    }

    private fun cargarDatos() {
        val docRef = db.collection("usuarios").document(auth.uid.toString())
        docRef.get().addOnCompleteListener {
            if (it.isSuccessful) {
                val document: DocumentSnapshot? = it.result
                if (document!!.exists()) {
                    val rut = document.get("rut").toString()
                    val nombre = document.get("nombre").toString()
                    val apellido = document.get("apellido").toString()
                    val correo = document.get("correo").toString()
                    val contrasena = document.get("contrasena").toString()

                    println("Nombre: " + nombre)
                    this.mTxtUsuario.text = nombre

                }
            } else {
                Toast.makeText(
                    this,
                    "No se han podido cargar los datos de la base de datos, reinicie.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }


}