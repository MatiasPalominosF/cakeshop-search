package com.example.apppasteleria.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.apppasteleria.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    //Se crean las variables de Firebase.
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Se inicializan las varibles de Firebase.
        this.auth = FirebaseAuth.getInstance()
        this.db = FirebaseFirestore.getInstance()

        Log.d("AUTH", "Id del usuario: ${auth.currentUser?.uid}")
        realizarPedido.setOnClickListener {
            findNavController().navigate(R.id.ordersFragment)
        }

        misPedidos.setOnClickListener {
            findNavController().navigate(R.id.myOrdersFragment)
        }

        historialPedidos.setOnClickListener {
            findNavController().navigate(R.id.historyOrdersFragment)
        }

        cardOfertas.setOnClickListener {
            findNavController().navigate(R.id.saleFragment)
        }

    }


}