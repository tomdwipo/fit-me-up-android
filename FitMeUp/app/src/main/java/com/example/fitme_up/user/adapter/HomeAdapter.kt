package com.example.fitme_up.user.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.R
import com.example.fitme_up.user.dataset.ActivityData

class HomeAdapter(private val myDataset: List<ActivityData>) : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.findViewById(R.id.activityName)
        val textView2: TextView = itemView.findViewById(R.id.activityDomicile)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_home_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = myDataset[position]
        holder.textView1.text = item.title
        holder.textView2.text = item.description

    }

    override fun getItemCount() = myDataset.size
}

