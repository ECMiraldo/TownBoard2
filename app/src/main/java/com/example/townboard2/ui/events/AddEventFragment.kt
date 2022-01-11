package com.example.townboard2.ui.events

import android.app.Activity
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
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.util.*


class AddEventFragment : Fragment() {

    val REQUEST_IMAGE_CAPTURE = 1
    private var bitmap: Bitmap? = null
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
        val photoName = UUID.randomUUID().toString() + ".jpg"

        binding.addEventImageView.setOnClickListener {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                takePictureIntent.resolveActivity(requireContext().packageManager)?.also {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                    val storage = FirebaseStorage.getInstance()
                    val storageRef = storage.reference
                    val baos = ByteArrayOutputStream()
                    val photoImagesRef = storageRef.child("eventPhotos/${photoName}")
                    bitmap!!.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                    val data = baos.toByteArray()

                    var uploadTask = photoImagesRef.putBytes(data)
                    uploadTask.addOnFailureListener {
                        //error uploading photo
                        Toast.makeText(requireContext(), "error uploading photo", Toast.LENGTH_LONG).show()
                    }.addOnSuccessListener { taskSnapshot ->
                        Toast.makeText(requireContext(), "Photo upload with success", Toast.LENGTH_LONG)
                            .show()
                }
            }
        }

        binding.buttonDone.setOnClickListener() {
            val storage = FirebaseStorage.getInstance()
            val storageRef = storage.reference
            val photoImagesRef = storageRef.child("eventPhotos/${photoName}")

                val event = hashMapOf(
                    "name" to binding.nameEventsEdit.text.toString(),
                    "hora" to binding.horaEventEdit.text.toString(),
                    "data" to binding.dataEventEdit.text.toString(),
                    "local" to binding.localEventEdit.text.toString(),
                    "description" to binding.descriptionEventsEdit.text.toString(),
                    "photoName" to photoName
                )
                db.collection("city").document(cityName!!)
                    .collection("events").add(event).addOnSuccessListener {
                        findNavController().popBackStack()
                    }
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            binding.addEventImageView.setImageBitmap(imageBitmap)
            bitmap = imageBitmap
        }
    }

}