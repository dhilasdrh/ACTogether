package com.dhilasdrh.zerowaste.ui.donate

import android.content.res.TypedArray
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhilasdrh.zerowaste.R
import com.dhilasdrh.zerowaste.adapter.DonateListAdapter
import com.dhilasdrh.zerowaste.databinding.FragmentDonateBinding
import com.dhilasdrh.zerowaste.model.DonateList

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
           /* startActivity<DonationDetailsActivity>(
                EXTRA_ITEM to it
            )*/
        }

        listDonation.addAll(getListDonation())

        binding.rvDonate.setHasFixedSize(true)
        binding.rvDonate.adapter = donateListAdapter
        binding.rvDonate.layoutManager = LinearLayoutManager(context)
        //binding.rvDonate.layoutManager = GridLayoutManager(context, 2)

        /*val textView: TextView = root.findViewById(R.id.text_notifications)
        donateViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return binding.root
    }

    private fun getListDonation(): ArrayList<DonateList> {
        val dataTitle = resources.getStringArray(R.array.donate_name)
        val dataDescription = resources.getStringArray(R.array.donate_description)
        val dataPhoto = resources.getStringArray(R.array.donate_img)

        val listDonate = ArrayList<DonateList>()
        for (position in dataTitle.indices) {
            val donation = DonateList(
                dataTitle[position],
                dataDescription[position],
                dataPhoto[position]
            )
            listDonate.add(donation)
        }
        return listDonate
    }
}