package com.example.fitme_up.feature_get_coaches.presentation.screens

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.OnButtonClickListener
import com.example.fitme_up.OnItemClickListener
import com.example.fitme_up.R
import com.example.fitme_up.feature_get_coaches.data.service.request.GetCoachesRequest
import com.example.fitme_up.feature_get_coaches.data.service.response.GetCoachesResponse
import com.example.fitme_up.user.booking.BookingCoachDetails
import com.example.fitme_up.user.dataset.CoachListData

class FindCoachAdapter(private val myDataset: List<GetCoachesResponse>, private val parentFragment: Fragment) :
    RecyclerView.Adapter<FindCoachAdapter.MyViewHolder>(), OnButtonClickListener {

    inner class MyViewHolder(itemView: View, private val listener: OnButtonClickListener) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.findViewById(R.id.text_recycler_title)
        val textView2: TextView = itemView.findViewById(R.id.text_recycler_subtitle)
//        val textView3: TextView = itemView.findViewById(R.id.text_recycler_subtitle2)
//        val textView4: TextView = itemView.findViewById(R.id.text_recycler_subtitle_right2)

        fun bind(item: GetCoachesResponse) {
            textView1.text = item.data[position].full_name
            textView2.text = item.data[position].favSports.toString()

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
        holder.textView1.text = item.data[position].full_name
        holder.textView2.text = item.data[position].favSports.toString()

        holder.itemView.findViewById<Button>(R.id.btn_view_details).setOnClickListener {
            this.onButtonClick()
        }
    }

    override fun onButtonClick() {
        val newFragment = BookingCoachDetails()
        val fragmentManager: FragmentManager? = parentFragment.activity?.supportFragmentManager
        fragmentManager?.let{
            val transaction = it.beginTransaction()
            transaction.replace(R.id.fragment_cont, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }

    override fun getItemCount() = myDataset.size

}