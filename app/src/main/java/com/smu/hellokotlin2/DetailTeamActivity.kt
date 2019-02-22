package com.smu.hellokotlin2

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.smu.hellokotlin2.db.database
import com.smu.hellokotlin2.model.DataDetailTeam
import com.smu.hellokotlin2.model.DataPlayerLeague
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.startActivity

class DetailTeamActivity : AppCompatActivity(), PlayerDetailFragment.OnListFragmentInteractionListener {

    private lateinit var pagerAdapter: ViewPagerAdapter
    private lateinit var idTeam: String
    private lateinit var strName: String
    private lateinit var strBadge: String
    private var menuItem: Menu? = null
    private var isFavourite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        toolbarDetailTeam.title = "Detail Team"

        setSupportActionBar(toolbarDetailTeam)



        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        idTeam = intent.getStringExtra("id")
        strName = intent.getStringExtra("name")
        strBadge = intent.getStringExtra("badge")

        var overviewDetailFragment = OverviewDetailFragment()
        var playerDetailFragment = PlayerDetailFragment()

        val args = Bundle()
        args.putString("id", idTeam)

        val argsName = Bundle()
        argsName.putString("name", strName)

        overviewDetailFragment.arguments = args
        playerDetailFragment.arguments = argsName

        pagerAdapter = ViewPagerAdapter(supportFragmentManager)
        pagerAdapter.addFragment(overviewDetailFragment, "Overview")
        pagerAdapter.addFragment(playerDetailFragment, "Player")
        viewpagerTeam.adapter = pagerAdapter

        favoriteState();

        tabsTeam.setupWithViewPager(viewpagerTeam)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavourite) removeFromFavorite() else addToFavorite()

                isFavourite = !isFavourite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavorite() {
        if (isFavourite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(DataDetailTeam.TABLE_FAVORITE_TEAM,
                        DataDetailTeam.idTeam to idTeam,
                        DataDetailTeam.strTeam to strName,
                        DataDetailTeam.strBadge to strBadge)
            }
            snackbar(viewpagerTeam, "Added to favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(viewpagerTeam, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(DataDetailTeam.TABLE_FAVORITE_TEAM, "(idTeam = {id})", "id" to idTeam)
            }
            snackbar(viewpagerTeam, "Removed from favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(viewpagerTeam, e.localizedMessage).show()
        }
    }

    private fun favoriteState() {
        database.use {
            val result = select(DataDetailTeam.TABLE_FAVORITE_TEAM)
                    .whereArgs("(idTeam = {id})",
                            "id" to idTeam)
            val favorite = result.parseList(classParser<DataDetailTeam>())
            if (!favorite.isEmpty()) isFavourite = true
        }
    }

    override fun onListFragmentInteraction(item: DataPlayerLeague) {
        startActivity<DetailPlayerActivity>("id" to item.strPlayer)
    }
}
