package com.example.fitme_up.venueowner.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.OnItemClickListener
import com.example.fitme_up.R

class VenueOwnerMyVenueAdapter(private val stringList: List<String>, private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<VenueOwnerMyVenueAdapter.ViewHolder>(),
    OnItemClickListener {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.text_recycler_title)

        init {
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_venue_my_venue, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = stringList[position]
        holder.textView.text = item
    }

    override fun getItemCount() = stringList.size

    override fun onItemClick(position: Int) {
        TODO("Not yet implemented")
    }
}