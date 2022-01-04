package com.example.townboard2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


//CREATEDATABASE


class ChatActivity : AppCompatActivity() {

    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var messageBox : EditText
    private lateinit var sendButton : ImageView
    private lateinit var messageAdapter : MessageAdapter
    private lateinit var messageList : ArrayList<Message>
    private lateinit var database : DatabaseReference

    var receiverRoom : String? = null
    var senderRoom : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val intent = Intent()
        val name = intent.getStringExtra("name")
        val receiverUID = intent.getStringExtra("uID")
        val senderUID = FirebaseAuth.getInstance().currentUser?.uid
        database =  FirebaseDatabase.getInstance().getReference()

        senderRoom = receiverUID + senderUID
        receiverRoom = senderUID + receiverUID



        supportActionBar?.title = name

        chatRecyclerView = findViewById(R.id.chatRecyclerView)
        messageBox = findViewById(R.id.MessageBox)
        sendButton = findViewById(R.id.sendButton)
        messageList = ArrayList()
        messageAdapter = MessageAdapter(this, messageList)

        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.adapter = messageAdapter

        //EX; CHAT.PVZ.MESSAGES
        //    CHAT.PVZ.ADDS
        //    CHAT.PVZ.EVENTS
        //    PVZ = INTENT.GETSTRINGEXTRA(ROOM)

        database.child("chats").child(senderRoom!!).   //WHAT ROOM HE WANTS TO GO
        child("messages").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                messageList.clear()

                for (postSnapshot in snapshot.children) {
                    val message = postSnapshot.getValue(Message::class.java)
                    messageList.add(message!!)
                }
                messageAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }


        })


        sendButton.setOnClickListener {
            val message = messageBox.text.toString()
            val messageObj = Message(message, senderUID!!)

            database.child("chats").child(senderRoom!!).child("messages").push()
                .setValue(messageObj).addOnSuccessListener {
                    database.child("chats").child(receiverRoom!!).child("messages").setValue(messageObj)

                }
            messageBox.setText("")
        }



    }
}