package com.example.townboard2

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage

class AddAdapter(val context : Context, val addList : ArrayList<Add> ) : RecyclerView.Adapter<AddAdapter.AddViewHolder>() {



    class AddViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val add = itemView.findViewById<CardView>(R.id.addCardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.adds_card_view, parent, false)
        return AddAdapter.AddViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddViewHolder, position: Int) {
        val add = addList[position]
        holder.add.findViewById<TextView>(R.id.addTextViewTitle).text = add.description

        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference
        val photoName = addList[position].photoName
        val photoImagesRef = storageRef.child("addPhotos/${photoName}")

        val ONE_MEGABYTE: Long = (1024 * 1024).toLong()
        photoImagesRef.getBytes(ONE_MEGABYTE).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
            holder.add.findViewById<ImageView>(R.id.addImageViewCard).setImageBitmap(bitmap)
        }
            .addOnFailureListener{
                //TODO: CREATE A NEW IMAGE AND PUT IT AS DEFAULT HERE
            }

    }

    override fun getItemCount(): Int {
        return addList.size
    }

}