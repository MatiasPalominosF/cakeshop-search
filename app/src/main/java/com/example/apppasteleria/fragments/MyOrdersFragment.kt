package com.example.apppasteleria.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apppasteleria.R
import com.example.apppasteleria.data.DataSource
import com.example.apppasteleria.data.model.Order
import com.example.apppasteleria.domain.RepoImpl
import com.example.apppasteleria.fragments.adaptador.OrderAdapter
import com.example.apppasteleria.fragments.viewmodel.MainViewModel
import com.example.apppasteleria.fragments.viewmodel.VMFactory
import com.example.apppasteleria.vo.Resource
import kotlinx.android.synthetic.main.fragment_my_orders.*

class MyOrdersFragment : Fragment(), OrderAdapter.OnOrderClickListener {

    //Creando instancia view model
    private val viewModel by viewModels<MainViewModel> {
        VMFactory(RepoImpl(DataSource()))
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
        viewModel.fetchOrderList.observe(viewLifecycleOwner, Observer { result ->
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

        findNavController().navigate(R.id.orderDetailFragment, bundle)
    }


}