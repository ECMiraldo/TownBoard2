package com.example.townboard2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class addEventsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_events_acitivity)

        val editTextName = findViewById<EditText>(R.id.nameEventsEdit)
        val editTextlocal = findViewById<EditText>(R.id.localEventEdit)
        val editTextdata = findViewById<EditText>(R.id.dataEventEdit)
        val editTexthora = findViewById<EditText>(R.id.horaEventEdit)
        val editTextDescription = findViewById<EditText>(R.id.descriptionEventsEdit)

        val buttonDone = findViewById<Button>(R.id.buttonDone)

        buttonDone.setOnClickListener {
            val intent = intent
            intent.putExtra("event_name", editTextName.text.toString())
            intent.putExtra("event_local", editTextlocal.text.toString())
            intent.putExtra("event_data", editTextdata.text.toString())
            intent.putExtra("event_hora", editTexthora.text.toString())
            intent.putExtra("plant_description", editTextDescription.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }

    }
}