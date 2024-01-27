package com.example.fitme_up.user.adapter.cupAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.R
import com.example.fitme_up.user.dataset.CupTeamMemberData

class CupEditTeamAdapter(private val myDataset: List<CupTeamMemberData>, private val onDeleteListener: (CupTeamMemberData) -> Unit) : RecyclerView.Adapter<CupEditTeamAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.findViewById(R.id.text_player_name)
        val textView2: TextView = itemView.findViewById(R.id.text_player_role)

        val removeBtn: ImageButton = itemView.findViewById(R.id.btn_team_edit_remove_player)

        fun bind(item: CupTeamMemberData) {
            textView1.text = item.full_name
            textView2.text = item.player_status

            removeBtn.setOnClickListener() {
                onDeleteListener(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_cup_team_edit, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = myDataset[position]
        holder.textView1.text = item.full_name
        holder.textView2.text = item.player_status

        if(item.player_status.equals("Room Master")){
            holder.removeBtn.visibility = View.GONE
        }
        else{
            holder.removeBtn.visibility = View.VISIBLE
        }

        holder.removeBtn.setOnClickListener {
            onDeleteListener(item)

        }

    }

    override fun getItemCount() = myDataset.size

}