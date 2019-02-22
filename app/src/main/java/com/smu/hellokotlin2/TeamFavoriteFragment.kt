package com.smu.hellokotlin2

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smu.hellokotlin2.db.database
import com.smu.hellokotlin2.model.DataDetailTeam
import kotlinx.android.synthetic.main.fragment_team_fav_list.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

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
class TeamFavoriteFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {


    // TODO: Customize parameters
    private var mListener: OnListTeamFavFragmentInteractionListener? = null
    private var teamsFav: MutableList<DataDetailTeam> = mutableListOf()
    private lateinit var adapter: TeamFavRecyclerViewAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        swipeRefreshLayoutTeamFav.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)
        swipeRefreshLayoutTeamFav.setOnRefreshListener(this)

        adapter = TeamFavRecyclerViewAdapter(teamsFav, mListener)

        listTeamFav.layoutManager = LinearLayoutManager(context)

        listTeamFav.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        listTeamFav.adapter = adapter
        showFavorit()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_team_fav_list, container, false)


        return view
    }

    fun showFavorit() {
        context?.database?.use {
            swipeRefreshLayoutTeamFav.isRefreshing = false
            val result = select(DataDetailTeam.TABLE_FAVORITE_TEAM)
            val favorite = result.parseList(classParser<DataDetailTeam>())
            teamsFav.clear()
            teamsFav.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnListTeamFavFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnListTeamFavFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onRefresh() {
        showFavorit()
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
    interface OnListTeamFavFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListTeamFavFragmentInteraction(item: DataDetailTeam)
    }

}
