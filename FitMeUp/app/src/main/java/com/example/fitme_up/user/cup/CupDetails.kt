package com.example.fitme_up.user.cup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.fitme_up.R
import com.example.fitme_up.user.adapter.VenueImageAdapter
import com.example.fitme_up.user.adapter.cupAdapter.CupDetailsAdapter
import com.example.fitme_up.user.adapter.cupAdapter.CupListAdapter
import com.example.fitme_up.user.dataset.CupData
import com.example.fitme_up.user.dataset.CupTeamData
import com.example.fitme_up.user.dataset.LfgPlayerData
import com.example.fitme_up.user.dataset.VenueImageData

class CupDetails : Fragment() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewPager: ViewPager2

    private lateinit var btnJoinCup: Button
    private lateinit var btnQuitCup: Button

    private lateinit var cupTeamText: TextView
    private lateinit var cupNameText: TextView
    private lateinit var cupDateText: TextView
    private lateinit var cupVenueText: TextView
    private lateinit var cupVenueDomicileText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cup_details, container, false)

        recyclerView = view.findViewById(R.id.recycler_team_player)
        viewPager = view.findViewById(R.id.venuePager)

        btnJoinCup = view.findViewById(R.id.btn_cup_join)
        btnQuitCup = view.findViewById(R.id.btn_cup_quit)

        cupTeamText = view.findViewById(R.id.text_cup_team)
        cupNameText = view.findViewById(R.id.text_cup_name)
        cupDateText = view.findViewById(R.id.text_cup_date)
        cupVenueText = view.findViewById(R.id.text_venue_name)
        cupVenueDomicileText = view.findViewById(R.id.text_venue_domicile)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cupNameText.text = "Champion Cup"
        cupDateText.text = "2 January 2024"
        cupVenueText.text = "Zazila (Futsal & Badminton)"
        cupVenueDomicileText.text = "Jakarta Timur"

        viewManager = LinearLayoutManager(context)
        recyclerView.layoutManager = viewManager

        val imageList = listOf(
            VenueImageData(R.drawable.image_1),
            VenueImageData(R.drawable.image_1),
            VenueImageData(R.drawable.image_1)
        )

        val dataList2 = arrayListOf(
            "Super Team",
            "Team 1",
            "Badminton Success"
        )

        val alertDialog = getActivity()?.let {
            val editText = EditText(it)
            editText.isSingleLine = true
            AlertDialog.Builder(it)
                .setTitle("Join Cup")
                .setMessage("Are you sure you want to join this Cup?")
                .setPositiveButton("Join Cup") { _, _ ->
                    //kasih logic dari DB buat get data player
                    dataList2.add("My Team")
                    viewAdapter.notifyDataSetChanged()
                    btnJoinCup.visibility = View.GONE
                    btnQuitCup.visibility = View.VISIBLE
                    cupTeamText.text = dataList2.size.toString() + " Teams"
                }
                .setNegativeButton("Cancel") { _, _ -> }
                .create()
        }

        val alertDialog2 = getActivity()?.let {
            val editText = EditText(it)
            editText.isSingleLine = true
            AlertDialog.Builder(it)
                .setTitle("Leave Cup")
                .setMessage("Are you sure you want to leave this Cup?")
                .setPositiveButton("Quit Cup") { _, _ ->
                    //kasih logic dari DB buat get data player
                    dataList2.get(dataList2.indexOf("My Team"))
                    dataList2.remove("My Team")
                    viewAdapter.notifyDataSetChanged()
                    btnJoinCup.visibility = View.VISIBLE
                    btnQuitCup.visibility = View.GONE
                    cupTeamText.text = dataList2.size.toString() + " Teams"
                }
                .setNegativeButton("Cancel") { _, _ -> }
                .create()
        }

        btnJoinCup.setOnClickListener {
            if (alertDialog != null) {
                showAlertDialog(alertDialog)
            }
        }

        btnQuitCup.setOnClickListener {
            if (alertDialog2 != null) {
                showAlertDialog(alertDialog2)
            }
        }

        cupTeamText.text = dataList2.size.toString() + " Teams"

        viewAdapter = CupDetailsAdapter(dataList2)
        viewPager.adapter = VenueImageAdapter(imageList)

        recyclerView.adapter = viewAdapter

    }

    private fun showAlertDialog(alertDialog: AlertDialog) {
        if (!alertDialog.isShowing) {
            alertDialog.show()
        }
    }

}