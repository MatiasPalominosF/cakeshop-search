package com.example.apppasteleria.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.apppasteleria.R
import com.example.apppasteleria.data.model.Pasteleria

class NewOrderFragment : Fragment() {

    private lateinit var pasteleria: Pasteleria

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireArguments().let {
            pasteleria = it.getParcelable("pasteleria")!!
            Log.d("PASTELERIA2", "$pasteleria")
        }
        //Utilizado para cambiar el título y activar buscador del toolbar
        val toolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar>(R.id.appbar)
        val texto = toolbar?.findViewById<TextView>(R.id.txt_saludo)
        val buscador = toolbar?.findViewById<LinearLayout>(R.id.buscador)
        val recuadro = toolbar?.findViewById<LinearLayout>(R.id.recuadro)
        recuadro?.visibility = View.VISIBLE
        buscador?.visibility = View.GONE
        texto?.text = "Realizar pedido"

        return inflater.inflate(R.layout.fragment_new_order, container, false)
    }
}