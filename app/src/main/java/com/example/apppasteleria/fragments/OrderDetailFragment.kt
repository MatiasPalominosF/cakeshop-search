package com.example.apppasteleria.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.apppasteleria.R
import com.example.apppasteleria.data.model.Order
import kotlinx.android.synthetic.main.fragment_order_detail.*

class OrderDetailFragment : Fragment() {

    private lateinit var order: Order

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            order = it.getParcelable("order")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val toolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar>(R.id.appbar)
        val texto = toolbar?.findViewById<TextView>(R.id.txt_saludo)
        val buscador = toolbar?.findViewById<LinearLayout>(R.id.buscador)
        val recuadro = toolbar?.findViewById<LinearLayout>(R.id.recuadro)
        recuadro?.visibility = View.VISIBLE
        buscador?.visibility = View.GONE
        texto?.text = "Detalle producto"
        return inflater.inflate(R.layout.fragment_order_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rellenarLayout()
    }

    private fun rellenarLayout() {
        Glide.with(requireContext()).load(order.image).into(imageHeader)
        txtNombrePasteleria.text = order.nameCakeShop
        txtProducto.text = order.name
        txtPrecio.text = order.price
        txtDescripcion.text = order.description
    }

}