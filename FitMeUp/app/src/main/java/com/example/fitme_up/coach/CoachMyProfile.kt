package com.example.fitme_up.coach

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.fitme_up.R

class CoachMyProfile : Fragment() {

//    private var checkboxListener: OnCheckboxSelectedListener? = null

    private lateinit var textMonday: LinearLayout
    private lateinit var textTuesday: LinearLayout
    private lateinit var textWednesday: LinearLayout
    private lateinit var textThursday: LinearLayout
    private lateinit var textFriday: LinearLayout
    private lateinit var textSaturday: LinearLayout
    private lateinit var textSunday: LinearLayout

    private lateinit var editProfileBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_coach_my_profile, container, false)

        textMonday = view.findViewById(R.id.layout_monday)
        textTuesday = view.findViewById(R.id.layout_tuesday)
        textWednesday = view.findViewById(R.id.layout_wednesday)
        textThursday = view.findViewById(R.id.layout_thursday)
        textFriday = view.findViewById(R.id.layout_friday)
        textSaturday = view.findViewById(R.id.layout_saturday)
        textSunday = view.findViewById(R.id.layout_sunday)

        editProfileBtn = view.findViewById(R.id.btn_edit_profile)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editProfileBtn.setOnClickListener(){
            val frag = CoachEditProfile()
            val tran = fragmentManager?.beginTransaction()
            tran?.addToBackStack(null)
            tran?.replace(R.id.fragment_cont, frag)?.commit()
        }

    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        checkboxListener = context as? OnCheckboxSelectedListener
//    }

//    override fun onCheckboxSelected(checkboxId: Int, isSelected: Boolean) {
//        when (checkboxId) {
//            R.id.checkbox_monday -> {
//                if (isSelected) {
//                    textMonday.visibility = View.VISIBLE
//                } else {
//                    textMonday.visibility = View.GONE
//                }
//            }
//
//            R.id.checkbox_tuesday -> {
//                if (isSelected) {
//                    textTuesday.visibility = View.VISIBLE
//                } else {
//                    textTuesday.visibility = View.GONE
//                }
//            }
//
//            R.id.checkbox_wednesday -> {
//                if (isSelected) {
//                    textWednesday.visibility = View.VISIBLE
//                } else {
//                    textWednesday.visibility = View.GONE
//                }
//            }
//
//            R.id.checkbox_thursday -> {
//                if (isSelected) {
//                    textThursday.visibility = View.VISIBLE
//                } else {
//                    textThursday.visibility = View.GONE
//                }
//            }
//
//            R.id.checkbox_friday -> {
//                if (isSelected) {
//                    textFriday.visibility = View.VISIBLE
//                } else {
//                    textFriday.visibility = View.GONE
//                }
//            }
//
//            R.id.checkbox_saturday -> {
//                if (isSelected) {
//                    textSaturday.visibility = View.VISIBLE
//                } else {
//                    textSaturday.visibility = View.GONE
//                }
//            }
//
//            R.id.checkbox_sunday -> {
//                if (isSelected) {
//                    textSunday.visibility = View.VISIBLE
//                } else {
//                    textSunday.visibility = View.GONE
//                }
//            }
//
//            // Add more days as needed
//        }
//    }


}