package com.example.townboard2.ui.events

import android.content.ContentValues
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.townboard2.Event
import com.example.townboard2.Message
import com.example.townboard2.MessageAdapter
import com.example.townboard2.R
import com.example.townboard2.databinding.FragmentEventsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class EventsFragment : Fragment() {
  var evento1 = Event("name1", "local1", "hora1","15/02/2022","aaaaaaaaaaaaaaaaaaaaaaaaaaa", "4c9j7m3u-900.jpg")
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
    eventList.add(evento1)
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


  }





override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


  inner class EventAdapter : BaseAdapter(){
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

      }

      return rootView
    }


  }

}