package com.dhilasdrh.zerowaste.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dhilasdrh.zerowaste.databinding.ItemEventBinding
import com.dhilasdrh.zerowaste.model.Event

class EventAdapter(private var listEvent: ArrayList<Event>, private var listener: (Event) -> Unit)
    : RecyclerView.Adapter<EventAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventAdapter.RecyclerViewHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventAdapter.RecyclerViewHolder, position: Int) {
        holder.bind(listEvent[position], listener)
    }

    override fun getItemCount(): Int = listEvent.size

    inner class RecyclerViewHolder(private val binding: ItemEventBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(items: Event, listener: (Event) -> Unit) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(items.photo)
                    .into(imgEvent)
                tvEventName.text = items.title
                tvDate.text = items.date
                tvTime.text = items.time
                tvLocation.text = items.location

                itemView.setOnClickListener {
                    listener(items)
                }
            }
        }
    }
}