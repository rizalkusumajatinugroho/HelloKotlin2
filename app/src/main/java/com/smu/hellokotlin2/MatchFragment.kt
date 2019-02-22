package com.smu.hellokotlin2


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_match.*


/**
 * A simple [Fragment] subclass.
 */
class MatchFragment : Fragment() {
    private lateinit var pagerAdapter: ViewPagerAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as HomeActivity).setSupportActionBar(toolbarHome)

        pagerAdapter = ViewPagerAdapter(childFragmentManager)
        pagerAdapter.addFragment(LastMatchFragment(), "Last Match")
        pagerAdapter.addFragment(NextMatchFragment(), "Next Match")
//        pagerAdapter.addFragment(FavoritesFragment(), "Favorites")
        viewpagerHome.adapter = pagerAdapter

        tabsHome.setupWithViewPager(viewpagerHome)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_match, container, false)

        return view
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

}// Required empty public constructor
