package com.example.apppasteleria.fragments.adaptador

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apppasteleria.R
import com.example.apppasteleria.data.model.Pasteleria
import kotlinx.android.synthetic.main.order_row_pastelerias.view.*

class PasteleriaAdapter(
    private val context: Context,
    private val itemClickListener: OnDataClickListener
) :
    RecyclerView.Adapter<PasteleriaAdapter.MainViewHolder>() {

    private var dataList = mutableListOf<Pasteleria>()

    interface OnDataClickListener {
        fun onDataClick(pasteleria: Pasteleria)
    }

    fun setListData(data: MutableList<Pasteleria>) {
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.order_row_pastelerias, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (dataList.size > 0) {
            dataList.size
        } else {
            0
        }
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val pasteleria = dataList[position]
        holder.bindView(pasteleria)
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(pasteleria: Pasteleria) {
            Glide.with(context).load(pasteleria.image).centerCrop().into(itemView.image_pasteleria)
            itemView.txt_name_cake_shop.text = pasteleria.nombre
            itemView.txt_direccion.text = pasteleria.direccion
            itemView.txt_celular.text = pasteleria.celular
            itemView.setOnClickListener {
                itemClickListener.onDataClick(pasteleria)
            }
        }

    }
}