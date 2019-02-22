package com.smu.hellokotlin2

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.smu.hellokotlin2.PlayerDetailFragment.OnListFragmentInteractionListener
import com.smu.hellokotlin2.model.DataPlayerLeague
import com.squareup.picasso.Picasso

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class PlayerRecyclerViewAdapter(private val mValues: List<DataPlayerLeague>, private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<PlayerRecyclerViewAdapter.ViewHolder>() {

    public override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_player, parent, false)
        return ViewHolder(view)
    }

    public override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues.get(position)
        mValues[position].strThumb?.let { Picasso.get().load(it).resize(200, 200).onlyScaleDown().into(holder.ivPhotoPlaer) }
        holder.tvPlayerName.setText(mValues.get(position).strPlayer)

        holder.mView.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(v: View) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener!!.onListFragmentInteraction(holder.mItem!!)
                }
            }
        })
    }

    public override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val ivPhotoPlaer: ImageView
        val tvPlayerName: TextView
        var mItem: DataPlayerLeague? = null

        init {
            ivPhotoPlaer = mView.findViewById<View>(R.id.ivPlayerPhoto) as ImageView
            tvPlayerName = mView.findViewById<View>(R.id.tvPlayerName) as TextView
        }

        public override fun toString(): String {
            return super.toString() + " '" + tvPlayerName.getText() + "'"
        }
    }
}
