package com.example.apppasteleria.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.apppasteleria.R
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

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
        val toolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar>(R.id.appbar)
        val texto = toolbar?.findViewById<TextView>(R.id.txt_saludo)
        val buscador = toolbar?.findViewById<LinearLayout>(R.id.buscador)
        val recuadro = toolbar?.findViewById<LinearLayout>(R.id.recuadro)
        recuadro?.visibility = View.VISIBLE
        buscador?.visibility = View.GONE
        texto?.text = "Bienvenido"

        realizarPedido.setOnClickListener {
            findNavController().navigate(R.id.pasteleriasFragment)
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