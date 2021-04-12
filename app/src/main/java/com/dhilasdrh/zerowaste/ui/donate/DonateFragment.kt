package com.dhilasdrh.zerowaste.ui.donate

import android.content.res.TypedArray
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhilasdrh.zerowaste.R
import com.dhilasdrh.zerowaste.activity.*
import com.dhilasdrh.zerowaste.adapter.DonateListAdapter
import com.dhilasdrh.zerowaste.databinding.ActivityArticleDetailsBinding
import com.dhilasdrh.zerowaste.databinding.FragmentDonateBinding
import com.dhilasdrh.zerowaste.model.DonateList
import org.jetbrains.anko.support.v4.startActivity

class DonateFragment : Fragment() {

    private lateinit var donateViewModel: DonateViewModel
    private lateinit var binding: FragmentDonateBinding
    private val listDonation = ArrayList<DonateList>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        donateViewModel =
            ViewModelProvider(this).get(DonateViewModel::class.java)
        binding = FragmentDonateBinding.inflate(layoutInflater, container, false)

        val donateListAdapter = DonateListAdapter(listDonation){
          val position = listDonation.indexOf(it)
               if (position == 0){
                   startActivity<DonateMoneyActivity>()
               } else if (position == 1){
                   startActivity<DonateFoodActivity>()
               } else if (position == 2){
                   startActivity<DonateItemsActivity>()
               } else if (position == 3){
                   startActivity<DonateRecyclableActivity>()
               } else if (position == 4){
                   startActivity<DonateCompostableActivity>()
               }
        }

        getListDonation()

        binding.rvDonate.setHasFixedSize(true)
        binding.rvDonate.adapter = donateListAdapter
        binding.rvDonate.layoutManager = LinearLayoutManager(context)
        //binding.rvDonate.layoutManager = GridLayoutManager(context, 2)

        return binding.root
    }

    private fun getListDonation(): ArrayList<DonateList> {
        val dataTitle = resources.getStringArray(R.array.donate_name)
        val dataDescription = resources.getStringArray(R.array.donate_description)
        val dataPhoto = resources.getStringArray(R.array.donate_img)

        for (position in dataTitle.indices) {
            val donation = DonateList(
                dataTitle[position],
                dataDescription[position],
                dataPhoto[position]
            )
            listDonation.add(donation)
        }
        return listDonation
    }
}