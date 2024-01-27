package com.example.fitme_up.user.adapter.lfgAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.R
import com.example.fitme_up.user.dataset.LfgPlayerData

class LfgPlayerAdapter(private val myDataset: List<LfgPlayerData>) : RecyclerView.Adapter<LfgPlayerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.findViewById(R.id.text_player_name)
        val textView2: TextView = itemView.findViewById(R.id.text_player_role)

        fun bind(item: LfgPlayerData) {
            textView1.text = item.full_name
            textView2.text = item.player_status
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = when (viewType) {
            1 -> {
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_lfg_player_master, parent, false)
            }
            else -> {
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_lfg_player, parent, false)
            }
        }
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = myDataset[position]
        holder.bind(item)
    }

    override fun getItemViewType(position: Int): Int {
        val item = myDataset[position]
        return if (item.player_status == "Room Master") 1 else 0
    }

    override fun getItemCount() = myDataset.size
}