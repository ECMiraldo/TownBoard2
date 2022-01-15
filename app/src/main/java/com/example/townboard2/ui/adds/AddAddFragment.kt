package com.example.townboard2.ui.adds

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
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
import com.google.firebase.storage.FirebaseStorage


class AddAddFragment : Fragment() {

    val REQUEST_IMAGE_CAPTURE = 1
    private var _binding: FragmentAddAddBinding? = null
    private val binding get() = _binding!!
    val db = Firebase.firestore
    lateinit var imageUri : Uri


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAddAddBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cityName = getActivity()?.getIntent()?.getExtras()?.getString("cityName");

        binding.addAddImageView.setOnClickListener{
            selectImage()
        }
        _binding?.addAddButtonDone?.setOnClickListener(){
            val photoName = "$imageUri.jpg"
            val storage = FirebaseStorage.getInstance()
            val storageRef = storage.reference
            val photoImagesRef = storageRef.child("addPhotos/${photoName}")
            photoImagesRef.putFile(imageUri)


            val add = hashMapOf(
                "titulo" to _binding?.addAddEditTitle?.text.toString(),
                "local"  to _binding?.addAddEditLocal?.text.toString(),
                "description"  to _binding?.addAddEditDescription?.text.toString(),
                "photoName" to photoName
            )
            db.collection("city").document(cityName!!)
                .collection("adds").add(add).addOnSuccessListener {
                    findNavController().popBackStack()
                }

        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            binding.addAddImageView.setImageURI(data?.data)
            imageUri = data?.data!!
        }
    }

    private fun selectImage(){
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)

    }

}