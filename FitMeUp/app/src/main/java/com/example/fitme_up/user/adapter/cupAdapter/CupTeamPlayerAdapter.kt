package com.example.fitme_up.user.adapter.cupAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.R
import com.example.fitme_up.user.adapter.lfgAdapter.LfgPlayerAdapter
import com.example.fitme_up.user.dataset.CupTeamMemberData

class CupTeamPlayerAdapter(private val myDataset: List<CupTeamMemberData>) : RecyclerView.Adapter<CupTeamPlayerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.findViewById(R.id.text_player_name)
        val textView2: TextView = itemView.findViewById(R.id.text_player_role)

        fun bind(item: CupTeamMemberData) {
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
        holder.textView1.text = item.full_name
        holder.textView2.text = item.player_status
    }

    override fun getItemCount() = myDataset.size

    override fun getItemViewType(position: Int): Int {
        val item = myDataset[position]
        return if (item.player_status == "Room Master") 1 else 0
    }

}