package com.example.townboard2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText


//<?xml version="1.0" encoding="utf-8"?>
//<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
//xmlns:app="http://schemas.android.com/apk/res-auto"
//xmlns:tools="http://schemas.android.com/tools"
//android:layout_width="match_parent"
//android:layout_height="match_parent"
//tools:context=".addPublicActivity">
//
//
//<de.hdodenhof.circleimageview.CircleImageView
//android:id="@+id/iamgemEmpresa"
//android:layout_width="250dp"
//android:layout_height="250dp"
//android:layout_centerHorizontal="true"
//android:src="@color/black"
//android:layout_marginTop="20dp"/>
//
//<EditText
//android:id="@+id/editName"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_centerHorizontal="true"
//android:layout_below="@id/iamgemEmpresa"
//android:layout_marginTop="10dp"
//android:textColor="@color/black"
//android:hint="Nome da empresa"/>
//
//<EditText
//android:id="@+id/editLocal"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_centerHorizontal="true"
//android:layout_below="@id/editName"
//android:layout_marginTop="10dp"
//android:textColor="@color/black"
//android:textSize="15sp"
//android:hint="Local"
//android:textStyle="bold"/>
//<EditText
//android:id="@+id/minieditDescription"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_below="@id/editLocal"
//android:layout_marginStart="20sp"
//android:layout_marginTop="15dp"
//android:hint="Uma breve descricao"
//android:textColor="@color/black"/>
//
//
//<EditText
//android:id="@+id/editDescription"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_below="@id/minieditDescription"
//android:layout_marginStart="20sp"
//android:layout_marginTop="15dp"
//android:hint="Uma descricao mais detalhada"
//android:textColor="@color/black" />
//
//<Button
//android:id="@+id/buttonDone"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_marginEnd="8dp"
//android:layout_marginBottom="8dp"
//android:text="Done"
//app:layout_constraintBottom_toBottomOf="parent"
//app:layout_constraintEnd_toEndOf="parent" />
//
//</RelativeLayout>
//class addPublicActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_add_public)
//
//        val editTextName = findViewById<EditText>(R.id.editName)
//        val editTextLocal = findViewById<EditText>(R.id.editLocal)
//        val editTextminiDescription = findViewById<EditText>(R.id.minieditDescription)
//        val editTextDescription = findViewById<EditText>(R.id.editDescription)
//        val buttonDone = findViewById<Button>(R.id.buttonDone)
//
//        buttonDone.setOnClickListener {
//            val intent = intent
//            intent.putExtra("public_name", editTextName.text.toString())
//            intent.putExtra("public_local", editTextLocal.text.toString())
//            intent.putExtra("public_minidescription", editTextminiDescription.text.toString())
//            intent.putExtra("public_description",editTextDescription.text.toString())
//            setResult(RESULT_OK, intent)
//            finish()
//        }
//
//    }
//}