package com.example.apppasteleria.activity

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.apppasteleria.R
import com.example.apppasteleria.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_dashboard.*


class Dashboard : AppCompatActivity() {


    //Se crean las variables del XML.
    private lateinit var mTxtUsuario: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val toolbar = findViewById<Toolbar>(R.id.appbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        val navController: NavController = findNavController(R.id.nav_host_fragment)
        bottomNavigationView.setupWithNavController(navController)


    }
}