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
import com.smu.hellokotlin2.model.DataDetailLeague
import com.smu.hellokotlin2.model.DataSpinnerLeague
import com.smu.hellokotlin2.presenter.MainPresenter
import com.smu.hellokotlin2.view.MainView
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
class NextMatchFragment : Fragment(), MainView, SwipeRefreshLayout.OnRefreshListener {
    // TODO: Customize parameters
    private var mColumnCount = 1
    private var mListener: NextMatchInteractionListener? = null
    private lateinit var mainPresenter: MainPresenter

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var list: RecyclerView
    private lateinit var spinnerNext: Spinner
    private lateinit var dataAllLeague: List<DataSpinnerLeague>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            mColumnCount = arguments!!.getInt(ARG_COLUMN_COUNT)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_next_match_list, container, false)
        setHasOptionsMenu(true)
        mainPresenter = MainPresenter(this, this.context!!)

        swipeRefresh = view.findViewById(R.id.swipeRefreshLayout)
        list = view.findViewById(R.id.listNextMatch)
        spinnerNext = view.findViewById(R.id.spinnerNext)


        swipeRefresh.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)
        swipeRefresh.setOnRefreshListener(this)

        mainPresenter.getAllLeague()

        spinnerNext.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                dataAllLeague.get(position).idLeague?.let { mainPresenter.getNextMatch(it) }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

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
                mainPresenter.searchEvent(query)
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


    override fun showSpinnerData(data: List<DataSpinnerLeague>) {
        if (data.size > 0) {
            dataAllLeague = data
            var leagueTitle = arrayOfNulls<String>(data.size)
            for (i in data.indices) {
                leagueTitle[i] = data.get(i).strLeague
            }

            val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, leagueTitle)

            if (spinnerNext != null) {
                spinnerNext.adapter = spinnerAdapter

                spinnerNext.setSelection(0)
            }

        }
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is NextMatchInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement NextMatchInteractionListener")
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
    interface NextMatchInteractionListener {
        // TODO: Update argument type and name
        fun onNextMatchInteraction(item: DataDetailLeague)
    }

    override fun onRefresh() {
        dataAllLeague.get(spinnerNext.selectedItemPosition).idLeague?.let { mainPresenter.getNextMatch(it) }
    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }

    override fun showTeamList(data: List<DataDetailLeague>) {
        list.layoutManager = LinearLayoutManager(context)

        list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        list.adapter = NextMatchRecyclerViewAdapter(data, mListener)
    }

    override fun showToast(message: String) {
        toast(message)
    }

    companion object {

        // TODO: Customize parameter argument names
        private val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        fun newInstance(columnCount: Int): NextMatchFragment {
            val fragment = NextMatchFragment()
            val args = Bundle()
            args.putInt(ARG_COLUMN_COUNT, columnCount)
            fragment.arguments = args
            return fragment
        }
    }
}
