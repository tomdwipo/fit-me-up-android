package com.example.fitme_up.venueowner.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.OnButtonClickListener
import com.example.fitme_up.R
import com.example.fitme_up.coach.CoachRequestDetails
import com.example.fitme_up.venueowner.dataset.VenueOwnerBookData

class VenueOwnerHomeAdapter(private val myDataset: List<VenueOwnerBookData>, private val parentFragment: Fragment) : RecyclerView.Adapter<VenueOwnerHomeAdapter.MyViewHolder>(),
    OnButtonClickListener {

    class MyViewHolder(itemView: View, private val listener: OnButtonClickListener) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.findViewById(R.id.text_recycler_title)
        val textView2: TextView = itemView.findViewById(R.id.text_recycler_subtitle_right)
        val textView3: TextView = itemView.findViewById(R.id.text_recycler_subtitle_right2)
        val button1: Button = itemView.findViewById(R.id.btn_view_details)

        fun bind(item: VenueOwnerBookData) {
            textView1.text = item.venueSport
            textView2.text = item.bookStatus
            textView3.text = item.orderID
            button1.setOnClickListener {
                listener.onButtonClick()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list, parent, false)
        return MyViewHolder(view, this)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = myDataset[position]
        holder.textView1.text = item.venueSport
        holder.textView2.text = item.bookStatus
        holder.textView3.text = item.orderID
        holder.button1.setOnClickListener {
            this.onButtonClick()
        }
    }

    override fun onButtonClick() {
        val newFragment = CoachRequestDetails()
        val transaction = parentFragment.parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_cont, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun getItemCount() = myDataset.size
}