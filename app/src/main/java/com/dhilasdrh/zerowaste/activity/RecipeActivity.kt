package com.dhilasdrh.zerowaste.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhilasdrh.zerowaste.R
import com.dhilasdrh.zerowaste.adapter.EventAdapter
import com.dhilasdrh.zerowaste.adapter.RecipeAdapter
import com.dhilasdrh.zerowaste.databinding.ActivityRecipeBinding
import com.dhilasdrh.zerowaste.databinding.FragmentHomeBinding
import com.dhilasdrh.zerowaste.model.Article
import com.dhilasdrh.zerowaste.model.Event
import com.dhilasdrh.zerowaste.model.Recipe

class RecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeBinding
    private val listRecipe = ArrayList<Recipe>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recipeAdapter = RecipeAdapter(listRecipe){
            /*  startActivity<ArticleDetailsActivity>(
                 EXTRA_ITEM to it
                 )*/
        }

        listRecipe.addAll(getListRecipe())

        binding.rvRecipe.setHasFixedSize(true)
        binding.rvRecipe.adapter = recipeAdapter
        binding.rvRecipe.layoutManager = LinearLayoutManager(this)
    }

    private fun getListRecipe(): ArrayList<Recipe> {
        val dataTitle = resources.getStringArray(R.array.recipe_name)
        val dataTime = resources.getStringArray(R.array.recipe_desc2)
        val dataDescription = resources.getStringArray(R.array.recipe_desciption)
        val dataPhoto = resources.getStringArray(R.array.recipe_photo)

        val listRecipe = ArrayList<Recipe>()
        for (position in dataTitle.indices) {
            val recipe = Recipe (
                dataTitle[position],
                dataTime[position],
                dataDescription[position],
                dataPhoto[position]
            )
            listRecipe.add(recipe)
        }
        return listRecipe
    }
}