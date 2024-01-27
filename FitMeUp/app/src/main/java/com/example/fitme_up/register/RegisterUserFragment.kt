package com.example.fitme_up.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import androidx.lifecycle.ViewModelProvider

import com.example.fitme_up.R
import com.example.fitme_up.RetrofitClient
import com.example.fitme_up.blueprint.ValidationError
import com.example.fitme_up.blueprint.DataResponse

import com.example.fitme_up.blueprint.RegisterUserInput
import com.example.fitme_up.viewmodel.ViewModelList
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterUserFragment : Fragment() {

    var nameEditText: EditText? = null
    var emailEditText: EditText? = null
    var passwordEditText: EditText? = null
    var confirmPasswordEditText: EditText? = null
    var dobEditText: EditText? = null
    lateinit var registerButton: Button

    //retrofit
    private lateinit var viewModel: ViewModelList

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register_user, container, false)

        nameEditText = view.findViewById(R.id.input_register_name)
        emailEditText = view.findViewById(R.id.input_register_email)
        passwordEditText = view.findViewById(R.id.input_register_password)
        confirmPasswordEditText = view.findViewById(R.id.input_register_confirm_password)
        dobEditText = view.findViewById(R.id.input_register_dob)
        registerButton = view.findViewById(R.id.registerButtonUser)

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //RETROFIT
        viewModel = ViewModelProvider(this).get(ViewModelList::class.java)

        registerButton.setOnClickListener {

            val register = RegisterUserInput(
                nameEditText?.text.toString(),
                emailEditText?.text.toString(),
                passwordEditText?.text.toString(),
                confirmPasswordEditText?.text.toString(),
                dobEditText?.text.toString(),
                arrayListOf(),
                null
            )

            val fetch = RetrofitClient.instance.postRegisterUser(register)

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
                        if (errorResponse.error.message.size == 2){
                            if (errorResponse.error.message[0].field.equals("favSportIds") && errorResponse.error.message[1].field.equals("domicileId")){
                                val bundle = Bundle()
                                bundle.putParcelable("bundle", register)

                                val frag = RegisterUserFragment2()
                                frag.arguments = bundle

                                val tran = fragmentManager?.beginTransaction()
                                tran?.replace(R.id.frame_register_cont, frag)
                                tran?.commit()
                                tran?.addToBackStack(null)
                            }
                        }
                        else{
                            for (i in 0..<errorResponse.error.message.size){
                                if (errorResponse.error.message[i].field.equals("fullName")){
                                    nameEditText?.error = errorResponse.error.message[i].message
                                }else if (errorResponse.error.message[i].field.equals("email")){
                                    emailEditText?.error = errorResponse.error.message[i].message
                                }else if (errorResponse.error.message[i].field.equals("dateBirth")){
                                    dobEditText?.error = errorResponse.error.message[i].message
                                }else if (errorResponse.error.message[i].field.equals("password")){
                                    passwordEditText?.error = errorResponse.error.message[i].message
                                }else if (errorResponse.error.message[i].field.equals("password_confirmation")){
                                    confirmPasswordEditText?.error = errorResponse.error.message[i].message
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

//    private fun isValidEmail(email: String): Boolean {
//        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
//    }
//
//    private fun checkDOB(dob: String): Boolean {
//        val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
//
//        return try {
//            LocalDate.parse(dob, dateFormatter)
//            true
//        } catch (e: Exception) {
//            false
//        }
//    }

//    private fun checkField(): Boolean {
//
//        val date = dobEditText?.text.toString()
//
//        loop@ for (i in 0..2) {
//            if (nameEditText?.text.toString().isEmpty()) {
//                nameEditText?.error = "error"
//                nameEditText?.requestFocus()
//                return false
//            }
//            if (emailEditText?.text.toString().isEmpty()) {
//                emailEditText?.error = "Email cannot be empty!"
//                emailEditText?.requestFocus()
//                return false
//            }
//            if (isValidEmail(emailEditText?.text.toString())) {
//
//            }
//            if (!isValidEmail((emailEditText?.text.toString()))) {
//                emailEditText?.error = "Incorrect email format!"
//                emailEditText?.requestFocus()
//                return false
//            }
//            if (passwordEditText?.text.toString().isEmpty()) {
//                passwordEditText?.error = "Password cannot be empty!"
//                passwordEditText?.requestFocus()
//                return false
//            }
//            if (confirmPasswordEditText?.text.toString().isEmpty()) {
//                confirmPasswordEditText?.error = "Please confirm your password"
//                confirmPasswordEditText?.requestFocus()
//                return false
//            }
//            if (passwordEditText?.text.toString() != confirmPasswordEditText?.text.toString()) {
//                confirmPasswordEditText?.error = "Check your password"
//                confirmPasswordEditText?.requestFocus()
//                return false
//            }
//            if (dobEditText?.text.toString().isEmpty()) {
//                dobEditText?.error = "DOB cannot be empty!"
//                dobEditText?.requestFocus()
//                return false
//            }
//            if (checkDOB(date)) {
//
//            }
//            if (!checkDOB(date)) {
//                dobEditText?.error = "Date format must be dd/MM/yyyy"
//                dobEditText?.requestFocus()
//                return false
//            }
//        }
//        return true
//    }

}