package com.dhilasdrh.zerowaste.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dhilasdrh.zerowaste.databinding.ItemProductBinding
import com.dhilasdrh.zerowaste.model.Product

class ProductAdapter(private var listProduct: ArrayList<Product>, private var listener: (Product) -> Unit)
    : RecyclerView.Adapter<ProductAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.RecyclerViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductAdapter.RecyclerViewHolder, position: Int) {
        holder.bind(listProduct[position], listener)
    }

    override fun getItemCount(): Int = listProduct.size

    inner class RecyclerViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(items: Product, listener: (Product) -> Unit) {
            with(binding) {
                val totalSold = "${items.totalSold} Sold • 2 Left"
                val price = "Rp${items.price}"

                Glide.with(itemView.context)
                    .load(items.photo)
                    .into(image)
                tvName.text = items.title
                tvTotalSold.text = totalSold
                tvPrice.text = price

                itemView.setOnClickListener {
                    listener(items)
                }
            }
        }
    }
}