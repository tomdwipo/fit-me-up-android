package com.example.fitme_up.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fitme_up.LoginActivity
import com.example.fitme_up.R
import com.example.fitme_up.RetrofitClient
import com.example.fitme_up.blueprint.DataResponse
import com.example.fitme_up.blueprint.RegisterCoachInput
import com.example.fitme_up.blueprint.ValidationError
import com.example.fitme_up.viewmodel.ViewModelSelectDay
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterCoachFragment3 : Fragment() {

//    private var checkboxListener: OnCheckboxSelectedListener? = null

    private lateinit var registerBtn: Button
//    private lateinit var sharedViewModel: ViewModelSelectSport
    private lateinit var sharedViewModel2: ViewModelSelectDay

    private lateinit var checkMonday: CheckBox
    private lateinit var checkTuesday: CheckBox
    private lateinit var checkWednesday: CheckBox
    private lateinit var checkThursday: CheckBox
    private lateinit var checkFriday: CheckBox
    private lateinit var checkSaturday: CheckBox
    private lateinit var checkSunday: CheckBox

    private lateinit var mondayStartinput: EditText
    private lateinit var mondayEndinput: EditText

    private lateinit var tuesdayStartinput: EditText
    private lateinit var tuesdayEndinput: EditText

    private lateinit var wednesdayStartinput: EditText
    private lateinit var wednesdayEndinput: EditText

    private lateinit var thursdayStartinput: EditText
    private lateinit var thursdayEndinput: EditText

    private lateinit var fridayStartinput: EditText
    private lateinit var fridayEndinput: EditText

    private lateinit var saturdayStartinput: EditText
    private lateinit var saturdayEndinput: EditText

    private lateinit var sundayStartinput: EditText
    private lateinit var sundayEndinput: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register_coach3, container, false)

        registerBtn = view.findViewById(R.id.registerButtonCoach3)

        checkMonday = view.findViewById(R.id.checkbox_monday)
        checkTuesday = view.findViewById(R.id.checkbox_tuesday)
        checkWednesday = view.findViewById(R.id.checkbox_wednesday)
        checkThursday = view.findViewById(R.id.checkbox_thursday)
        checkFriday = view.findViewById(R.id.checkbox_friday)
        checkSaturday = view.findViewById(R.id.checkbox_saturday)
        checkSunday = view.findViewById(R.id.checkbox_sunday)

        mondayStartinput = view.findViewById(R.id.input_monday_start_hour)
        mondayEndinput = view.findViewById(R.id.input_monday_end_hour)

        tuesdayStartinput = view.findViewById(R.id.input_tuesday_start_hour)
        tuesdayEndinput = view.findViewById(R.id.input_tuesday_end_hour)

        wednesdayStartinput = view.findViewById(R.id.input_wednesday_start_hour)
        wednesdayEndinput = view.findViewById(R.id.input_wednesday_end_hour)

        thursdayStartinput = view.findViewById(R.id.input_thursday_start_hour)
        thursdayEndinput = view.findViewById(R.id.input_thursday_end_hour)

        fridayStartinput = view.findViewById(R.id.input_friday_start_hour)
        fridayEndinput = view.findViewById(R.id.input_friday_end_hour)

        saturdayStartinput = view.findViewById(R.id.input_saturday_start_hour)
        saturdayEndinput = view.findViewById(R.id.input_saturday_end_hour)

        sundayStartinput = view.findViewById(R.id.input_sunday_start_hour)
        sundayEndinput = view.findViewById(R.id.input_sunday_end_hour)

        sharedViewModel2 = ViewModelProvider(requireActivity()).get(ViewModelSelectDay::class.java)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments?.getParcelable("bundle", RegisterCoachInput::class.java)

//        sharedViewModel.updateSelectedState(null)
        sharedViewModel2.clearSelectedDays()

        checkMonday.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                mondayStartinput.isEnabled = true
                mondayEndinput.isEnabled = true
//                bundle?.schedules?.scheduleDay = 1
            }else{
                mondayStartinput.isEnabled = false
                mondayEndinput.isEnabled = false
            }

        }
        checkTuesday.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                tuesdayStartinput.isEnabled = true
                tuesdayEndinput.isEnabled = true
//                bundle?.schedules?.scheduleDay = 2
            }else{
                tuesdayStartinput.isEnabled = false
                tuesdayEndinput.isEnabled = false
            }

        }
        checkWednesday.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                wednesdayStartinput.isEnabled = true
                wednesdayEndinput.isEnabled = true
//                bundle?.schedules?.scheduleDay = 3
            }else{
                wednesdayStartinput.isEnabled = false
                wednesdayEndinput.isEnabled = false
            }

        }
        checkThursday.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                thursdayStartinput.isEnabled = true
                thursdayEndinput.isEnabled = true
//                bundle?.schedules?.scheduleDay = 4
            }else{
                thursdayStartinput.isEnabled = false
                thursdayEndinput.isEnabled = false
            }

        }
        checkFriday.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                fridayStartinput.isEnabled = true
                fridayEndinput.isEnabled = true
//                bundle?.schedules?.scheduleDay = 5
            }else{
                fridayStartinput.isEnabled = false
                fridayEndinput.isEnabled = false
            }

        }
        checkSaturday.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                saturdayStartinput.isEnabled = true
                saturdayEndinput.isEnabled = true
//                bundle?.schedules?.scheduleDay = 6
            }else{
                saturdayStartinput.isEnabled = false
                saturdayEndinput.isEnabled = false
            }

        }
        checkSunday.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                sundayStartinput.isEnabled = true
                sundayEndinput.isEnabled = true
            }else{
                sundayStartinput.isEnabled = false
                sundayEndinput.isEnabled = false
            }

        }

        registerBtn.setOnClickListener() {

            if(checkMonday.isChecked || checkTuesday.isChecked || checkWednesday.isChecked ||
                checkThursday.isChecked || checkFriday.isChecked || checkSaturday.isChecked || checkSunday.isChecked){

                val fetch = RetrofitClient.instance.postRegisterCoach(bundle)

                fetch.enqueue(object : Callback<DataResponse> {
                    override fun onResponse(
                        call: Call<DataResponse>,
                        response: Response<DataResponse>
                    ) {
                        println("TESTING " + response.body().toString())
                        if (response.isSuccessful){
                            Toast.makeText(activity, response.message(), Toast.LENGTH_SHORT).show()

                        } else{
                            val errorMsg =  response.errorBody()

                            val errorResponse = Gson().fromJson(errorMsg?.charStream(), ValidationError::class.java)
                            if (errorResponse.error.message.isEmpty()) {
                                val bundle = Bundle()
                                bundle.putParcelable("bundle", bundle)

                                val frag = RegisterCoachFragment5()
                                frag.arguments = bundle

                                activity?.let {
                                    val intent = Intent(it, LoginActivity::class.java)
                                    startActivity(intent)
                                }
                            }
                            else{
                                Log.d("print", errorResponse.toString())
                            }
                        }
                    }

                    override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                        Log.d("print", t.toString())
                    }
                })

            }
        }
    }

    private fun updateSelectedDaysList(day: String, isChecked: Boolean) {
        if (isChecked) {
            // Add the checked day to the list
            sharedViewModel2.addSelectedDay(day)
        } else {
            // Remove the unchecked day from the list
            sharedViewModel2.removeSelectedDay(day)
        }
    }
}
