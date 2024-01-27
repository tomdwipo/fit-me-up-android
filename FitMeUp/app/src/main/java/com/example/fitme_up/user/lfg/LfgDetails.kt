package com.example.fitme_up.user.lfg

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.fitme_up.R
import com.example.fitme_up.user.adapter.VenueImageAdapter
import com.example.fitme_up.user.adapter.lfgAdapter.LfgPlayerAdapter
import com.example.fitme_up.user.booking.BookingVenueFind
import com.example.fitme_up.user.dataset.LfgPlayerData
import com.example.fitme_up.user.dataset.VenueImageData

class LfgDetails : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var btnJoinLfg: Button
    private lateinit var btnQuitLfg: Button
    private lateinit var btnCancelLfg: Button
    private lateinit var btnFindVenueLfg: Button
    private lateinit var viewPager: ViewPager2
    private lateinit var venueLayout: ConstraintLayout

    private lateinit var lfgNameText: TextView
    private lateinit var lfgTimeText: TextView
    private lateinit var lfgPlayerText: TextView

    private lateinit var venueNameText: TextView
    private lateinit var venueSportText: TextView
    private lateinit var venueDomicileText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_lfg_details, container, false)

        recyclerView = view.findViewById(R.id.recycler_team_player)
        viewManager = LinearLayoutManager(context)
        recyclerView.layoutManager = viewManager

        btnJoinLfg = view.findViewById(R.id.btn_lfg_join)
        btnQuitLfg = view.findViewById(R.id.btn_lfg_quit)
        btnFindVenueLfg = view.findViewById(R.id.btn_lfg_find_venue)
        btnCancelLfg = view.findViewById(R.id.btn_lfg_cancel)
        viewPager = view.findViewById(R.id.venuePager)
        venueLayout = view.findViewById(R.id.layout_lfg_venue)

        lfgNameText = view.findViewById(R.id.text_lfg_name)
        lfgTimeText = view.findViewById(R.id.text_lfg_time)
        lfgPlayerText = view.findViewById(R.id.text_lfg_player_min)

        venueNameText = view.findViewById(R.id.text_venue_name)
        venueSportText = view.findViewById(R.id.text_venue_sport)
        venueDomicileText = view.findViewById(R.id.text_venue_domicile)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lfgNameText.text = "LFG Fun Match"
        lfgTimeText.text = "09:00"
        lfgPlayerText.text = "(Min 4 required)"

        venueNameText.text = "Tifosi"
        venueSportText.text = "Badminton"
        venueDomicileText.text = "Jakarta Timur"

        val imageList = listOf(
            VenueImageData(R.drawable.image_1),
            VenueImageData(R.drawable.image_1),
            VenueImageData(R.drawable.image_1)
        )

        val dataList = arrayListOf(
            LfgPlayerData("John Rico", "Room Master"),
            LfgPlayerData("Jon Rico", "Player"),
            LfgPlayerData("Jhon Rico", "Player"),
            LfgPlayerData("Juan Rico", "Player"),
            LfgPlayerData("Joe Rico", "Player"),
            LfgPlayerData("Jo Rico", "Player"),
            LfgPlayerData("Jojo Rico", "Player")
        )

        addFragmentWithTag(this, "lfg_details")

        viewAdapter = LfgPlayerAdapter(dataList)
        viewPager.adapter = VenueImageAdapter(imageList)

        recyclerView.adapter = viewAdapter

        btnFindVenueLfg.setOnClickListener {
            val frag = BookingVenueFind()
            val tran = fragmentManager?.beginTransaction()
            tran?.addToBackStack(null)
            tran?.replace(R.id.fragment_cont, frag)?.commit()
        }



        val alertDialog = getActivity()?.let {
            val editText = EditText(it)
            editText.isSingleLine = true
            AlertDialog.Builder(it)
                .setTitle("Join LFG")
                .setMessage("Are you sure you want to join this LFG?")
                .setPositiveButton("Join LFG") { _, _ ->
                    //kasih logic dari DB buat get data player
                    dataList.add(LfgPlayerData("Player 1", "Player"))
                    viewAdapter.notifyDataSetChanged()
                    btnJoinLfg.visibility = View.GONE
                    btnQuitLfg.visibility = View.VISIBLE
                }
                .setNegativeButton("Cancel") { _, _ -> }
                .create()
        }

        val alertDialog2 = getActivity()?.let {
            val editText = EditText(it)
            editText.isSingleLine = true
            AlertDialog.Builder(it)
                .setTitle("Leave LFG")
                .setMessage("Are you sure you want to leave this LFG?")
                .setPositiveButton("Leave LFG") { _, _ ->
                    //kasih logic dari DB buat get data player
                    dataList.get(dataList.indexOf(LfgPlayerData("Player 1", "Player")))
                    dataList.remove(LfgPlayerData("Player 1", "Player"))
                    viewAdapter.notifyDataSetChanged()
                    btnJoinLfg.visibility = View.VISIBLE
                    btnQuitLfg.visibility = View.GONE
                }
                .setNegativeButton("Cancel") { _, _ -> }
                .create()
        }

        val alertDialog3 = getActivity()?.let {
            val editText = EditText(it)
            editText.isSingleLine = true
            AlertDialog.Builder(it)
                .setTitle("Cancel LFG")
                .setMessage("Are you sure you want to cancel this LFG?")
                .setPositiveButton("Cancel LFG") { _, _ ->
                    //kasih logic dari DB buat get data player
                    parentFragmentManager.popBackStack("lfg_venue_success", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    parentFragmentManager.popBackStack("lfg_find_venue", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    parentFragmentManager.popBackStack("lfg_venue_order", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    parentFragmentManager.popBackStack("lfg_venue_confirm", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    val frag = LfgFind()
                    val tran = fragmentManager?.beginTransaction()
                    tran?.addToBackStack(null)
                    parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    tran?.replace(R.id.fragment_cont, frag)?.commit()
                }
                .setNegativeButton("No") { _, _ -> }
                .create()
        }

        btnJoinLfg.setOnClickListener {
            if (alertDialog != null) {
                showAlertDialog(alertDialog)
            }
        }

        btnQuitLfg.setOnClickListener {
            if (alertDialog2 != null) {
                showAlertDialog(alertDialog2)
            }
        }

        btnCancelLfg.setOnClickListener(){
            if (alertDialog3 != null) {
                showAlertDialog(alertDialog3)
            }
        }

        val fragmentManager = activity?.supportFragmentManager
        if (fragmentManager != null) {
            val backStackCount = activity?.supportFragmentManager?.backStackEntryCount
            if (backStackCount != null) {
                if (backStackCount > 0) {

//                    Log.d("print", fragmentManager.findFragmentByTag("lfg_find_venue").toString())
                    val fm = fragmentManager.findFragmentByTag("lfg_venue_success")
                    val fm2 = fragmentManager.findFragmentByTag("lfg_create_new")

                    if (fm != null) {

                        Log.d("print", "from lfg success")
                        btnFindVenueLfg.visibility = View.GONE
                        btnCancelLfg.visibility = View.VISIBLE
//                        Toast.makeText(activity, "from lfg", Toast.LENGTH_SHORT).show()
                        //tambah logic data kalo dari lfg (masukin ke lfg DB)
                    }
                    else if (fm2 != null){
                        Log.d("print", "from lfg create new")
                        btnCancelLfg.visibility = View.VISIBLE
                        btnFindVenueLfg.visibility = View.VISIBLE
                        venueLayout.visibility = View.GONE
                    }
                    else{
                        btnJoinLfg.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    fun addFragmentWithTag(fragment: Fragment, tag: String) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_cont, fragment, tag)
            ?.commit()
    }

    private fun showAlertDialog(alertDialog: AlertDialog) {
        if (!alertDialog.isShowing) {
            alertDialog.show()
        }
    }
}