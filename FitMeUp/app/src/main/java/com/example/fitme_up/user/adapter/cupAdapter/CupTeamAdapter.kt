package com.example.fitme_up.user.adapter.cupAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.OnButtonClickListener
import com.example.fitme_up.R
import com.example.fitme_up.user.cup.CupTeamDetails
import com.example.fitme_up.user.dataset.CupTeamData

class CupTeamAdapter(private val myDataset: List<CupTeamData>, private val parentFragment: Fragment) : RecyclerView.Adapter<CupTeamAdapter.MyViewHolder>(),
    OnButtonClickListener {

    inner class MyViewHolder(itemView: View, private val listener: OnButtonClickListener) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.findViewById(R.id.text_recycler_title)
        val textView2: TextView = itemView.findViewById(R.id.text_recycler_subtitle)
        val textView3: TextView = itemView.findViewById(R.id.text_recycler_subtitle2)
        val textView4: TextView = itemView.findViewById(R.id.text_recycler_subtitle_right2)
        val detailsBtn: Button = itemView.findViewById(R.id.btn_view_details)

        fun bind(item: CupTeamData) {
            textView1.text = item.teamName
            textView2.text = item.teamSport
            textView3.text = item.teamDomicile
            if (textView2.text == "Badminton"){
                textView4.text = item.teamMember.toString() + "/" + 8 + "players"
            }
            else{
                textView4.text = item.teamMember.toString() + "/" + 10 + "players"
            }

            detailsBtn.setOnClickListener(){
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
        holder.textView1.text = item.teamName
        holder.textView2.text = item.teamSport
        holder.textView3.text = item.teamDomicile

        holder.detailsBtn.setOnClickListener {
            this.onButtonClick()
        }

        if (holder.textView2.text == "Badminton"){
            holder.textView4.text = item.teamMember.toString() + "/" + 8 + " players"
        }
        else{
            holder.textView4.text = item.teamMember.toString() + "/" + 10 + " players"
        }
    }

    override fun getItemCount() = myDataset.size

    override fun onButtonClick() {
        val newFragment = CupTeamDetails()
        val transaction = parentFragment.parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_cont, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}