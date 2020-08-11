package com.example.apppasteleria.fragments.adaptador

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apppasteleria.R
import com.example.apppasteleria.data.model.Order
import kotlinx.android.synthetic.main.order_row.view.*

class OrderAdapter(
    private val context: Context,
    private val itemClickListener: OnOrderClickListener
) :
    RecyclerView.Adapter<OrderAdapter.MainViewHolder>() {

    private var dataList = mutableListOf<Order>()

    interface OnOrderClickListener {
        fun onOrderClick(order: Order)
    }

    fun setListData(data: MutableList<Order>) {
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.order_row, parent, false)
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
        val order = dataList[position]
        holder.bindView(order)
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(order: Order) {
            Glide.with(context).load(order.image).centerCrop().into(itemView.img_order)
            itemView.txt_name_cake.text = order.name
            itemView.txt_name_cake_shop.text = order.nameCakeShop
            itemView.txt_price.text = order.price
            itemView.setOnClickListener {
                itemClickListener.onOrderClick(order)
            }
        }

    }

}