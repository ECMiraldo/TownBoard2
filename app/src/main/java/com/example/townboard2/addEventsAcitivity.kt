package com.example.townboard2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText


//<?xml version="1.0" encoding="utf-8"?>
//<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
//xmlns:app="http://schemas.android.com/apk/res-auto"
//xmlns:tools="http://schemas.android.com/tools"
//android:orientation="vertical"
//android:layout_width="match_parent"
//android:layout_height="match_parent">
//
//<TextView
//android:id="@+id/TituloActivity"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_gravity="center"
//android:text="Eventos"
//android:textSize="40sp"
//android:textColor="@color/purple_500" />
//
//<RelativeLayout
//android:layout_width="match_parent"
//android:layout_height="wrap_content"
//android:layout_marginTop="16dp">
//
//<LinearLayout
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_marginVertical="10dp"
//android:orientation="horizontal">
//
//<ImageView
//android:id="@+id/logo_pic"
//android:layout_width="116dp"
//android:layout_height="match_parent"
//android:layout_marginStart="20dp"
//android:src="@color/black" />
//
//<LinearLayout
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_marginStart="5dp"
//android:orientation="vertical">
//
//<EditText
//android:id="@+id/nameEventsEdit"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:hint="Exemplo"
//android:textColor="@color/black"
//android:textSize="26sp"
//android:textStyle="bold" />
//
//<EditText
//android:id="@+id/localEventEdit"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_marginTop="7dp"
//android:hint="Barcelos"
//android:textStyle="italic" />
//
//<EditText
//android:id="@+id/dataEventEdit"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_marginTop="7dp"
//android:inputType="date"
//android:hint="15/05/2022"
//android:textStyle="italic" />
//
//<EditText
//android:id="@+id/horaEventEdit"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_marginTop="7dp"
//android:inputType="time"
//android:hint="15:00"
//android:textStyle="bold" />
//
//
//<EditText
//android:id="@+id/descriptionEventsEdit"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_marginTop="7dp"
//android:hint="pequena descricao"
//android:textStyle="normal" />
//
//
//
//
//</LinearLayout>
//
//</LinearLayout>
//
//
//</RelativeLayout>
//
//<Button
//android:id="@+id/buttonDone"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_marginEnd="8dp"
//android:layout_marginBottom="8dp"
//android:layout_marginStart="300dp"
//android:text="Done" />
//
//</LinearLayout>
//class AddEventsActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.add_events_acitivity)
//
//        val editTextName = findViewById<EditText>(R.id.nameEventsEdit)
//        val editTextlocal = findViewById<EditText>(R.id.localEventEdit)
//        val editTextdata = findViewById<EditText>(R.id.dataEventEdit)
//        val editTexthora = findViewById<EditText>(R.id.horaEventEdit)
//        val editTextDescription = findViewById<EditText>(R.id.descriptionEventsEdit)
//
//        val buttonDone = findViewById<Button>(R.id.buttonDone)
//
//        buttonDone.setOnClickListener {
//            val intent = intent
//            intent.putExtra("event_name", editTextName.text.toString())
//            intent.putExtra("event_local", editTextlocal.text.toString())
//            intent.putExtra("event_data", editTextdata.text.toString())
//            intent.putExtra("event_hora", editTexthora.text.toString())
//            intent.putExtra("plant_description", editTextDescription.text.toString())
//            setResult(RESULT_OK, intent)
//            finish()
//        }
//
//    }
//}