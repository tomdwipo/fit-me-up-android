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
import com.example.fitme_up.LoginActivity
import com.example.fitme_up.R
import com.example.fitme_up.RetrofitClient
import com.example.fitme_up.blueprint.DataResponse
import com.example.fitme_up.blueprint.RegisterVenueOwnerInput
import com.example.fitme_up.blueprint.ValidationError
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterVenueOwnerFragment3 : Fragment() {

    var venueNameEditText: EditText? = null
    var venueAddressEditText: EditText? = null
    var venuePhotosEditText: EditText? = null
    var venueDescEditText: EditText? = null

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

    private lateinit var registerBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register_venue_owner3, container, false)

        registerBtn = view.findViewById(R.id.btn_register_venue)

        venueNameEditText = view.findViewById(R.id.input_register_venue_name)
        venueAddressEditText = view.findViewById(R.id.input_register_venue_address)
        venuePhotosEditText = view.findViewById(R.id.input_register_venue_image)
        venueDescEditText = view.findViewById(R.id.input_register_venue_desc)

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

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments?.getParcelable("bundle", RegisterVenueOwnerInput::class.java)

        checkMonday.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                mondayStartinput.isEnabled = true
                mondayEndinput.isEnabled = true
            }else{
                mondayStartinput.isEnabled = false
                mondayEndinput.isEnabled = false
            }

        }
        checkTuesday.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                tuesdayStartinput.isEnabled = true
                tuesdayEndinput.isEnabled = true
            }else{
                tuesdayStartinput.isEnabled = false
                tuesdayEndinput.isEnabled = false
            }

        }
        checkWednesday.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                wednesdayStartinput.isEnabled = true
                wednesdayEndinput.isEnabled = true
            }else{
                wednesdayStartinput.isEnabled = false
                wednesdayEndinput.isEnabled = false
            }

        }
        checkThursday.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                thursdayStartinput.isEnabled = true
                thursdayEndinput.isEnabled = true
            }else{
                thursdayStartinput.isEnabled = false
                thursdayEndinput.isEnabled = false
            }

        }
        checkFriday.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                fridayStartinput.isEnabled = true
                fridayEndinput.isEnabled = true
            }else{
                fridayStartinput.isEnabled = false
                fridayEndinput.isEnabled = false
            }

        }
        checkSaturday.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                saturdayStartinput.isEnabled = true
                saturdayEndinput.isEnabled = true
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

            val fetch = RetrofitClient.instance.postRegisterVenueOwner(bundle)

            fetch.enqueue(object : Callback<DataResponse> {
                override fun onResponse(
                    call: Call<DataResponse>,
                    response: Response<DataResponse>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(activity, response.message(), Toast.LENGTH_SHORT).show()

                    } else{
                        val errorMsg =  response.errorBody()

                        val errorResponse = Gson().fromJson(errorMsg?.charStream(), ValidationError::class.java)
                        if (errorResponse.error.message.size == 1) {
                            if (errorResponse.error.message[0].field == "venuePhotos"
                            ) {
                                val bundle = Bundle()
                                bundle.putParcelable("bundle", bundle)

                                val frag = RegisterVenueOwnerFragment2()
                                frag.arguments = bundle

                                activity?.let {
                                    val intent = Intent(it, LoginActivity::class.java)
                                    startActivity(intent)
                                }
                            }
                        }
                        else{
                            for (i in 0..<errorResponse.error.message.size){
                                if (errorResponse.error.message[i].field.equals("fullName")){
                                    venueNameEditText?.error = errorResponse.error.message[i].message
                                }else if (errorResponse.error.message[i].field.equals("email")){
                                    venueAddressEditText?.error = errorResponse.error.message[i].message
                                }else if (errorResponse.error.message[i].field.equals("dateBirth")){
                                    venuePhotosEditText?.error = errorResponse.error.message[i].message
                                }else if (errorResponse.error.message[i].field.equals("password")){
                                    venueDescEditText?.error = errorResponse.error.message[i].message
                                }
                            }
                        }
                        Log.d("print", errorResponse.toString())
                    }
                }

                override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                    Log.d("print", t.toString())
                }
            })


        }
    }

}