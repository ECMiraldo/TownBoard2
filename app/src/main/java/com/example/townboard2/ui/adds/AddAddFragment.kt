package com.example.townboard2.ui.adds

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.townboard2.R
import com.example.townboard2.databinding.FragmentAddAddBinding
import com.example.townboard2.databinding.FragmentAddsBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class AddAddFragment : Fragment() {

    private var _binding: FragmentAddAddBinding? = null
    private val binding get() = _binding!!
    val db = Firebase.firestore


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAddAddBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cityName = getActivity()?.getIntent()?.getExtras()?.getString("cityName");

        _binding?.addAddButtonDone?.setOnClickListener(){
            val add = hashMapOf(
                "titulo" to _binding?.addAddEditTitle?.text.toString(),
                "local"  to _binding?.addAddEditLocal?.text.toString(),
                "description"  to _binding?.addAddEditDescription?.text.toString(),
                "photoName" to "photo1"
            )
            db.collection("city").document(cityName!!)
                .collection("events").add(add).addOnSuccessListener {
                    findNavController().popBackStack()
                }

        }
    }


}