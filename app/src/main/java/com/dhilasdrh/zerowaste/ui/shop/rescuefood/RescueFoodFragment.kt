package com.dhilasdrh.zerowaste.ui.shop.rescuefood

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dhilasdrh.zerowaste.R
import com.dhilasdrh.zerowaste.activity.ArticleDetailsActivity
import com.dhilasdrh.zerowaste.adapter.ProductAdapter
import com.dhilasdrh.zerowaste.databinding.RescueFoodFragmentBinding
import com.dhilasdrh.zerowaste.model.Product
import org.jetbrains.anko.support.v4.startActivity

class RescueFoodFragment : Fragment() {

    private lateinit var binding: RescueFoodFragmentBinding
    private val listRescueFood = ArrayList<Product>()
    private lateinit var viewModel: RescueFoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RescueFoodFragmentBinding.inflate(layoutInflater, container, false)

        val adapter = ProductAdapter(listRescueFood){
            startActivity<ArticleDetailsActivity>(
                //EXTRA_ITEM to it
            )
        }

        binding.rvRescueFood.setHasFixedSize(true)
        binding.rvRescueFood.adapter = adapter
        binding.rvRescueFood.layoutManager = GridLayoutManager(context, 2)

        listRescueFood.addAll(getListRescueFood())

        return binding.root
    }

    private fun getListRescueFood(): ArrayList<Product> {
        val dataTitle = resources.getStringArray(R.array.product_name)
        val dataTotalSold = resources.getStringArray(R.array.product_sold)
        val dataPrice = resources.getStringArray(R.array.product_price)
        val dataPhoto = resources.getStringArray(R.array.product_photo)

        val listFood = ArrayList<Product>()
        for (position in dataTitle.indices) {
            val food = Product(
                dataTitle[position],
                dataTotalSold[position],
                dataPrice[position],
                dataPhoto[position],
            )
            listFood.add(food)
        }
        return listFood
    }

}