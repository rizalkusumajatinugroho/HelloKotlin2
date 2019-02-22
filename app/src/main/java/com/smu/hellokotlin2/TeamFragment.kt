package com.smu.hellokotlin2

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.smu.hellokotlin2.model.*
import com.smu.hellokotlin2.presenter.MainPresenter
import com.smu.hellokotlin2.presenter.TeamPresenter
import com.smu.hellokotlin2.view.MainView
import com.smu.hellokotlin2.view.PlayerView
import com.smu.hellokotlin2.view.TeamView
import kotlinx.android.synthetic.main.fragment_team_list.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast

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
class TeamFragment : Fragment(), MainView, TeamView, PlayerView, SwipeRefreshLayout.OnRefreshListener {
    override fun showTeamDetail(data: List<DataImageBadgeTeam>) {

    }

    override fun showAllPlayer(data: List<DataPlayerLeague>) {

    }


    // TODO: Customize parameters

    private var mListener: OnTeamListFragmentInteractionListener? = null
    private lateinit var spinner: Spinner
    private lateinit var list: RecyclerView
    private lateinit var teamPresenter: TeamPresenter
    private lateinit var mainPresenter: MainPresenter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var dataAllLeague: List<DataSpinnerLeague>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as HomeActivity).setSupportActionBar(toolbarTeam)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_team_list, container, false)


        setHasOptionsMenu(true)

        spinner = view.findViewById(R.id.spinnerTeam)
        list = view.findViewById(R.id.listTeam)
        swipeRefresh = view.findViewById(R.id.swipeRefreshLayout)

        teamPresenter = TeamPresenter(this, this, this)
        mainPresenter = MainPresenter(this, this.context!!)

        swipeRefresh.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)
        swipeRefresh.setOnRefreshListener(this)



        mainPresenter.getAllLeague()

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                dataAllLeague.get(position).strLeague?.let { teamPresenter.getAllTeam(it) }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }


        // Set the adapter

        return view
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        super.onPrepareOptionsMenu(menu)

        val searchViewMenuItem = menu?.findItem(R.id.search)
        var searchView = searchViewMenuItem?.actionView as SearchView
//        val v = searchView.findViewById(android.support.v7.appcompat.R.id.search_button) as ImageView

        searchView.setQueryHint(resources.getString(R.string.search_title))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Log.d("debug_rizal", "query search : " + query)
                teamPresenter.searchTeam(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty())
                    return false
                else {
                    return true
                }


            }
        })
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnTeamListFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnListTeamFavFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun showAllTeam(data: List<DataTeamLeague>) {
        if (list != null) {
            list.layoutManager = LinearLayoutManager(context)

            list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

            list.adapter = TeamRecyclerViewAdapter(data, mListener)
        }


    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }

    override fun showTeamList(data: List<DataDetailLeague>) {

    }

    override fun showSpinnerData(data: List<DataSpinnerLeague>) {
        if (data.size > 0) {
            dataAllLeague = data
            var leagueTitle = arrayOfNulls<String>(data.size)
            for (i in data.indices) {
                leagueTitle[i] = data.get(i).strLeague
            }

            val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, leagueTitle)
            if (spinner != null) {
                spinner.adapter = spinnerAdapter

                spinner.setSelection(0)
            }

        }
    }

    override fun showToast(message: String) {
        toast(message)
    }

    override fun onRefresh() {
        dataAllLeague.get(spinner.selectedItemPosition).strLeague?.let { teamPresenter.getAllTeam(it) }
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
    interface OnTeamListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onTeamListFragmentInteractionListener(item: DataTeamLeague)
    }


}
