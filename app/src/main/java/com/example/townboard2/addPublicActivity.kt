package com.example.townboard2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class addPublicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_public)

        val editTextName = findViewById<EditText>(R.id.editName)
        val editTextLocal = findViewById<EditText>(R.id.editLocal)
        val editTextminiDescription = findViewById<EditText>(R.id.minieditDescription)
        val editTextDescription = findViewById<EditText>(R.id.editDescription)
        val buttonDone = findViewById<Button>(R.id.buttonDone)

        buttonDone.setOnClickListener {
            val intent = intent
            intent.putExtra("public_name", editTextName.text.toString())
            intent.putExtra("public_local", editTextLocal.text.toString())
            intent.putExtra("public_minidescription", editTextminiDescription.text.toString())
            intent.putExtra("public_description",editTextDescription.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }

    }
}