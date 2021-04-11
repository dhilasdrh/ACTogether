package com.dhilasdrh.zerowaste.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.dhilasdrh.zerowaste.R
import com.dhilasdrh.zerowaste.databinding.ActivityArticleDetailsBinding

class ArticleDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleDetailsBinding

    companion object {
        const val ARTICLE_TITLE = "article_title"
        const val ARTICLE_DESCRIPTION = "article_description"
        const val ARTICLE_PHOTO = "article_photo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleDetailsBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val articleTitle = intent?.getStringExtra(ARTICLE_TITLE)
        val articleDescription = intent?.getStringExtra(ARTICLE_DESCRIPTION)
        val articlePhoto = intent?.getStringExtra(ARTICLE_PHOTO)

        binding.articleTitle.text = articleTitle
        binding.articleDescription.text = articleDescription

        Glide.with(this)
            .load(articlePhoto)
            .into(binding.imgArticle)

        supportActionBar?.title = articleTitle
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}