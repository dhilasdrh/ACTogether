package com.dhilasdrh.zerowaste.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dhilasdrh.zerowaste.activity.ArticleDetailsActivity
import com.dhilasdrh.zerowaste.activity.DonateFoodActivity
import com.dhilasdrh.zerowaste.databinding.ItemDonateListBinding
import com.dhilasdrh.zerowaste.model.DonateList

class DonateListAdapter(private var listDonateList: ArrayList<DonateList>, private var listener: (DonateList) -> Unit)
    : RecyclerView.Adapter<DonateListAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonateListAdapter.RecyclerViewHolder {
        val binding = ItemDonateListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DonateListAdapter.RecyclerViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.bind(listDonateList[position], listener)
    }

    override fun getItemCount(): Int = listDonateList.size

    inner class RecyclerViewHolder(private val binding: ItemDonateListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(items: DonateList, listener: (DonateList) -> Unit) {
            with(binding) {
               Glide.with(itemView.context)
                    .load(items.img)
                    .into(imgDonation)
                tvDonationName.text = items.title
                tvDonationDescription.text = items.description
                // imgDonation.setImageResource(items.img)

                itemView.setOnClickListener {
                    listener(items)
                }
            }
        }
    }
}