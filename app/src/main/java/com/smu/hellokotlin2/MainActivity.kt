package com.smu.hellokotlin2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import com.smu.hellokotlin2.model.DataDetailLeague
import com.smu.hellokotlin2.model.DataFavorites
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), LastMatchFragment.LastMatchInteractionListener, NextMatchFragment.NextMatchInteractionListener, FavoritesFragment.FavoritesListInteractionListener {


    private lateinit var pagerAdapter: ViewPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)


        pagerAdapter = ViewPagerAdapter(supportFragmentManager)
        pagerAdapter.addFragment(LastMatchFragment(), "Last Match")
        pagerAdapter.addFragment(NextMatchFragment(), "Next Match")
        pagerAdapter.addFragment(FavoritesFragment(), "Favorites")
        viewpager.adapter = pagerAdapter

        tabs.setupWithViewPager(viewpager)

    }

    override fun onNextMatchInteraction(item: DataDetailLeague) {
        startActivity<SecondActivity>("id" to item.idEvent)
    }

    override fun onLastMatchInteraction(item: DataDetailLeague) {
        startActivity<SecondActivity>("id" to item.idEvent)
    }

    override fun onFavoritesListInteraction(item: DataFavorites) {
        startActivity<SecondActivity>("id" to item.idEvent)
    }


    class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        val itemList: MutableList<Fragment> = mutableListOf()
        val titleList: MutableList<String> = mutableListOf()

        override fun getItem(position: Int): Fragment = itemList.get(position)

        override fun getCount(): Int = itemList.size

        override fun getPageTitle(position: Int): CharSequence? = titleList.get(position)

        fun addFragment(fragment: Fragment, title: String) {
            itemList.add(fragment)
            titleList.add(title)
        }

    }


}
