package com.dhilasdrh.zerohunger.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dhilasdrh.zerowaste.databinding.ItemArticleBinding
import com.dhilasdrh.zerowaste.model.Article

class ArticleAdapter(private var listArticle: ArrayList<Article>, private var listener: (Article) -> Unit)
    : RecyclerView.Adapter<ArticleAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleAdapter.RecyclerViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleAdapter.RecyclerViewHolder, position: Int) {
        holder.bind(listArticle[position], listener)
    }

    override fun getItemCount(): Int = listArticle.size

    inner class RecyclerViewHolder(private val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(items: Article, listener: (Article) -> Unit) {
            with(binding) {
                Glide.with(itemView.context)
                        .load(items.photo)
                        .apply(RequestOptions().override(350, 550))
                        .into(imgItemArticle)
                tvArticleTitle.text = items.title
                tvArticleDescription.text = items.description

                itemView.setOnClickListener {
                    listener(items)
                }
            }
        }
    }
}
