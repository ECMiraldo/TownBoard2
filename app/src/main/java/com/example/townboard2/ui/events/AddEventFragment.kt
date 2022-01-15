package com.example.townboard2.ui.events

import android.R.attr
import android.app.Activity.RESULT_OK
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
import android.net.Uri
import android.provider.MediaStore.*
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*
import androidx.core.app.ActivityCompat.startActivityForResult
import android.R.attr.data
import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.townboard2.MainActivity
import com.example.townboard2.MyFirebaseMessagingService


class AddEventFragment : Fragment() {

    val REQUEST_IMAGE_CAPTURE = 1
    private var bitmap: Bitmap? = null
    private var _binding: FragmentAddEventBinding? = null
    private val binding get() = _binding!!
    val db = Firebase.firestore
    lateinit var imageUri : Uri




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cityName = getActivity()?.getIntent()?.getExtras()?.getString("cityName");


        binding.addEventImageView.setOnClickListener {

            selectImage()


        }

        binding.buttonDone.setOnClickListener() {
            val photoName = "$imageUri.jpg"
            val storage = FirebaseStorage.getInstance()
            val storageRef = storage.reference
            val photoImagesRef = storageRef.child("eventPhotos/${photoName}")
            photoImagesRef.putFile(imageUri)

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

                Toast.makeText(requireContext(), "Photo upload with success", Toast.LENGTH_LONG)
                    .show()

                   sendNotification("novo evento")
            }



        }

    private fun sendNotification(messageBody: String) {
        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(requireContext(), 0 /* Request code */, intent,
            PendingIntent.FLAG_ONE_SHOT)

        val channelId = getString(R.string.default_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(requireContext(), channelId)
            .setSmallIcon(R.drawable.ic_stat_ic_notification)
            .setContentTitle("Novo evento")
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)


        val notificationManager = requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            binding.addEventImageView.setImageURI(data?.data)
            imageUri = data?.data!!
        }
    }

    private fun selectImage(){
        val intent = Intent(Intent.ACTION_PICK, Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)

    }
}