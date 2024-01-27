package com.example.fitme_up.venueowner.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.OnButtonClickListener
import com.example.fitme_up.R
import com.example.fitme_up.user.adapter.bookingAdapter.BookingVenueTab1Adapter
import com.example.fitme_up.user.booking.BookingVenueDetails
import com.example.fitme_up.user.cup.CupLeaderboard
import com.example.fitme_up.user.dataset.CupData
import com.example.fitme_up.user.dataset.VenueData
import com.example.fitme_up.venueowner.dataset.VenueOwnerCupData

class VenueOwnerCupListAdapter(private val myDataset: List<VenueOwnerCupData>, private val parentFragment: Fragment) : RecyclerView.Adapter<VenueOwnerCupListAdapter.MyViewHolder>(), OnButtonClickListener {

    class MyViewHolder(itemView: View, private val listener: OnButtonClickListener) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.findViewById(R.id.text_recycler_title)
        val textView2: TextView = itemView.findViewById(R.id.text_recycler_subtitle)
        val textView3: TextView = itemView.findViewById(R.id.text_recycler_subtitle2)
        val textView4: TextView = itemView.findViewById(R.id.text_recycler_subtitle_right2)

        fun bind(item: VenueOwnerCupData) {
            textView1.text = item.cupName
            textView2.text = item.cupSport
            textView3.text = item.cupVenue
            textView4.text = item.cupDate
            itemView.findViewById<Button>(R.id.btn_view_details).setOnClickListener {
                listener.onButtonClick()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_venue_cup, parent, false)
        return MyViewHolder(view, this)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = myDataset[position]
        holder.textView1.text = item.cupName
        holder.textView3.text = item.cupSport
        holder.textView2.text = item.cupVenue
        holder.textView4.text = item.cupDate

        holder.itemView.findViewById<Button>(R.id.btn_view_details).setOnClickListener {
            this.onButtonClick()
        }

    }

    override fun onButtonClick() {
        val newFragment = CupLeaderboard()
        val transaction = parentFragment.parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_cont, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun getItemCount() = myDataset.size
}