package com.example.townboard2.ui.adds

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation.createNavigateOnClickListener
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.townboard2.CityActivity
import com.example.townboard2.R
import com.example.townboard2.ui.events.EventsFragment
import com.google.firebase.storage.FirebaseStorage

class AddAdapter(val context: Context?, val addList: ArrayList<Add> ) : RecyclerView.Adapter<AddAdapter.AddViewHolder>() {



    class AddViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val add = itemView.findViewById<CardView>(R.id.addCardView)
        val card = itemView.findViewById<CardView>(R.id.addCardView).setOnClickListener {
            createNavigateOnClickListener(R.id.action_navigation_event_to_AddDetailFragment)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.row_adds, parent, false)
        return AddViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddViewHolder, position: Int) {
        val add = addList[position]
        holder.add.findViewById<TextView>(R.id.addTextViewTitle).text = add.name
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference
        val photoName = addList[position].photoName
        val photoImagesRef = storageRef.child("addPhotos/${photoName}")

        val ONE_MEGABYTE: Long = (1024 * 1024).toLong()
        photoImagesRef.getBytes(ONE_MEGABYTE).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
            holder.add.findViewById<ImageView>(R.id.addImageViewCard).setImageBitmap(bitmap)
            holder.add.findViewById<TextView>(R.id.addTextViewTitle).text = add.name
        }
            .addOnFailureListener{
                //TODO: CREATE A NEW IMAGE AND PUT IT AS DEFAULT HERE
            }
        holder.add.setOnClickListener {
            createNavigateOnClickListener("@id/nav_add_detail")
        }
    }

    override fun getItemCount(): Int {
        return addList.size
    }

}