package com.example.fitme_up.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.fitme_up.blueprint.FavSportData

class SportListAdapter(context: Context, private val maxItems: Int, val items: List<FavSportData>) : ArrayAdapter<FavSportData>(context, android.R.layout.simple_spinner_item, items) {

    override fun getCount(): Int {
        return if (maxItems > 0) Math.min(maxItems, items.size) else items.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false)
        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = items[position].sport_name
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getView(position, convertView, parent)
    }

}