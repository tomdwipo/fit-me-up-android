package com.example.fitme_up.venueowner.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.OnButtonClickListener
import com.example.fitme_up.R
import com.example.fitme_up.venueowner.VenueOwnerBookDetails
import com.example.fitme_up.venueowner.dataset.VenueOwnerBookData

class VenueOwnerBookingHistoryAdapter(private val myDataset: List<VenueOwnerBookData>, private val parentFragment: Fragment) : RecyclerView.Adapter<VenueOwnerBookingHistoryAdapter.MyViewHolder>(),
    OnButtonClickListener {

    class MyViewHolder(itemView: View, private val listener: OnButtonClickListener) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.findViewById(R.id.text_recycler_title)
        val textView2: TextView = itemView.findViewById(R.id.text_recycler_title2)
        val textView3: TextView = itemView.findViewById(R.id.text_recycler_subtitle_right)
        val textView4: TextView = itemView.findViewById(R.id.text_recycler_subtitle_right2)
        val button1: Button = itemView.findViewById(R.id.btn_view_details)

        fun bind(item: VenueOwnerBookData) {
            textView1.text = item.venueName
            textView2.text = item.venueSport
            textView3.text = item.bookStatus
            textView4.text = item.orderID
            button1.setOnClickListener {
                listener.onButtonClick()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_venue_history, parent, false)
        return MyViewHolder(view, this)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = myDataset[position]
        holder.textView1.text = item.venueName
        holder.textView2.text = item.venueSport
        holder.textView3.text = item.bookStatus
        holder.textView4.text = item.orderID
        holder.button1.setOnClickListener {
            this.onButtonClick()
        }

        val context: Context = holder.textView1.context

        holder.textView3.setTextColor(context.getColor(R.color.white))
        holder.textView3.setBackgroundResource(R.drawable.venue_waiting_approval)

        if(holder.textView3.text == "Completed"){
            holder.textView3.setTextColor(context.getColor(R.color.white))
            holder.textView3.setBackgroundResource(R.drawable.venue_completed)
        }
        if(holder.textView3.text == "Declined"){
            holder.textView3.setTextColor(context.getColor(R.color.white))
            holder.textView3.setBackgroundResource(R.drawable.venue_declined)
        }

    }

    override fun onButtonClick() {
        val newFragment = VenueOwnerBookDetails()
        val transaction = parentFragment.parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_cont, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun getItemCount() = myDataset.size
}