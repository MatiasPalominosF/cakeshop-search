package com.example.apppasteleria.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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