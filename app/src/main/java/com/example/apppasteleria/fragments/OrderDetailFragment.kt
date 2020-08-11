package com.example.apppasteleria.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.apppasteleria.R
import com.example.apppasteleria.data.model.Order

class OrderDetailFragment : Fragment() {

    private lateinit var order: Order

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireArguments().let {
            order = it.getParcelable("order")!!
        }
        return inflater.inflate(R.layout.fragment_order_detail, container, false)
    }

}