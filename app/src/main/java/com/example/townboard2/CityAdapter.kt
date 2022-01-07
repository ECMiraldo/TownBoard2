package com.example.townboard2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView.*

class CityAdapter(val context : Context, val cityList : ArrayList<City>, val userName: String) :
    Adapter<CityAdapter.CityViewHolder>() {

    class CityViewHolder(itemView: View) : ViewHolder(itemView) {
        val cityName = itemView.findViewById<TextView>(R.id.city_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.city_layout, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val currentCity = cityList[position]
        holder.cityName.text = currentCity.name

        holder.itemView.setOnClickListener {
            val intent = Intent(context, CityActivity::class.java)
            intent.putExtra("cityName", currentCity.name)
            //intent.putExtra("room", )  ///GET CHAT ROOM FROM SIGNUP
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

}


