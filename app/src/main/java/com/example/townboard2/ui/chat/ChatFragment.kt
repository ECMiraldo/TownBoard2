package com.example.townboard2.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.townboard2.databinding.FragmentChatBinding

class ChatFragment : Fragment() {

  private lateinit var chatViewModel: ChatViewModel
  private var _binding: FragmentChatBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    chatViewModel = ViewModelProvider(this).get(ChatViewModel::class.java)

    _binding = FragmentChatBinding.inflate(inflater, container, false)

    val root: View = binding.root

    val textView: TextView = binding.textHome
    chatViewModel.text.observe(viewLifecycleOwner, Observer {
      textView.text = it
    })
    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}




//    package com.example.townboard2
//
//    import android.content.ContentValues
//    import android.content.Intent
//    import androidx.appcompat.app.AppCompatActivity
//    import android.os.Bundle
//    import android.util.Log
//    import android.view.MenuItem
//    import android.widget.EditText
//    import android.widget.ImageView
//    import androidx.recyclerview.widget.LinearLayoutManager
//    import androidx.recyclerview.widget.RecyclerView
//    import com.example.townboard2.databinding.ActivityChatBinding
//    import com.example.townboard2.databinding.ActivityLoginBinding
//    import com.google.android.material.bottomnavigation.BottomNavigationView
//    import com.google.firebase.auth.FirebaseAuth
//    import com.google.firebase.database.*
//    import com.google.firebase.firestore.ktx.firestore
//    import com.google.firebase.ktx.Firebase
//
//
//    //CREATEDATABASE
//
//
//    class ChatActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityChatBinding
//    private lateinit var messageAdapter : MessageAdapter
//    private lateinit var messageList : ArrayList<Message>
//private lateinit var auth : FirebaseAuth
//val db = Firebase.firestore
//
//override fun onCreate(savedInstanceState: Bundle?) {
//super.onCreate(savedInstanceState)
//setContentView(R.layout.activity_chat)
//
//binding = ActivityChatBinding.inflate(layoutInflater)
//setContentView(binding.root)
//
//val intent = intent
//val cityName = intent.getStringExtra("cityName")
//auth = FirebaseAuth.getInstance()
//messageList = arrayListOf<Message>()
//
//    messageAdapter = MessageAdapter(this, messageList)
//    binding.chatRecyclerView.layoutManager = LinearLayoutManager(this)
//    binding.chatRecyclerView.adapter = messageAdapter
//
//    //EX; CHAT.PVZ.MESSAGES
//    //    CHAT.PVZ.ADDS
//    //    CHAT.PVZ.EVENTS
//    //    PVZ = INTENT.GETSTRINGEXTRA(ROOM)
//
//    db.collection("city").document(cityName!!)
//    .collection("messages").get()
//    .addOnSuccessListener {  result ->
//    for (document in result.documents) {
//    val message = document.data?.let {it["message"] as String?}
//    val messageUID = document.data?.let {it["senderUID"] as String?}
//    messageList.add(Message(message!!, messageUID!!))
//    }
//    messageAdapter.notifyDataSetChanged()
//    }.addOnFailureListener(){
//    exception ->  Log.w(ContentValues.TAG, "Error getting documents.", exception)
//    }
//
//
//
//    binding.sendButton.setOnClickListener {
//    val message = binding.messageBox.text.toString()
//    val messageObj = Message(message, auth.currentUser?.uid.toString())
//    db.collection("city").document(cityName!!)
//    .collection("messages").add(messageObj)
//    messageList.add(messageObj)
//    messageAdapter.notifyDataSetChanged()
//    binding.messageBox.setText("")
//    }
//
//    }
//    }

//<?xml version="1.0" encoding="utf-8"?>
//<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
//xmlns:app="http://schemas.android.com/apk/res-auto"
//xmlns:tools="http://schemas.android.com/tools"
//android:layout_width="match_parent"
//android:layout_height="match_parent"
//tools:context=".ChatActivity">
//
//<com.google.android.material.bottomnavigation.BottomNavigationView
//android:id="@+id/bottom_navigation"
//android:layout_width="match_parent"
//android:layout_height="wrap_content"
//app:menu="@menu/bottom_navigation"
//android:layout_alignParentBottom="true" />
//
//
//<androidx.recyclerview.widget.RecyclerView
//android:id="@+id/chatRecyclerView"
//android:layout_width="match_parent"
//android:layout_height="0dp"
//android:layout_above="@+id/linearLayout"
//android:layout_alignParentTop="true"
//android:layout_marginTop="1dp"
//android:layout_marginBottom="0dp"
//app:layout_constraintBottom_toBottomOf="parent"
//app:layout_constraintEnd_toEndOf="parent"
//app:layout_constraintStart_toStartOf="parent"
//app:layout_constraintTop_toTopOf="parent" />
//
//<LinearLayout
//android:id="@+id/linearLayout"
//android:layout_width="match_parent"
//android:layout_height="wrap_content"
//android:layout_alignParentBottom="true"
//android:layout_marginBottom="269dp"
//android:orientation="horizontal"
//android:weightSum="188"
//app:layout_constraintBottom_toTopOf="@+id/chatRecyclerView"
//app:layout_constraintEnd_toEndOf="parent"
//app:layout_constraintStart_toStartOf="parent"
//app:layout_constraintTop_toTopOf="parent"/>
//
//<EditText
//android:id="@+id/messageBox"
//android:layout_width="wrap_content"
//android:layout_height="50dp"
//android:layout_above="@+id/bottom_navigation"
//android:layout_alignParentStart="true"
//android:layout_marginStart="10dp"
//android:layout_marginEnd="10dp"
//android:layout_marginBottom="1dp"
//android:layout_toStartOf="@+id/sendButton"
//android:hint="Message"
//android:paddingStart="10dp"
//app:layout_constraintBottom_toTopOf="@id/bottom_navigation"
//app:layout_constraintRight_toLeftOf="@+id/sendButton"
//app:layout_constraintStart_toStartOf="parent"
//tools:ignore="MissingConstraints" />
//
//<ImageView
//android:id="@+id/sendButton"
//android:layout_width="58dp"
//android:layout_height="58dp"
//android:layout_above="@+id/bottom_navigation"
//android:layout_alignParentEnd="true"
//android:layout_marginEnd="2dp"
//android:layout_marginBottom="-2dp"
//android:src="@android:drawable/ic_menu_send"
//app:layout_constraintBottom_toTopOf="@id/bottom_navigation"
//app:layout_constraintEnd_toEndOf="parent"
//app:srcCompat="@android:drawable/ic_menu_send"
//tools:ignore="MissingConstraints" />
//
//
//</RelativeLayout>