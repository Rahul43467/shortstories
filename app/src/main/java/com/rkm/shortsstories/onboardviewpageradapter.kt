package com.rkm.shortsstories

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class onboardViewPagerAdapter(var list: List<Fragment>, val fm: FragmentManager, val lc: Lifecycle):
    FragmentStateAdapter(fm,lc) {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return list.get(position)
    }
}