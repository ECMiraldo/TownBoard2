package com.example.townboard2.ui.events

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.townboard2.LoginActivity
import com.example.townboard2.MainActivity
import com.example.townboard2.MyFirebaseMessagingService
import com.example.townboard2.R
import com.example.townboard2.databinding.FragmentEventsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class EventsFragment : Fragment() {

  private var _binding: FragmentEventsBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  private lateinit var eventAdapter: EventAdapter
  private lateinit var eventList: ArrayList<Event>
  private lateinit var auth: FirebaseAuth
  val db = Firebase.firestore




  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    _binding = FragmentEventsBinding.inflate(inflater, container, false)
    val root: View = binding.root
    return root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val cityName = getActivity()?.getIntent()?.getExtras()?.getString("cityName");

    auth = FirebaseAuth.getInstance()
    eventList = arrayListOf<Event>()
    eventAdapter = EventAdapter()
    _binding?.eventListView?.adapter = eventAdapter

    db.collection("city").document(cityName!!)
      .collection("events").get().addOnSuccessListener{ result ->
        for (document in result.documents) {
          val name = document.data?.let { it["name"] as String? }
          val data = document.data?.let { it["data"] as String? }
          val hora = document.data?.let { it["hora"] as String? }
          val local = document.data?.let { it["local"] as String? }
          val description = document.data?.let { it["description"] as String? }
          val photoName = document.data?.let { it["photoName"] as String?}
         eventList.add(Event(name,local,hora,data,description,photoName))


        }
        eventAdapter.notifyDataSetChanged()
      }.addOnFailureListener() { exception ->
        Log.w(ContentValues.TAG, "Error getting documents.", exception)
      }

    binding.buttonAddEvent.setOnClickListener {
      findNavController().navigate(R.id.action_navigation_event_to_AddEventFragment)
    }

  }





  override fun onResume() {
    super.onResume()
    eventAdapter.notifyDataSetChanged()


  }



  override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }


  inner class EventAdapter() : BaseAdapter(){
    override fun getCount(): Int {
      return eventList.count()
    }

    override fun getItem(position: Int): Any {
      return eventList[position]
    }

    override fun getItemId(position: Int): Long {
      return 0L
    }

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
      var rootView = layoutInflater.inflate(R.layout.row_events, viewGroup, false)
      val eventName = rootView.findViewById<TextView>(R.id.nameEvent)
      val eventDescription = rootView.findViewById<TextView>(R.id.descriptionEvent)
      val eventPhoto = rootView.findViewById<ImageView>(R.id.imageEvent)
      val eventDate = rootView.findViewById<TextView>(R.id.dataEvent)
      val eventTime = rootView.findViewById<TextView>(R.id.horaEvent)
      val eventPlace = rootView.findViewById<TextView>(R.id.localEvent)
      eventDescription.text = eventList[position].description
      eventName.text = eventList[position].name
      eventDate.text = eventList[position].data
      eventTime.text = eventList[position].hora
      eventPlace.text = eventList[position].local

      val storage = FirebaseStorage.getInstance()
      val storageRef = storage.reference
      val photoName = eventList[position].photoName
      val photoImagesRef = storageRef.child("eventPhotos/${photoName}")

      val ONE_MEGABYTE: Long = (1024 * 1024).toLong()
      photoImagesRef.getBytes(ONE_MEGABYTE).addOnSuccessListener {
        val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
        eventPhoto.setImageBitmap(bitmap)

      }.addOnFailureListener {
        //TODO: CREATE A NEW IMAGE AND PUT IT AS DEFAULT HERE
      }

      return rootView
    }


  }

}