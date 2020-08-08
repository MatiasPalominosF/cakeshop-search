package com.example.apppasteleria.fragments.adaptador

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apppasteleria.R
import com.example.apppasteleria.base.BaseViewHolder
import com.example.apppasteleria.data.model.Order
import kotlinx.android.synthetic.main.order_row.view.*

class MainAdapter(
    private val context: Context,
    private val orderList: List<Order>,
    private val itemClickListener: OnOrderClickListener
) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnOrderClickListener {
        fun onOrderClick(order: Order)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.order_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(orderList[position], position)
        }
    }

    inner class MainViewHolder(itemView: View) : BaseViewHolder<Order>(itemView) {
        override fun bind(item: Order, position: Int) {
            Glide.with(context).load(item.image).centerCrop().into(itemView.img_order)
            itemView.txt_name_cake.text = item.name
            itemView.txt_name_cake_shop.text = item.nameCakeShop
            itemView.setOnClickListener {
                itemClickListener.onOrderClick(item)
            }
        }

    }

}