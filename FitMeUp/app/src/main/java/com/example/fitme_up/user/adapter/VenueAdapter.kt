package com.example.fitme_up.user.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.OnButtonClickListener
import com.example.fitme_up.R
import com.example.fitme_up.user.booking.BookingCoachDetails
import com.example.fitme_up.user.booking.BookingVenueDetails
import com.example.fitme_up.user.dataset.VenueData

class VenueAdapter (private val myDataset2: List<VenueData>, private val parentFragment: Fragment) :
    RecyclerView.Adapter<VenueAdapter.MyViewHolder>(), OnButtonClickListener {

    class MyViewHolder(itemView: View, private val listener: OnButtonClickListener) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.findViewById(R.id.text_recycler_title)
        val textView2: TextView = itemView.findViewById(R.id.text_recycler_subtitle)
        val textView3: TextView = itemView.findViewById(R.id.text_recycler_subtitle2)
        val textView4: TextView = itemView.findViewById(R.id.text_recycler_subtitle_right2)

        val button: Button = itemView.findViewById(R.id.btn_view_details)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_list, parent, false)
        return MyViewHolder(view, this)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = myDataset2[position]
        holder.textView1.text = item.venue_name
        holder.textView2.text = item.sports_name
        holder.textView3.text = item.domicile
        holder.textView4.text = "Rp. " + item.venue_price.toString()

        holder.button.setOnClickListener(){
            this.onButtonClick()
        }

    }

    override fun getItemCount() = myDataset2.size

    override fun onButtonClick() {
        val newFragment = BookingVenueDetails()
        val transaction = parentFragment.parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_cont, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
