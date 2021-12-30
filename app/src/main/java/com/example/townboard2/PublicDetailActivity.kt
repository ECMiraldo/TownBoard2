package com.example.townboard2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView

class PublicDetailActivity : AppCompatActivity() {
    var name : String? = null
    var description : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_public_detail)

        name = intent.getStringExtra("public_name")?:""
        val localDetail   : String = intent.getStringExtra("public_local")?:""
        description = intent.getStringExtra("public_description")?:""

        val textViewName           = findViewById<TextView>(R.id.nameDetail)
        val textViewLocal           =findViewById<TextView>(R.id.local)
        val textViewDescription    = findViewById<TextView>(R.id.descriptionDetail)

        textViewName.text = name
        textViewLocal.text = localDetail
        textViewDescription.text = description

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }
}