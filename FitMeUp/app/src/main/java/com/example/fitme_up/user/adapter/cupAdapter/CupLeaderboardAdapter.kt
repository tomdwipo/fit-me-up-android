package com.example.fitme_up.user.adapter.cupAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.OnButtonClickListener
import com.example.fitme_up.R
import com.example.fitme_up.user.cup.CupLeaderboardTeamMemberList
import com.example.fitme_up.user.dataset.CupMatchData

class CupLeaderboardAdapter(private val myDataset: List<CupMatchData>, private val parentFragment: Fragment) : RecyclerView.Adapter<CupLeaderboardAdapter.MyViewHolder>(),
    OnButtonClickListener {

    class MyViewHolder(itemView: View, private val listener: OnButtonClickListener) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.findViewById(R.id.text_cup_team1_name)
        val textView2: TextView = itemView.findViewById(R.id.text_cup_team1_score)
        val textView3: TextView = itemView.findViewById(R.id.text_cup_team2_name)
        val textView4: TextView = itemView.findViewById(R.id.text_cup_team2_score)

        fun bind(item: CupMatchData) {
            textView1.text = item.team1Name
            textView2.text = item.team1Score.toString()
            textView3.text = item.team2Name
            textView4.text = item.team2Score.toString()

            textView1.setOnClickListener {
                listener.onButtonClick()
            }

            textView3.setOnClickListener {
                listener.onButtonClick()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_cup_score, parent, false)
        return MyViewHolder(view, this)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = myDataset[position]
        holder.textView1.text = item.team1Name
        holder.textView2.text = item.team1Score.toString()
        holder.textView3.text = item.team2Name
        holder.textView4.text = item.team2Score.toString()

        holder.itemView.findViewById<TextView>(R.id.text_cup_team1_name).setOnClickListener {
            this.onButtonClick()
        }
        holder.itemView.findViewById<TextView>(R.id.text_cup_team2_name).setOnClickListener {
            this.onButtonClick()
        }



    }

    override fun onButtonClick() {
        val newFragment = CupLeaderboardTeamMemberList()
        val transaction = parentFragment.parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_cont, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun getItemCount() = myDataset.size
}