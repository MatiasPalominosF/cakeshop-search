package com.example.apppasteleria.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apppasteleria.R
import com.example.apppasteleria.data.model.Pasteleria
import com.example.apppasteleria.fragments.adaptador.PasteleriaAdapter
import com.example.apppasteleria.fragments.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_pastelerias.*

class PasteleriasFragment : Fragment(), PasteleriaAdapter.OnDataClickListener {

    //Se crea la instancia del adaptador de la pastelería
    private lateinit var adapter: PasteleriaAdapter

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
        return inflater.inflate(R.layout.fragment_pastelerias, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = activity?.findViewById<Toolbar>(R.id.appbar)
        val texto = toolbar?.findViewById<TextView>(R.id.txt_saludo)
        val buscador = toolbar?.findViewById<LinearLayout>(R.id.buscador)
        val recuadro = toolbar?.findViewById<LinearLayout>(R.id.recuadro)
        recuadro?.visibility = View.GONE
        buscador?.visibility = View.VISIBLE
        texto?.text = "Seleccionar pastelería"


        setupRecyclerView()
        observeData()

    }

    private fun setupRecyclerView() {
        rv_pastelerias.layoutManager = LinearLayoutManager(requireContext())
        rv_pastelerias.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        adapter = PasteleriaAdapter(requireContext(), this)
        rv_pastelerias.adapter = adapter
    }

    private fun observeData() {
        shimmer_view_container.startShimmer()
        viewModel.fetchDataPastelerias().observe(viewLifecycleOwner, Observer {
            shimmer_view_container.stopShimmer()
            shimmer_view_container.visibility = View.GONE
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onDataClick(pasteleria: Pasteleria) {
        val bundle = Bundle()
        bundle.putParcelable("pasteleria", pasteleria)
        findNavController().navigate(R.id.action_ordersFragment_to_newOrderFragment, bundle)
    }


}