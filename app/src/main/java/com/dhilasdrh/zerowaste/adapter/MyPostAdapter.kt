package com.dhilasdrh.zerowaste.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dhilasdrh.zerowaste.databinding.ItemCommunityPostBinding
import com.dhilasdrh.zerowaste.model.Post


class MyPostAdapter(private var listPost: ArrayList<Post>, private var listener: (Post) -> Unit)
    : RecyclerView.Adapter<MyPostAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPostAdapter.RecyclerViewHolder {
        val binding = ItemCommunityPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyPostAdapter.RecyclerViewHolder, position: Int) {
        holder.bind(listPost[position], listener)
    }

    override fun getItemCount(): Int = 1

    inner class RecyclerViewHolder(private val binding: ItemCommunityPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(items: Post, listener: (Post) -> Unit) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(items.photoUrl)
                    .into(imgPostAvatar)

                /*   Glide.with(itemView.context)
                       .load(items.activityIcon)
                       .override(35, 35)
                       .into(imgActivityIcon)*/

                Glide.with(itemView.context)
                    .load(items.postImage)
                    .into(imgPostPhoto)

                tvPostName.text = items.name
                tvPostDate.text = items.date
                tvPostActivity.text = items.activity
                tvPostCaption.text = items.caption

                itemView.setOnClickListener {
                    listener(items)
                }
            }
        }
    }
}