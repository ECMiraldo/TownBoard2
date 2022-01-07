package com.example.townboard2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList



class MessageAdapter(val context : Context?, val messageList : ArrayList<Message>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_RECEIVED = 1
    val ITEM_SENT = 2


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            val view : View = LayoutInflater.from(context).inflate(R.layout.receive_layout, parent, false)
            return ReceivedViewHolder(view)
        } else {
            val view : View = LayoutInflater.from(context).inflate(R.layout.send_layout, parent, false)
            return SentViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessage = messageList[position]
        if (holder.javaClass == SentViewHolder::class.java){
            //do stuff for sent view holder

            val viewHolder = holder as SentViewHolder
            holder.sentMessage.text = currentMessage.message
            holder.dataMessage.text = currentMessage.date.toString()
            holder.horaMessage.text = currentMessage.hour.toString()




        } else {
            //do stuff for receive view holder
            val viewHolder = holder as ReceivedViewHolder
            holder.receivedMessage.text = currentMessage.message
            holder.pessoaEnviou.text = currentMessage.senderName
            holder.dataMessageReceived.text = currentMessage.date
            holder.horaMessageReceived.text = currentMessage.hour.toString()
        }

    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]
        if (FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderUID)){
            return ITEM_SENT
        } else {
            return ITEM_RECEIVED
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    class SentViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val sentMessage = itemView.findViewById<TextView>(R.id.sendMessage)
        val dataMessage = itemView.findViewById<TextView>(R.id.dataMessage)
        val horaMessage = itemView.findViewById<TextView>(R.id.horaMessage)
    }
    class ReceivedViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val receivedMessage = itemView.findViewById<TextView>(R.id.messageText)
        val pessoaEnviou = itemView.findViewById<TextView>(R.id.userMessage)
        val dataMessageReceived = itemView.findViewById<TextView>(R.id.dataMessage)
        val horaMessageReceived =itemView.findViewById<TextView>(R.id.horaMessage)

    }


}