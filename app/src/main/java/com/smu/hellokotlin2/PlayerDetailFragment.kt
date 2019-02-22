package com.smu.hellokotlin2

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smu.hellokotlin2.model.*
import com.smu.hellokotlin2.presenter.TeamPresenter
import com.smu.hellokotlin2.view.MainView
import com.smu.hellokotlin2.view.PlayerView
import com.smu.hellokotlin2.view.TeamView

/**
 * A fragment representing a list of Items.
 *
 *
 * Activities containing this fragment MUST implement the [OnListFragmentInteractionListener]
 * interface.
 */
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
class PlayerDetailFragment : Fragment(), MainView, TeamView, PlayerView, SwipeRefreshLayout.OnRefreshListener {


    // TODO: Customize parameters
    private var mListener: OnListFragmentInteractionListener? = null
    private lateinit var listPlayer: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var strName: String
    private lateinit var teamPresenter: TeamPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)
        swipeRefreshLayout.setOnRefreshListener(this)

        teamPresenter = TeamPresenter(this, this, this)

        val args = arguments

        strName = args!!.getString("name")
        Log.d("debug_rizal", "strName player : " + strName)
        teamPresenter.searchPlayer(strName)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_player_list, container, false)

        listPlayer = view.findViewById(R.id.listDetailPlayer)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayoutPlayer)

        return view
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnListTeamFavFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: DataPlayerLeague)
    }

    override fun showAllTeam(data: List<DataTeamLeague>) {

    }

    override fun showTeamDetail(data: List<DataImageBadgeTeam>) {

    }

    override fun showAllPlayer(data: List<DataPlayerLeague>) {
        listPlayer.layoutManager = LinearLayoutManager(context)

        listPlayer.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        listPlayer.adapter = PlayerRecyclerViewAdapter(data, mListener)
    }

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showTeamList(data: List<DataDetailLeague>) {

    }

    override fun showSpinnerData(data: List<DataSpinnerLeague>) {

    }

    override fun showToast(message: String) {

    }

    override fun onRefresh() {
        teamPresenter.searchPlayer(strName)
    }

}
