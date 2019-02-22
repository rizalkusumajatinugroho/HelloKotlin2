package com.smu.hellokotlin2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.smu.hellokotlin2.R.id.*
import com.smu.hellokotlin2.model.DataDetailLeague
import com.smu.hellokotlin2.model.DataDetailTeam
import com.smu.hellokotlin2.model.DataFavorites
import com.smu.hellokotlin2.model.DataTeamLeague
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.startActivity

class HomeActivity : AppCompatActivity(), LastMatchFragment.LastMatchInteractionListener, NextMatchFragment.NextMatchInteractionListener, FavoritesFragment.FavoritesListInteractionListener, TeamFragment.OnTeamListFragmentInteractionListener, TeamFavoriteFragment.OnListTeamFavFragmentInteractionListener {



    private var menuItem: Menu? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
//        setSupportActionBar(toolbarHome)
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                match -> {
                    loadMatchFragment(savedInstanceState)
                }
                teams -> {
                    loadTeamsFragment(savedInstanceState)
                }
                favorites -> {
                    loadFavoritesFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = match
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        return true
    }

    private fun loadMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, MatchFragment(), MatchFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadTeamsFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, TeamFragment(), TeamFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, AllFavoriteFragment(), AllFavoriteFragment::class.java.simpleName)
                    .commit()
        }
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

    override fun onTeamListFragmentInteractionListener(item: DataTeamLeague) {
        startActivity<DetailTeamActivity>("id" to item.idTeam, "name" to item.strTeam, "badge" to item.strTeamBadge)

    }

    override fun onListTeamFavFragmentInteraction(item: DataDetailTeam) {
        startActivity<DetailTeamActivity>("id" to item.idTeam, "name" to item.strTeam, "badge" to item.strBadge)
    }
}
