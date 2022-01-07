package com.example.townboard2.ui.events

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.townboard2.R
import com.example.townboard2.databinding.FragmentAddEventBinding
import com.example.townboard2.databinding.FragmentEventsBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import android.graphics.Bitmap
import androidx.navigation.fragment.findNavController


class AddEventFragment : Fragment() {

    private var _binding: FragmentAddEventBinding? = null
    private val binding get() = _binding!!
    val db = Firebase.firestore


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cityName = getActivity()?.getIntent()?.getExtras()?.getString("cityName");

        _binding?.buttonDone?.setOnClickListener(){
            val event = hashMapOf(
                "name" to _binding?.nameEventsEdit?.text.toString(),
                "hora"  to _binding?.horaEventEdit?.text.toString(),
                "data"  to _binding?.dataEventEdit?.text.toString(),
                "local" to _binding?.localEventEdit?.text.toString(),
                "description" to _binding?.descriptionEventsEdit?.text.toString(),
                "photoName" to "photo1"
            )
            db.collection("city").document(cityName!!)
                .collection("events").add(event).addOnSuccessListener {
                    findNavController().popBackStack()
                }

        }


    }


}