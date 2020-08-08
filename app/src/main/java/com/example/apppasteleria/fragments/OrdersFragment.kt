package com.example.apppasteleria.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apppasteleria.R
import com.example.apppasteleria.data.DataSource
import com.example.apppasteleria.domain.RepoImpl
import com.example.apppasteleria.fragments.adaptador.MainAdapter
import com.example.apppasteleria.fragments.viewmodel.MainViewModel
import com.example.apppasteleria.fragments.viewmodel.VMFactory
import com.example.apppasteleria.vo.Resource
import kotlinx.android.synthetic.main.fragment_orders.*

class OrdersFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_orders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}