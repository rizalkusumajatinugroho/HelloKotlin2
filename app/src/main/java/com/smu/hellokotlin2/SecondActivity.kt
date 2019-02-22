package com.smu.hellokotlin2

import android.database.sqlite.SQLiteConstraintException
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.smu.hellokotlin2.R.drawable.ic_add_to_favorites
import com.smu.hellokotlin2.R.drawable.ic_added_to_favorites
import com.smu.hellokotlin2.db.database
import com.smu.hellokotlin2.model.DataDetailLeague
import com.smu.hellokotlin2.model.DataFavorites
import com.smu.hellokotlin2.model.DataImageBadgeTeam
import com.smu.hellokotlin2.model.DataSpinnerLeague
import com.smu.hellokotlin2.presenter.MainPresenter
import com.smu.hellokotlin2.presenter.SecondPresenter
import com.smu.hellokotlin2.view.MainView
import com.smu.hellokotlin2.view.SecondView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_second.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat

class SecondActivity : AppCompatActivity(), MainView, SecondView, SwipeRefreshLayout.OnRefreshListener {
    override fun showSpinnerData(data: List<DataSpinnerLeague>) {

    }

    private lateinit var mainPresenter: MainPresenter
    private lateinit var secondPresenter: SecondPresenter
    private lateinit var dataDetailEvent: DataDetailLeague
    private lateinit var dataDetailBadge: DataImageBadgeTeam
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var idEvent: String
    private var menuItem: Menu? = null
    private var isFavourite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        toolbarSecond.title = "Detail Event"

        setSupportActionBar(toolbarSecond)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        idEvent = intent.getStringExtra("id")

        mainPresenter = MainPresenter(this, this)
        secondPresenter = SecondPresenter(this, this, this)

        swipeRefresh = findViewById(R.id.detailSwipeRefreshLayout)

        swipeRefresh.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)
        swipeRefresh.setOnRefreshListener(this)

        favoriteState();

        mainPresenter.getDetailEvent(idEvent)


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
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(DataFavorites.TABLE_FAVORITE,
                        DataFavorites.idEvent to idEvent,
                        DataFavorites.strHomeTeam to dataDetailEvent.strHomeTeam,
                        DataFavorites.strAwayTeam to dataDetailEvent.strAwayTeam,
                        DataFavorites.intHomeScore to dataDetailEvent.intHomeScore,
                        DataFavorites.intAwayScore to dataDetailEvent.intAwayScore,
                        DataFavorites.dateEvent to dataDetailEvent.dateEvent)
            }
            snackbar(swipeRefresh, "Added to favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(swipeRefresh, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(DataFavorites.TABLE_FAVORITE, "(idEvent = {id})", "id" to idEvent)
            }
            snackbar(swipeRefresh, "Removed from favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(swipeRefresh, e.localizedMessage).show()
        }
    }

    private fun favoriteState() {
        database.use {
            val result = select(DataFavorites.TABLE_FAVORITE)
                    .whereArgs("(idEvent = {id})",
                            "id" to idEvent)
            val favorite = result.parseList(classParser<DataFavorites>())
            if (!favorite.isEmpty()) isFavourite = true
        }
    }


    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }

    override fun onRefresh() {
        mainPresenter.getDetailEvent(idEvent)
    }

    override fun showTeamList(data: List<DataDetailLeague>) {
        if (data.size > 0) {
            dataDetailEvent = data.get(0)

            val strCurrentDate = dataDetailEvent.dateEvent
            var format = SimpleDateFormat("yyyy-mm-dd")
            val newDate = format.parse(strCurrentDate)

            format = SimpleDateFormat("EEE, dd MMMM yyyy")
            val date = format.format(newDate)

            detailDateMatch.text = date
            detailHomeName.text = dataDetailEvent.strHomeTeam
            detailAwayName.text = dataDetailEvent.strAwayTeam
            detailHomeScore.text = dataDetailEvent.intHomeScore
            detailAwayScore.text = dataDetailEvent.intAwayScore

            detailHomeShot.text = dataDetailEvent.intHomeShots
            detailAwayShot.text = dataDetailEvent.intAwayShots

            detailHomeGoals.text = dataDetailEvent.strHomeGoalDetails
            detailAwayGoals.text = dataDetailEvent.strAwayGoalDetails

            detailHomeKeeper.text = dataDetailEvent.strHomeLineupGoalkeeper
            detailAwayKeeper.text = dataDetailEvent.strAwayLineupGoalkeeper

            detailHomeDefense.text = dataDetailEvent.strHomeLineupDefense
            detailAwayDefense.text = dataDetailEvent.strAwayLineupDefense

            detailHomeMidField.text = dataDetailEvent.strHomeLineupMidfield
            detailAwayMidField.text = dataDetailEvent.strAwayLineupMidfield

            detailHomeForward.text = dataDetailEvent.strHomeLineupForward
            detailAwayForward.text = dataDetailEvent.strAwayLineupForward

            detailHomeSubtitute.text = dataDetailEvent.strHomeLineupSubstitutes
            detailAwaySubtitute.text = dataDetailEvent.strAwayLineupSubstitutes

            secondPresenter.getDetailTeam(dataDetailEvent.idHomeTeam!!, "Home")
            secondPresenter.getDetailTeam(dataDetailEvent.idAwayTeam!!, "Away")

        }
    }

    override fun showToast(message: String) {
        toast(message)
    }

    override fun showImage(data: List<DataImageBadgeTeam>, team: String) {
        if (data.size > 0) {
            dataDetailBadge = data.get(0)

            if (team.equals("Home", true))
                dataDetailBadge.strTeamBadge?.let { Picasso.get().load(it).resize(200, 200).onlyScaleDown().into(detailHomeLogo) }
            else if (team.equals("Away", true))
                dataDetailBadge.strTeamBadge?.let { Picasso.get().load(it).resize(200, 200).onlyScaleDown().into(detailAwayLogo) }
        }

    }

}
