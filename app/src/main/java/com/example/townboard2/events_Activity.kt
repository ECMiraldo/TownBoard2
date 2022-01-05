package com.example.townboard2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

class events_Activity : AppCompatActivity() {
    var events = arrayListOf(
        events("name1", "latinName1", "description1","15/02/2022","aaaaaaaaaaaaaaaaaaaaaaaaaaa"),
        events("name2", "latinName2", "description2","18/10/2023","ceverja gratis pra todo mundo"),
        events("name3", "latinName3", "description3","01/01/2022","socorro")
    )

    lateinit var listviewPlants : ListView
    var adapter : PlantsAdapter?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listviewPlants = findViewById(R.id.listviewEvents)
        adapter = PlantsAdapter()
        listviewPlants.adapter = adapter

        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        buttonAdd.setOnClickListener {


            val intent = Intent(this@events_Activity, AddEventsActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_PLANT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_PLANT && resultCode == RESULT_OK) {
            data?.let{
                val name = it.getStringExtra("event_name")?:""
                val local = it.getStringExtra("event_local")?:""
                val data = it.getStringExtra("event_data")?:""
                val hora = it.getStringExtra("event_hora")?:""
                val description = it.getStringExtra("plant_description")?:""

                val event = events(name,
                    local,
                    data,hora,description)
                events.add(event)
                adapter?.notifyDataSetChanged()
            }
        }
    }

    inner class PlantsAdapter : BaseAdapter() {

        override fun getCount(): Int {
            return events.size
        }

        override fun getItem(position: Int): Any {
            return events[position]
        }

        override fun getItemId(p0: Int): Long {
            return 0L
        }

        override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
            var rootView = layoutInflater.inflate(R.layout.row_events,viewGroup,false)
            val textViewName        = rootView.findViewById<TextView>(R.id.nameEvents)
            val textViewlocal       = rootView.findViewById<TextView>(R.id.localEvent)
            val textViewdata        = rootView.findViewById<TextView>(R.id.dataEvent)
            val textViewhora        = rootView.findViewById<TextView>(R.id.horaEvent)
            val textViewdescription = rootView.findViewById<TextView>(R.id.descriptionEvents)

            textViewName.text = events[position].name
            textViewlocal.text =events[position].local
            textViewdata.text = events[position].data
            textViewhora.text =events[position].hora
            textViewdescription.text =events[position].description

            return rootView
        }
    }
    companion object {
        const val REQUEST_CODE_ADD_PLANT = 1001
    }
}