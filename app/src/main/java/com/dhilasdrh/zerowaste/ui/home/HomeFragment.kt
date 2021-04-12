package com.dhilasdrh.zerowaste.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhilasdrh.zerohunger.adapter.ArticleAdapter
import com.dhilasdrh.zerowaste.R
import com.dhilasdrh.zerowaste.activity.ArticleDetailsActivity
import com.dhilasdrh.zerowaste.activity.ArticleDetailsActivity.Companion.ARTICLE_DESCRIPTION
import com.dhilasdrh.zerowaste.activity.ArticleDetailsActivity.Companion.ARTICLE_PHOTO
import com.dhilasdrh.zerowaste.activity.ArticleDetailsActivity.Companion.ARTICLE_TITLE
import com.dhilasdrh.zerowaste.activity.InventoryActivity
import com.dhilasdrh.zerowaste.activity.MealPlanningActivity
import com.dhilasdrh.zerowaste.adapter.EventAdapter
import com.dhilasdrh.zerowaste.databinding.FragmentHomeBinding
import com.dhilasdrh.zerowaste.model.Article
import com.dhilasdrh.zerowaste.model.Event
import org.jetbrains.anko.support.v4.startActivity

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private val listArticle = ArrayList<Article>()
    private val listEvent = ArrayList<Event>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     //   homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        val articleAdapter = ArticleAdapter(listArticle){
            startActivity<ArticleDetailsActivity>(
                ARTICLE_TITLE to it.title,
                ARTICLE_DESCRIPTION to it.description,
                ARTICLE_PHOTO to it.photo
            )
        }

        val eventAdapter = EventAdapter(listEvent){
          /*  startActivity<ArticleDetailsActivity>(
                ARTICLE_TITLE to it.title,
                ARTICLE_DESCRIPTION to it.description,
                ARTICLE_PHOTO to it.photo)*/
        }

        listArticle.addAll(getListArticle())
        listEvent.addAll(getListEvent())

        binding.rvEvents.setHasFixedSize(true)
        binding.rvEvents.adapter = eventAdapter
        binding.rvEvents.setLayoutManager(
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        )

        binding.rvArticles.setHasFixedSize(true)
        binding.rvArticles.adapter = articleAdapter
        binding.rvArticles.setLayoutManager(
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        )

        binding.cvFoodInventory.setOnClickListener {
            startActivity<InventoryActivity>()
        }

        binding.cvMealPlanner.setOnClickListener {
            startActivity<MealPlanningActivity>()
        }

        binding.cvMealShoppingPlanner.setOnClickListener {

        }

        binding.cvRecipe.setOnClickListener {

        }
        return binding.root
    }

    private fun getListEvent(): ArrayList<Event> {
        val dataTitle = resources.getStringArray(R.array.event_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataDate = resources.getStringArray(R.array.event_date)
        val dataTime = resources.getStringArray(R.array.event_time)
        val dataLocation = resources.getStringArray(R.array.event_location)
        val dataPhoto = resources.getStringArray(R.array.event_photo)

        val listEvent = ArrayList<Event>()
        for (position in dataTitle.indices) {
            val event = Event(
                dataTitle[position],
                dataDescription[position],
                dataPhoto[position],
                dataDate[position],
                dataTime[position],
                dataLocation[position]
            )
            listEvent.add(event)
        }
        return listEvent
    }

    private fun getListArticle(): ArrayList<Article> {
        val dataTitle = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)

        val listArticle = ArrayList<Article>()
        for (position in dataTitle.indices) {
            val article = Article(
                dataTitle[position],
                dataDescription[position],
                dataPhoto[position]
            )
            listArticle.add(article)
        }
        return listArticle
    }
}