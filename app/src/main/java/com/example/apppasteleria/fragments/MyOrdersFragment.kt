package com.example.apppasteleria.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apppasteleria.R
import com.example.apppasteleria.data.model.Order
import com.example.apppasteleria.fragments.adaptador.OrderAdapter
import com.example.apppasteleria.fragments.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_my_orders.*

class MyOrdersFragment : Fragment(), OrderAdapter.OnOrderClickListener {

    //Creando instancia del adapter
    private lateinit var adapter: OrderAdapter

    //Creando instancia viewmodel
    private val viewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
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
        adapter = OrderAdapter(requireContext(), this)
        rv_orders.adapter = adapter

        observeData()

    }

    private fun observeData() {
        shimmer_view_container.startShimmer()
        viewModel.fetchDataOrders().observe(viewLifecycleOwner, Observer {
            Log.d("DATOS", "$it")
            shimmer_view_container.stopShimmer()
            shimmer_view_container.visibility = View.GONE
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