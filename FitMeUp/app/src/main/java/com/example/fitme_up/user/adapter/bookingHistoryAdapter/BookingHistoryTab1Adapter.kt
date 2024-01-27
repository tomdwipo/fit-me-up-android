package com.example.fitme_up.user.adapter.bookingHistoryAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.OnButtonClickListener
import com.example.fitme_up.R
import com.example.fitme_up.user.booking.BookingHistoryActiveDetails
import com.example.fitme_up.user.dataset.BookingData

class BookingHistoryTab1Adapter(private val myDataset: List<BookingData>, private val parentFragment: Fragment) : RecyclerView.Adapter<BookingHistoryTab1Adapter.MyViewHolder>(),
    OnButtonClickListener {
    class MyViewHolder(itemView: View, private val listener: OnButtonClickListener) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.findViewById(R.id.text_recycler_title)
        val textView2: TextView = itemView.findViewById(R.id.text_recycler_subtitle)
        val textView3: TextView = itemView.findViewById(R.id.text_recycler_subtitle2)
        val textView4: TextView = itemView.findViewById(R.id.text_recycler_subtitle_right)
        val textView5: TextView = itemView.findViewById(R.id.text_recycler_subtitle_right2)

        fun bind(item: BookingData) {
            textView1.text = item.VenueName
            textView2.text = item.Sport
            textView3.text = item.Domicile
            textView4.text = item.CoachName
            textView5.text = item.BookDate

            itemView.findViewById<Button>(R.id.btn_view_details).setOnClickListener {
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
        holder.textView1.text = item.VenueName
        holder.textView2.text = item.Sport
        holder.textView3.text = item.Domicile
        holder.textView4.text = item.CoachName
        holder.textView5.text = item.BookDate

        holder.itemView.findViewById<Button>(R.id.btn_view_details).setOnClickListener {
            this.onButtonClick()
        }

    }

    override fun onButtonClick() {
        val newFragment = BookingHistoryActiveDetails()
        val transaction = parentFragment.parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_cont, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun getItemCount() = myDataset.size
}