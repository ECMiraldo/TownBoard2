package com.example.townboard2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.*
import com.google.firebase.auth.FirebaseAuth

class UserAdapter(val context : Context, val userList : ArrayList<User>) :
    Adapter<UserAdapter.UserViewHolder>() {

    class  UserViewHolder(itemView: View) : ViewHolder(itemView) {
        val textName = itemView.findViewById<TextView>(R.id.nameSignUpTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.user_layout, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.textName.text = currentUser.name;

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ChatFragment::class.java)
            intent.putExtra("name", currentUser.name)
            intent.putExtra("uID", FirebaseAuth.getInstance().currentUser?.uid)
            //intent.putExtra("room", )  ///GET CHAT ROOM FROM SIGNUP
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

}