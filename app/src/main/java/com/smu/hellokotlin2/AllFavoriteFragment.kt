package com.smu.hellokotlin2


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_all_favorite.*


/**
 * A simple [Fragment] subclass.
 */
class AllFavoriteFragment : Fragment() {

    private lateinit var pagerAdapter: ViewPagerAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as HomeActivity).setSupportActionBar(toolbarAllFavorite)
        setHasOptionsMenu(true)
        pagerAdapter = ViewPagerAdapter(childFragmentManager)
        pagerAdapter.addFragment(FavoritesFragment(), "Match Favorite")
        pagerAdapter.addFragment(TeamFavoriteFragment(), "Team Favorite")
        viewpagerAllFavorite.adapter = pagerAdapter

        tabsAllFavorite.setupWithViewPager(viewpagerAllFavorite)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_favorite, container, false)
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        super.onPrepareOptionsMenu(menu)
        val searchViewMenuItem = menu?.findItem(R.id.search)

        searchViewMenuItem?.setVisible(false)
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
