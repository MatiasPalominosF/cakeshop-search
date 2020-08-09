package com.example.apppasteleria.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apppasteleria.R
import com.example.apppasteleria.data.DataSource
import com.example.apppasteleria.data.model.Order
import com.example.apppasteleria.domain.RepoImpl
import com.example.apppasteleria.fragments.adaptador.OrderAdapter
import com.example.apppasteleria.fragments.adaptador.OrderAdapter2
import com.example.apppasteleria.fragments.viewmodel.MainViewModel
import com.example.apppasteleria.fragments.viewmodel.MainViewModel2
import com.example.apppasteleria.fragments.viewmodel.VMFactory
import com.example.apppasteleria.vo.Resource
import kotlinx.android.synthetic.main.fragment_my_orders.*

class MyOrdersFragment : Fragment(), OrderAdapter2.OnOrderClickListener {

    //Creando instancia view model
    private val viewModel by viewModels<MainViewModel> {
        VMFactory(RepoImpl(DataSource()))
    }

    //Creando instancia del adapter
    private lateinit var adapter: OrderAdapter2

    //Creando instancia viewmodel
    private val viewModel2 by lazy {
        ViewModelProvider(this).get(MainViewModel2::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_orders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        adapter = OrderAdapter2(requireContext(), this)
        rv_orders.adapter = adapter
        val dummyList = mutableListOf<Order>()
        /*dummyList.add(
            Order(
                "https://comidaschilenas.com/wp-content/uploads/2019/08/Receta-de-torta-de-mil-hojas.jpg",
                "Torta de lúcuma",
                "Pastelería Amada JyM",
                "Para 20 personas",
                "20000"
            )
        )
        dummyList.add(
            Order(
                "https://www.mozart.cl/wp-content/uploads/2020/05/tmp376.jpg",
                "Torta de Mil hojas",
                "Pastelería Las Palmeras",
                "Para 15 personas",
                "15000"
            )
        )
        dummyList.add(
            Order(
                "https://blogs.eitb.eus/wp-content/uploads/sites/45/2011/11/DSC_0391_phixr-573x383.jpg",
                "Galletas",
                "Pastelería de Curicó",
                "20 unidades",
                "2500"
            )
        )*/
        observeData()

        /*viewModel.fetchOrderList.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    progress_bar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    progress_bar.visibility = View.GONE
                    rv_orders.adapter = OrderAdapter(requireContext(), result.data, this)
                }
                is Resource.Failure -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Ocurrió un error al traer los datos ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })*/
    }

    private fun observeData() {
        viewModel2.fetchDataOrders().observe(viewLifecycleOwner, Observer {
            Log.d("DATOS", "$it")
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun setupRecyclerView() {
        rv_orders.layoutManager = LinearLayoutManager(requireContext())
        rv_orders.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun onOrderClick(order: Order) {
        val bundle = Bundle()
        bundle.putParcelable("order", order)

        Log.d("BUNDLE", "$bundle")
        findNavController().navigate(R.id.orderDetailFragment, bundle)
    }


}