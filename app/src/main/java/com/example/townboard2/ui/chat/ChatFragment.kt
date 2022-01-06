package com.example.townboard2

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.townboard2.databinding.ActivityLoginBinding
import com.example.townboard2.databinding.FragmentChatBinding
import com.example.townboard2.ui.chat.ChatViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messageList: ArrayList<Message>
    private lateinit var auth: FirebaseAuth
    private lateinit var mContext: Context
    val db = Firebase.firestore

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val intent = super.getActivity()?.intent
        val cityName = intent?.getStringExtra("cityName")
        auth = FirebaseAuth.getInstance()
        messageList = arrayListOf<Message>()

        messageAdapter = MessageAdapter(mContext, messageList)
        binding.chatRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.chatRecyclerView.adapter = messageAdapter

        db.collection("city").document(cityName!!)
            .collection("messages").get()
            .addOnSuccessListener { result ->
                for (document in result.documents) {
                    val message = document.data?.let { it["message"] as String? }
                    val messageUID = document.data?.let { it["senderUID"] as String? }
                    messageList.add(Message(message!!, messageUID!!))
                }
                messageAdapter.notifyDataSetChanged()
            }.addOnFailureListener() { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
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