package com.smu.hellokotlin2

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.smu.hellokotlin2.TeamFragment.OnTeamListFragmentInteractionListener
import com.smu.hellokotlin2.model.DataTeamLeague
import com.squareup.picasso.Picasso

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class TeamRecyclerViewAdapter(private val mValues: List<DataTeamLeague>, private val mListener: OnTeamListFragmentInteractionListener?) : RecyclerView.Adapter<TeamRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_team, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]

        mValues[position].strTeamBadge?.let { Picasso.get().load(it).resize(200, 200).onlyScaleDown().into(holder.ivBadge) }

        holder.tvTeam.text = mValues[position].strTeam

        holder.mView.setOnClickListener {
            mListener?.onTeamListFragmentInteractionListener(holder.mItem!!)
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val ivBadge: ImageView
        val tvTeam: TextView
        var mItem: DataTeamLeague? = null

        init {
            ivBadge = mView.findViewById<View>(R.id.imageTeamBadge) as ImageView
            tvTeam = mView.findViewById<View>(R.id.strTeam) as TextView
        }

        override fun toString(): String {
            return super.toString() + " '" + tvTeam.text + "'"
        }
    }
}
