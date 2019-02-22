package com.smu.hellokotlin2

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.view.MenuItem
import com.smu.hellokotlin2.model.*
import com.smu.hellokotlin2.presenter.TeamPresenter
import com.smu.hellokotlin2.view.MainView
import com.smu.hellokotlin2.view.PlayerView
import com.smu.hellokotlin2.view.TeamView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_player.*

class DetailPlayerActivity : AppCompatActivity(), MainView, TeamView, PlayerView, SwipeRefreshLayout.OnRefreshListener  {

    private lateinit var teamPresenter: TeamPresenter
    private lateinit var strName: String
    private lateinit var dataPlayer: DataPlayerLeague

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)
        strName = intent.getStringExtra("id")
        swipeRefreshLayoutDetailPlayer.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)
        swipeRefreshLayoutDetailPlayer.setOnRefreshListener(this)

        toolbarDetailPlayer.title = "Detail Player"

        setSupportActionBar(toolbarDetailPlayer)



        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        teamPresenter = TeamPresenter(this, this, this)
        teamPresenter.searchPlayerName(strName)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }


            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showAllPlayer(data: List<DataPlayerLeague>) {
        dataPlayer = data.get(0)

        dataPlayer.strThumb?.let { Picasso.get().load(it).resize(200, 200).onlyScaleDown().into(ivLogoDetailPlayer) }
        tvStrPlayer.text = dataPlayer.strPlayer
        tvDescPlayer.text = dataPlayer.strDescriptionEN
    }

    override fun showAllTeam(data: List<DataTeamLeague>) {
   }

    override fun showLoading() {
        swipeRefreshLayoutDetailPlayer.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefreshLayoutDetailPlayer.isRefreshing = false
    }

    override fun showTeamList(data: List<DataDetailLeague>) {
     }

    override fun showTeamDetail(data: List<DataImageBadgeTeam>) {
    }

    override fun showSpinnerData(data: List<DataSpinnerLeague>) {
    }

    override fun showToast(message: String) {
    }

    override fun onRefresh() {
        teamPresenter.searchPlayerName(strName)
    }
}
