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

import com.smu.hellokotlin2.model.DataDetailLeague
import com.smu.hellokotlin2.model.DataFavorites
import com.smu.hellokotlin2.model.DataSpinnerLeague
import com.smu.hellokotlin2.view.MainView
import kotlinx.android.synthetic.main.fragment_favorites_list.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
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
class FavoritesFragment : Fragment(), MainView, SwipeRefreshLayout.OnRefreshListener {


    // TODO: Customize parameters
    private var mListener: FavoritesListInteractionListener? = null
    //    private lateinit var swipeRefresh : SwipeRefreshLayout
//    private lateinit var listFavorit : RecyclerView
    private var favorites: MutableList<DataFavorites> = mutableListOf()
    private lateinit var adapter: FavoritesRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)
        swipeRefreshLayout.setOnRefreshListener(this)

        adapter = FavoritesRecyclerViewAdapter(favorites, mListener)

        listFavorit.layoutManager = LinearLayoutManager(context)

        listFavorit.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        listFavorit.adapter = adapter
        showFavorit()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favorites_list, container, false)

//        swipeRefresh = view.findViewById(R.id.swipeRefreshLayout)
//        listFavorit = view.findViewById(R.id.listFavorit)


        return view
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is FavoritesListInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement FavoritesListInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun showSpinnerData(data: List<DataSpinnerLeague>) {

    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showTeamList(data: List<DataDetailLeague>) {

    }

    fun showFavorit() {
        context?.database?.use {
            swipeRefreshLayout.isRefreshing = false
            val result = select(DataFavorites.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<DataFavorites>())
            favorites.clear()
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun showToast(message: String) {
        toast(message)
    }

    override fun onRefresh() {
        favorites.clear()
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
    interface FavoritesListInteractionListener {
        // TODO: Update argument type and name
        fun onFavoritesListInteraction(item: DataFavorites)
    }

}
