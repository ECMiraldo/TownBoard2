package com.example.townboard2

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.townboard2.databinding.ActivityChatBinding
import com.example.townboard2.databinding.ActivityLoginBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


//CREATEDATABASE


class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private lateinit var messageAdapter : MessageAdapter
    private lateinit var messageList : ArrayList<Message>
    private lateinit var auth : FirebaseAuth
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val cityName = intent.getStringExtra("cityName")
        auth = FirebaseAuth.getInstance()
        messageList = arrayListOf<Message>()

        messageAdapter = MessageAdapter(this, messageList)
        binding.chatRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.chatRecyclerView.adapter = messageAdapter

        //EX; CHAT.PVZ.MESSAGES
        //    CHAT.PVZ.ADDS
        //    CHAT.PVZ.EVENTS
        //    PVZ = INTENT.GETSTRINGEXTRA(ROOM)

        db.collection("city").document(cityName!!)
            .collection("messages").get()
            .addOnSuccessListener {  result ->
                for (document in result.documents) {
                    val message = document.data?.let {it["message"] as String?}
                    val messageUID = document.data?.let {it["senderUID"] as String?}
                    messageList.add(Message(message!!, messageUID!!))
                }
                messageAdapter.notifyDataSetChanged()
            }.addOnFailureListener(){
                    exception ->  Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }



        binding.sendButton.setOnClickListener {
            val message = binding.messageBox.text.toString()
            val messageObj = Message(message, auth.currentUser?.uid.toString())
            db.collection("city").document(cityName!!)
                .collection("messages").add(messageObj)
            messageList.add(messageObj)
            messageAdapter.notifyDataSetChanged()
            binding.messageBox.setText("")
        }



    }
}
