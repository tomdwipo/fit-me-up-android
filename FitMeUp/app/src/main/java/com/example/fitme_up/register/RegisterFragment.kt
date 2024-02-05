package com.example.fitme_up.register

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.example.fitme_up.R
import com.example.fitme_up.feature_login.presentation.viewmodels.LoginViewModel
import com.example.fitme_up.feature_register_venue.data.service.request.RegisterVenueRequest
import com.example.fitme_up.feature_register_venue.presentation.viewmodels.RegisterVenueViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private val viewModel: RegisterVenueViewModel by viewModels()

    lateinit var roleUserButton: Button
    lateinit var roleCoachButton: Button
    lateinit var roleOwnerButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register_role, container, false)

        roleUserButton = view.findViewById(R.id.btnRoleUser)
        roleCoachButton = view.findViewById(R.id.btnRoleCoach)
        roleOwnerButton = view.findViewById(R.id.btnRoleOwner)

        roleUserButton.setOnClickListener() {
            val userFrag = RegisterUserFragment()
            val tran = fragmentManager?.beginTransaction()
            tran?.replace(R.id.frame_register_cont, userFrag)?.commit()
            tran?.addToBackStack(null)
        }
        roleCoachButton.setOnClickListener() {
            val coachFrag = RegisterCoachFragment()
            val tran = fragmentManager?.beginTransaction()
            tran?.replace(R.id.frame_register_cont, coachFrag)?.commit()
            tran?.addToBackStack(null)
        }
        roleOwnerButton.setOnClickListener() {
            //TODO: create function for pick image from gallery or camera

            //TODO: response pick image change to FILE type

            //TODO: EXAMPLE how to upload
           viewModel.viewModelScope.launch(Dispatchers.IO) {
               val drawableId = R.drawable.image_1
               val bitmap = BitmapFactory.decodeResource(resources, drawableId)
               val byteArrayOutputStream = ByteArrayOutputStream()
               bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)

               //TODO: add your data to RegisterVenueRequest param
               val request = RegisterVenueRequest()
               val file = ArrayList<File>()
               file.add(saveByteArrayOutputStreamToFile(byteArrayOutputStream, requireContext(), "testing.png"))

               val result =  viewModel.addVenue(request, file.toList())
                println("result $result")
            }

            //////
//            val coachFrag = RegisterVenueOwnerFragment()
//            val tran = fragmentManager?.beginTransaction()
//            tran?.replace(R.id.frame_register_cont, coachFrag)?.commit()
//            tran?.addToBackStack(null)
        }
        return view
    }

    private fun saveByteArrayOutputStreamToFile(byteArrayOutputStream: ByteArrayOutputStream, context: Context, fileName: String): File {
        // Step 1: Obtain the byte array
        val byteArray = byteArrayOutputStream.toByteArray()

        // Step 2: Create a file in the app's internal storage
        val file = File(context.filesDir, fileName)

        // Step 3: Write the byte array to the file
        FileOutputStream(file).use { output ->
            output.write(byteArray)
        }

        // Return the File object for further use
        return file
    }
    //
}