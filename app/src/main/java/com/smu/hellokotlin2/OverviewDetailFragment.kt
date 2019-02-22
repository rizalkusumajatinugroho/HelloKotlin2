package com.smu.hellokotlin2


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smu.hellokotlin2.model.*
import com.smu.hellokotlin2.presenter.TeamPresenter
import com.smu.hellokotlin2.view.MainView
import com.smu.hellokotlin2.view.PlayerView
import com.smu.hellokotlin2.view.TeamView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_overview_detail.*
import org.jetbrains.anko.support.v4.toast


/**
 * A simple [Fragment] subclass.
 */
class OverviewDetailFragment : Fragment(), MainView, TeamView, PlayerView, SwipeRefreshLayout.OnRefreshListener {


    private lateinit var teamPresenter: TeamPresenter
    private lateinit var dataImageBadgeTeam: DataImageBadgeTeam
    private lateinit var idTeam: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swipeRefreshLayoutDetail.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)
        swipeRefreshLayoutDetail.setOnRefreshListener(this)


        teamPresenter = TeamPresenter(this, this, this)
        val args = arguments

        idTeam = args!!.getString("id")

        teamPresenter.getDetailTeam(idTeam)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overview_detail, container, false)
    }

    override fun showAllPlayer(data: List<DataPlayerLeague>) {

    }

    override fun showAllTeam(data: List<DataTeamLeague>) {

    }

    override fun showLoading() {
        swipeRefreshLayoutDetail.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefreshLayoutDetail.isRefreshing = false
    }

    override fun showTeamList(data: List<DataDetailLeague>) {

    }

    override fun showTeamDetail(data: List<DataImageBadgeTeam>) {
        dataImageBadgeTeam = data.get(0)

        dataImageBadgeTeam.strTeamBadge?.let { Picasso.get().load(it).resize(200, 200).onlyScaleDown().into(ivLogoDetail) }
        tvStrTeam.text = dataImageBadgeTeam.strTeam
        tvDesc.text = dataImageBadgeTeam.strDescriptionEN
    }

    override fun showSpinnerData(data: List<DataSpinnerLeague>) {

    }

    override fun showToast(message: String) {
        toast(message)
    }

    override fun onRefresh() {
        teamPresenter.getDetailTeam(idTeam)
    }

}// Required empty public constructor
