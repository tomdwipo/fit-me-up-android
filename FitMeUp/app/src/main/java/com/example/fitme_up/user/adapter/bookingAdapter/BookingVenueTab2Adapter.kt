package com.example.fitme_up.user.adapter.bookingAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.OnButtonClickListener
import com.example.fitme_up.R
import com.example.fitme_up.user.booking.BookingVenueDetails
import com.example.fitme_up.user.dataset.VenueData

class BookingVenueTab2Adapter(private val myDataset: List<VenueData>, private val parentFragment: Fragment) : RecyclerView.Adapter<BookingVenueTab2Adapter.MyViewHolder>(),
    OnButtonClickListener {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.findViewById(R.id.text_recycler_title)
        val textView2: TextView = itemView.findViewById(R.id.text_recycler_subtitle)
        val textView3: TextView = itemView.findViewById(R.id.text_recycler_subtitle2)
        val textView4: TextView = itemView.findViewById(R.id.text_recycler_subtitle_right2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = myDataset[position]
        holder.textView1.text = item.venue_name
        holder.textView2.text = item.sports_name
        holder.textView3.text = item.domicile
        holder.textView4.text = "Rp. " + item.venue_price.toString()

    }

    override fun getItemCount() = myDataset.size

    override fun onButtonClick() {
        val newFragment = BookingVenueDetails()
        val transaction = parentFragment.parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_cont, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}