package com.smu.hellokotlin2

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.smu.hellokotlin2.TeamFavoriteFragment.OnListTeamFavFragmentInteractionListener
import com.smu.hellokotlin2.model.DataDetailTeam
import com.squareup.picasso.Picasso

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListTeamFavFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class TeamFavRecyclerViewAdapter(private val mValues: List<DataDetailTeam>, private val mListener: OnListTeamFavFragmentInteractionListener?) : RecyclerView.Adapter<TeamFavRecyclerViewAdapter.ViewHolder>() {

    public override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_team_fav, parent, false)
        return ViewHolder(view)
    }

    public override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues.get(position)

        mValues[position].strBadge?.let { Picasso.get().load(it).resize(200, 200).onlyScaleDown().into(holder.ivLogo) }

        holder.tvNama.setText(mValues.get(position).strTeam)

        holder.mView.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(v: View) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener!!.onListTeamFavFragmentInteraction(holder.mItem!!)
                }
            }
        })
    }

    public override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val ivLogo: ImageView
        val tvNama: TextView
        var mItem: DataDetailTeam? = null

        init {
            ivLogo = mView.findViewById<View>(R.id.imageTeamBadge) as ImageView
            tvNama = mView.findViewById<View>(R.id.strTeam) as TextView
        }

        public override fun toString(): String {
            return super.toString() + " '" + tvNama.getText() + "'"
        }
    }
}
