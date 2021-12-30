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

class activity_main_public : AppCompatActivity() {
    var publicsList = arrayListOf(
        public( "bigodesCafe", "Barcelos", "description1","descricaoLonga"),
        public("name2", "Arcozelo", "description2","Outradescricao"),
        public("name3", "Barcelos Centro", "description3","MaisUmadescricao")
    )

    lateinit var listviewPublic : ListView
    var adapter : PublicAdapter?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listviewPublic = findViewById(R.id.listviewPlants)
        adapter = PublicAdapter()
        listviewPublic.adapter = adapter

        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        buttonAdd.setOnClickListener {


            val intent = Intent(this@activity_main_public, addPublicActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_PUBLIC)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_PUBLIC && resultCode == RESULT_OK) {
            data?.let{
                val name = it.getStringExtra("public_name")?:""
                val local = it.getStringExtra("public_local")?:""
                val minidescription =it.getStringExtra("public_minidescription")?:""
                val description = it.getStringExtra("public_description")?:""

                val public = public(name,
                    local,
                    minidescription,
                    description)
                publicsList.add(public)
                adapter?.notifyDataSetChanged()
            }
        }
    }

    inner class PublicAdapter : BaseAdapter() {

        override fun getCount(): Int {
            return publicsList.size
        }

        override fun getItem(position: Int): Any {
            return publicsList[position]
        }

        override fun getItemId(p0: Int): Long {
            return 0L
        }

        override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
            var rootView = layoutInflater.inflate(R.layout.row_public,viewGroup,false)
            val textViewName = rootView.findViewById<TextView>(R.id.name)
            val textViewMiniDescription = rootView.findViewById<TextView>(R.id.minidescription)

            textViewName.text = publicsList[position].name
            textViewMiniDescription.text = publicsList[position].minidescription

            rootView.isClickable = true
            rootView.setOnClickListener {
                val intent = Intent(this@activity_main_public, PublicDetailActivity::class.java)
                intent.putExtra("public_name", publicsList[position].name)
                intent.putExtra("public_local", publicsList[position].local)
                intent.putExtra("public_description", publicsList[position].description)
                startActivity(intent)
            }

            return rootView
        }
    }
    companion object {
        const val REQUEST_CODE_ADD_PUBLIC = 1001
    }
}
