package com.dhilasdrh.zerowaste.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dhilasdrh.zerowaste.databinding.ItemRecipeBinding
import com.dhilasdrh.zerowaste.model.Recipe

class RecipeAdapter(private var listRecipe: ArrayList<Recipe>, private var listener: (Recipe) -> Unit)
    : RecyclerView.Adapter<RecipeAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeAdapter.RecyclerViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeAdapter.RecyclerViewHolder, position: Int) {
        holder.bind(listRecipe[position], listener)
    }

    override fun getItemCount(): Int = listRecipe.size

    inner class RecyclerViewHolder(private val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(items: Recipe, listener: (Recipe) -> Unit) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(items.photo)
                    .into(imgRecipe)
                tvRecipeName.text = items.title
                tvRecipeDescription.text = items.description
                tvRecipeDesc2.text = items.time

                itemView.setOnClickListener {
                    listener(items)
                }
            }
        }
    }
}
