package com.example.developerslife.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.developerslife.R
import com.example.developerslife.ui.adapter.SectionViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TabFragment : Fragment(R.layout.tab_fragment_layout) {

    private lateinit var sectionViewPager: ViewPager2
    private lateinit var sectionTabLayout: TabLayout
    private val latestPostsFragment by lazy { LatestPostsFragment() }
    private val topPostsFragment by lazy { TopPostsFragment() }
    private val hotPostsFragment by lazy { HotPostsFragment() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initViewPager()
    }

    private fun initViewPager() {
        val championshipViewPagerAdapter = SectionViewPagerAdapter(this)
        sectionViewPager.adapter = championshipViewPagerAdapter
        championshipViewPagerAdapter.pages =
            listOf(latestPostsFragment, topPostsFragment, hotPostsFragment)

        TabLayoutMediator(sectionTabLayout, sectionViewPager) { tab: TabLayout.Tab, i: Int ->
            when (i) {
                0 -> tab.text = "Последние"
                1 -> tab.text = "Лучшие"
                2 -> tab.text = "Горячие"
            }
        }.attach()
    }

    private fun initView(view: View) {
        sectionViewPager = view.findViewById(R.id.tabFragmentViewPager)
        sectionTabLayout = view.findViewById(R.id.tabFragmentTabLayout)
    }
}