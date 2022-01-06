package com.example.townboard2.ui.chat

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.townboard2.databinding.FragmentChatBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messageList: ArrayList<Message>
    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        val root = binding.root
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cityName = getActivity()?.getIntent()?.getExtras()?.getString("cityName");

        auth = FirebaseAuth.getInstance()
        messageList = arrayListOf<Message>()

        messageAdapter = MessageAdapter(context, messageList)
        _binding?.chatRecyclerView?.layoutManager = LinearLayoutManager(context)
        _binding?.chatRecyclerView?.adapter = messageAdapter

        //reads chat messages from database
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



        _binding?.sendButton?.setOnClickListener {
            val message = _binding?.messageBox?.text.toString()
            val messageObj = Message(message, auth.currentUser?.uid.toString())
            db.collection("city").document(cityName!!)
                .collection("messages").add(messageObj)
            messageList.add(messageObj)
            messageAdapter.notifyDataSetChanged()
            _binding?.messageBox?.setText("")
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

