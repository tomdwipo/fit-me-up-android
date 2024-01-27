package com.example.fitme_up.user.adapter.settingsAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.OnItemClickListener
import com.example.fitme_up.R
import com.example.fitme_up.blueprint.Domicile
import com.example.fitme_up.user.dataset.SettingsData

class SettingsAdapter(private val settingsList: List<SettingsData>, private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<SettingsAdapter.ViewHolder>(),
    OnItemClickListener {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val settingsIcon: ImageView = view.findViewById(R.id.icon_settings)
        val settingsName: TextView = view.findViewById(R.id.text_settings_name)
        val layout: CardView = view.findViewById(R.id.card_settings_list)

        init {
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_settings, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val setting = settingsList[position]
        holder.settingsName.text = setting.settings_title
        holder.settingsIcon.setImageResource(setting.settings_icon)

    }

    override fun getItemCount() = settingsList.size

    override fun onItemClick(position: Int) {
        TODO("Not yet implemented")
    }

}

//class SettingsAdapter(private val domicileList: List<Domicile>, private val parentFragment: Fragment, private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<SettingsAdapter.ViewHolder>(),
//    OnItemClickListener {
//
//    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val settingsName: TextView = view.findViewById(R.id.text_settings_name)
//        val settingsIcon: ImageView = view.findViewById(R.id.icon_settings)
//        val layout: CardView = view.findViewById(R.id.card_settings_list)
//
//        init {
//            itemView.setOnClickListener {
//                onItemClickListener.onItemClick(adapterPosition)
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_settings, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = domicileList[position]
//        holder.settingsName.text = item.domicile_name
//    }
//
//    override fun getItemCount() = domicileList.size
//
//    override fun onItemClick(position: Int) {
//        TODO("Not yet implemented")
//    }
//
//}