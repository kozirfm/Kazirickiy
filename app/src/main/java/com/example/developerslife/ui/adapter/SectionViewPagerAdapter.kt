package com.example.developerslife.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionViewPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    var pages: List<Fragment> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun createFragment(position: Int): Fragment {
        return pages[position]
    }

    override fun getItemCount(): Int {
        return pages.count()
    }
}