package com.smu.hellokotlin2

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.smu.hellokotlin2.FavoritesFragment.FavoritesListInteractionListener
import com.smu.hellokotlin2.model.DataFavorites
import java.text.SimpleDateFormat

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [FavoritesListInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class FavoritesRecyclerViewAdapter(private val mValues: List<DataFavorites>, private val mListener: FavoritesListInteractionListener?) : RecyclerView.Adapter<FavoritesRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_favorites, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues.get(position)
        val strCurrentDate = mValues.get(position).dateEvent
        var format = SimpleDateFormat("yyyy-mm-dd")
        val newDate = format.parse(strCurrentDate)

        format = SimpleDateFormat("EEE, dd MMMM yyyy")
        val date = format.format(newDate)

        holder.tvDateMatch.text = date
        holder.tvHomeTeam.text = mValues.get(position).strHomeTeam
        holder.tvScoreHome.text = mValues.get(position).intHomeScore
        holder.tvScoreAway.text = mValues.get(position).intAwayScore
        holder.tvAwayTeam.text = mValues.get(position).strAwayTeam

        holder.mView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onFavoritesListInteraction(holder.mItem!!)
                }
            }
        })
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val tvDateMatch: TextView
        val tvHomeTeam: TextView
        val tvScoreHome: TextView
        val tvScoreAway: TextView
        val tvAwayTeam: TextView
        var mItem: DataFavorites? = null

        init {
            tvDateMatch = mView.findViewById<View>(R.id.dateMatch) as TextView
            tvHomeTeam = mView.findViewById<View>(R.id.homeTeam) as TextView
            tvScoreHome = mView.findViewById<View>(R.id.homeTeamScore) as TextView
            tvScoreAway = mView.findViewById<View>(R.id.awayTeamScore) as TextView
            tvAwayTeam = mView.findViewById<View>(R.id.awayTeam) as TextView
        }

        override fun toString(): String {
            return super.toString() + " '" + tvHomeTeam.text + "'"
        }
    }
}
