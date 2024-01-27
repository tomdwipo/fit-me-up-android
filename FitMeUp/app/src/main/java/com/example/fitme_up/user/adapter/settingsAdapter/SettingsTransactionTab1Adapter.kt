package com.example.fitme_up.user.adapter.settingsAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.R
import com.example.fitme_up.user.dataset.SettingsTopUpData

class SettingsTransactionTab1Adapter(private val myDataset: List<SettingsTopUpData>, private val parentFragment: Fragment) : RecyclerView.Adapter<SettingsTransactionTab1Adapter.MyViewHolder>(){

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.findViewById(R.id.text_transaction_id)
        val textView2: TextView = itemView.findViewById(R.id.text_transaction_amount)
        val textView3: TextView = itemView.findViewById(R.id.text_transaction_date)

        fun bind(item: SettingsTopUpData) {
            textView1.text = item.topupId
            textView2.text = item.topupAmount.toString()
            textView3.text = item.topupDate
//            itemView.findViewById<Button>(R.id.btn_lfg_details).setOnClickListener {
//                listener.onButtonClick()
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_settings_transaction_history, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = myDataset[position]
        holder.textView1.text = item.topupId
        holder.textView2.text = "Rp. " + item.topupAmount.toString()
        holder.textView3.text = item.topupDate

//        holder.itemView.findViewById<Button>(R.id.btn_lfg_details).setOnClickListener {
//            this.onButtonClick()
//        }
    }

//    override fun onButtonClick() {
//        val newFragment = LfgDetails()
//        val transaction = parentFragment.parentFragmentManager.beginTransaction()
//        transaction.replace(R.id.fragment_cont, newFragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
//    }

    override fun getItemCount() = myDataset.size
}