package com.example.townboard2.ui.adds

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.townboard2.R
import com.example.townboard2.databinding.FragmentAddDetailBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage


class AddDetailFragment : Fragment() {

    private var _binding: FragmentAddDetailBinding? = null
    private val binding get() = _binding!!
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAddDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    //get stuff from intent? or from database?
    //just set text and do popbackstack on navigation
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addsdescriptionDetail.text = arguments?.getString("descricao")
        binding.editTextAddLocal.text = arguments?.getString("local")
        binding.addTitleTextView.text = arguments?.getString("titulo")
        var photoName = arguments?.getString("photoName")

        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference
        val photoImagesRef = storageRef.child("addPhotos/${photoName}")
        val ONE_MEGABYTE: Long = (1024 * 1024).toLong()
        photoImagesRef.getBytes(ONE_MEGABYTE).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
            binding.imageView10.setImageBitmap(bitmap)
        }



    }


}