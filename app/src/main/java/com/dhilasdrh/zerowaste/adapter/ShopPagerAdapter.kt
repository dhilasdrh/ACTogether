package com.dhilasdrh.zerowaste.adapter

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dhilasdrh.zerowaste.R
import com.dhilasdrh.zerowaste.ui.shop.ShopFragment
import com.dhilasdrh.zerowaste.ui.shop.rescuefood.RescueFoodFragment
import com.dhilasdrh.zerowaste.ui.shop.thriftshop.ThriftShopFragment
import com.dhilasdrh.zerowaste.ui.shop.upcycleshop.UpcycleShopFragment
import com.dhilasdrh.zerowaste.ui.shop.zerowasteshop.ZeroWasteShopFragment

class ShopPagerAdapter(private val mContext: ShopFragment, fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    @StringRes
    private val tabTitle = intArrayOf(R.string.rescue_food, R.string.zero_waste_shop, R.string.thrift_shop, R.string.upcycled)

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = RescueFoodFragment()
            1 -> fragment = ZeroWasteShopFragment()
            2 -> fragment = ThriftShopFragment()
            3 -> fragment = UpcycleShopFragment()
        }
        return fragment as Fragment
    }

    override fun getCount(): Int {
        return tabTitle.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mContext.resources.getString(tabTitle[position])
    }
}